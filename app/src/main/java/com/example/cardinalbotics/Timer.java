package com.example.cardinalbotics;

import android.os.SystemClock;

public class Timer {
    //Start and End times in Seconds
    private static long start = 0, end = -1;
    private static boolean running = false;

    private Timer() {}

    public static void start() {
        if (running) return;
        start = SystemClock.elapsedRealtime() / 1000;
        running = true;
    }

    public static void end() {
        if (!running) return;
        end = SystemClock.elapsedRealtime() / 1000;
        running = false;
    }

    public static void toggle() {
        if(running) {
            System.out.println("END TIMER TOGGLE");
            end();
            if(elapsed() > 0) Requester.send(elapsed());
        } else {
            System.out.println("START TIMER TOGGLE");
            start();
        }
    }

    public static long elapsed() {
        if (running) return -1;
        return end > start ? end - start : -1;
    }

    public static long elapsed(boolean useCurrentTime) {
        if(useCurrentTime) return running ? SystemClock.elapsedRealtime() / 1000 - start : -1;
        else return elapsed();
    }
}
