package com.example.cardinalbotics;

import android.os.SystemClock;

public class Timer {
	//Start and End times in Seconds
	private static long start = 0, end = -1;
	private static boolean running = false;

	private Timer() {}

	private static void start() {
		if(running) return;
		start = SystemClock.elapsedRealtime() / 1000;
		running = true;
	}

	private static void end() {
		if(!running) return;
		end = SystemClock.elapsedRealtime() / 1000;
	}

	private static long elapsed() {
		if(running) return -1;
		return end > start ? end - start : -1;
	}
}
