package com.example.customers.browserlauncher;

import com.example.customers.controller.pingcontroller.PingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.awt.Desktop;

@Component
@Profile("thread1")
public class ThreadBrowserLauncher1 {

    private final ApplicationContext context;
    private final PingController pingController;
    private static final Logger log = LoggerFactory.getLogger(ThreadBrowserLauncher1.class);

    public ThreadBrowserLauncher1(ApplicationContext context, PingController pingController) {
        this.context = context;
        this.pingController = pingController;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {

        if (!(context instanceof WebServerApplicationContext webCtx)) return;
        int port = webCtx.getWebServer().getPort();
        String url = "http://localhost:" + port;

        Thread.ofVirtual().start(() -> {
            Try.run(() -> Thread.sleep(2000));
            Try.run(() -> openBrowser(url));
        });

        // мониторинг активности браузера
        Thread.ofVirtual().start(this::monitorBrowserActivity);
    }


    private void openBrowser(String url) throws Exception {
        boolean opened = false;

        if (Desktop.isDesktopSupported()) {
            opened = Try.run(() -> Desktop.getDesktop().browse(URI.create(url)));
        }
        if (!opened) {
            opened = Try.run(() -> {
                Runtime runtime = Runtime.getRuntime();
                String os = System.getProperty("os.name").toLowerCase();
                runtime.exec(
                        os.contains("win") ? "cmd /c start " + url : os.contains("mac") ? "open " + url : "xdg-open " + url
                );
            });
        }

        if (!opened) {
            throw new Exception("Could not open browser using any method");
        }

        log.info("✅ Browser opened URL: {}", url);
    }


    private void monitorBrowserActivity() {
        while (true) {
            Try.run(() -> Thread.sleep(5000));
            long idle = System.currentTimeMillis() - pingController.getLastPing();
            if (idle > 20_000) {
                log.info("🚪 No ping from browser for 20s — shutting down application.");
                SpringApplication.exit(context, () -> 0);
                System.exit(0);
            }
        }
    }

}


