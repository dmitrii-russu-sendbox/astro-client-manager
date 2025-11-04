package com.example.customers.browserlauncher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class BrowserLauncher implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port:8082}")
    private String port;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Это событие возникает когда приложение полностью готово
        openBrowserWithDelay();
    }

    private void openBrowserWithDelay() {
        new Thread(() -> {
            try {
                // Ждем дополнительно 2 секунды после ready event
                Thread.sleep(2000);
                openBrowser();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void openBrowser() {
        String url = "http://localhost:" + port;

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI(url));
                    return;
                }
            }

            // Fallback для разных ОС
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