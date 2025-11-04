package com.example.customers.browserlauncher;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.net.URI;

@Component
@Profile("thread")
public class ThreadBrowserLauncher {

    private final WebServerApplicationContext context;

    public ThreadBrowserLauncher(WebServerApplicationContext context) {
        this.context = context;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {

        if (!(context instanceof WebServerApplicationContext webCtx)) return;
        int port = webCtx.getWebServer().getPort();
        String url = "http://localhost:" + port;

        new Thread(() -> {
            try {
                Thread.sleep(2000); // короткая задержка для надёжности
                openBrowser();
            } catch (Exception e) {
                System.out.println("Please open manually: " + url);
            }
        }).start();
    }

    private void openBrowser() {

        if (!(context instanceof WebServerApplicationContext webCtx)) return;
        int port = webCtx.getWebServer().getPort();
        String url = "http://localhost:" + port;

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI(url));
                    return;
                }
            }

            Runtime runtime = Runtime.getRuntime();
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                runtime.exec("cmd /c start " + url);
            } else if (os.contains("mac")) {
                runtime.exec("open " + url);
            } else {
                runtime.exec("xdg-open " + url);
            }

        } catch (Exception e) {
            System.out.println("Please open browser manually: " + url);
        }
    }

}