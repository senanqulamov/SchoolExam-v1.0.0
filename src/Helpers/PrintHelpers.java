package Helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrintHelpers {

    private static boolean debugMode = true;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String GRAY = "\u001B[90m";


    // Animation config
    private static final int ANIMATION_DURATION_MS = 1000;
    private static final int DOT_INTERVAL_MS = 250;



    // ----------------- Section Helpers ----------------- //
    public static void printTitle(String title) {
        /*animateDots("TITLE");*/
        System.out.println(CYAN + "\n==== " + title.toUpperCase() + " ====" + RESET);
    }

    public static void printSection(String section) {
        /*animateDots("SECTION");*/
        System.out.println("\n" + CYAN + "---- " + section + " ----" + RESET);
    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------------");
    }



    // Debug toggle
    public static void enableDebug() {
        debugMode = true;
    }

    public static void disableDebug() {
        debugMode = false;
    }



    // ----------------- Animation ----------------- //
    private static void animateDots(String label) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + ANIMATION_DURATION_MS;
        int dotCount = 0;

        while (System.currentTimeMillis() < endTime) {
            long currentTime = System.currentTimeMillis();
            int elapsedSeconds = (int) ((currentTime - startTime) / 1000);
            int totalEstimated = (int) (ANIMATION_DURATION_MS / 1000);

            String dots = ".".repeat(dotCount % 4);
            String line = String.format(
                    "\r[Loading ] (%ds / ~%ds) %s   ",
                    elapsedSeconds,
                    totalEstimated,
                    dots
            );

            System.out.print(line);
            System.out.flush();

            try {
                Thread.sleep(DOT_INTERVAL_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            dotCount++;
        }

        int totalSeconds = (int) ((System.currentTimeMillis() - startTime) / 1000);

        String finalLine = String.format("\r[Success] (%ds) \n", totalSeconds);
        System.out.print(finalLine);
    }




    // ----------------- Base Printers ----------------- //
    private static String timestamp() {
        return "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "]";
    }

    private static void log(String label, String color, String message) {
        /*animateDots(label);*/
        System.out.println(color + timestamp() + " [" + label + "] " + message + RESET);
    }



    // ----------------- Main Public Methods ----------------- //
    public static void print(String message) {
        /*animateDots("PRINT");*/
        System.out.println(message);
    }

    public static void printInfo(String message) {
        log("INFO", BLUE, message);
    }

    public static void printWarning(String message) {
        log("WARNING", YELLOW, message);
    }

    public static void printSuccess(String message) {
        log("SUCCESS", GREEN, message);
    }

    public static void printError(String message) {
        log("ERROR", RED, message);
    }

    public static void printDebug(String message) {
        if (debugMode) {
            log("DEBUG", GRAY, message);
        }
    }

    public static void printFormatted(String format, Object... args) {
        /*animateDots("PRINT");*/
        System.out.printf(format + "%n", args);
    }

    public static void printFormattedInfo(String format, Object... args) {
        log("INFO", BLUE, String.format(format, args));
    }

    public static void printFormattedWarning(String format, Object... args) {
        log("WARNING", YELLOW, String.format(format, args));
    }

    public static void printFormattedSuccess(String format, Object... args) {
        log("SUCCESS", GREEN, String.format(format, args));
    }

    public static void printFormattedError(String format, Object... args) {
        log("ERROR",RED , String.format(format, args));
    }

    public static void printFormattedDebug(String format, Object... args) {
        if (debugMode) {
            log("DEBUG", GRAY, String.format(format, args));
        }
    }
}
