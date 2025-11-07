package com.example.customers.browserlauncher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Try {
    private static final Logger log = LoggerFactory.getLogger(Try.class);

    public static boolean run(ThrowingRunnable action) {
        try {
            action.run();
            return true;
        } catch (Exception e) {
            log.error("❌ Operation failed: {}", e.getMessage(), e);
            return false;
        }
    }

    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws Exception;
    }

}