package com.example.customers.browserlauncher;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Profile("scheduledExecutor")
public class ScheduledExecutorServiceBrowserLauncher {

    private final ApplicationContext context;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public ScheduledExecutorServiceBrowserLauncher(ApplicationContext context) {
        this.context = context;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        //scheduler.schedule(() -> openBrowser(), 2, TimeUnit.SECONDS);
        scheduler.schedule(() -> {openBrowser(); scheduler.shutdown();}, 2, TimeUnit.SECONDS);
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

            String[] command;
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                command = new String[]{"cmd", "/c", "start", url};
            } else if (os.contains("mac")) {
                command = new String[]{"open", url};
            } else {
                command = new String[]{"xdg-open", url};
            }

            new ProcessBuilder(command).start();

        } catch (Exception e) {
            System.out.println("Please open browser manually: " + url);
        }
    }

}

