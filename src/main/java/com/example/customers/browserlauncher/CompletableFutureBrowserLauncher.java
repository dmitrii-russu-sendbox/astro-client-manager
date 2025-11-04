package com.example.customers.browserlauncher;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@Profile("future")
public class CompletableFutureBrowserLauncher {

    private final ApplicationContext context;

    public CompletableFutureBrowserLauncher(ApplicationContext context) {
        this.context = context;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {

        if (!(context instanceof WebServerApplicationContext webCtx)) return;
        int port = webCtx.getWebServer().getPort();
        String url = "http://localhost:" + port;

        CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS)
                .execute(() -> openBrowser(url));
    }

    private void openBrowser(String url) {

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