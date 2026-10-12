package com.library.ProcessesAndThreads;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Concurrency = several tasks are in progress over the same period.
 * Parallelism = several tasks execute at the very same instant on different cores.
 */
public class ConcurrencyAndParallelism {

	private static final String[] TASKS = { "A", "B", "C" };
	private static final long WORK_MS = 240;   // CPU time each task needs to finish
	private static final long SLICE_MS = 40;   // how long a task holds a core before yielding
	private static final long COLUMN_MS = 20;  // one character on the timeline
	private static final int COLUMNS = 50;

	private static final AtomicLong BLACKHOLE = new AtomicLong();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Logical processors: " + Runtime.getRuntime().availableProcessors());
		System.out.println("Each task needs " + WORK_MS + " ms of CPU. One column = " + COLUMN_MS + " ms.");
		System.out.println();

		sequential();
		timeShared(1, "2. CONCURRENT - 1 core, context switch every " + SLICE_MS + " ms");
		timeShared(TASKS.length, "3. PARALLEL   - " + TASKS.length + " cores, context switch every " + SLICE_MS + " ms");
	}

	/** One core, no context switching: a task runs to completion before the next one starts. */
	private static void sequential() {
		Timeline timeline = new Timeline();
		long start = System.nanoTime();
		for (String task : TASKS) {
			burn(timeline, task, WORK_MS);
		}
		report("1. SEQUENTIAL - 1 core, no context switching", timeline, start);
	}

	/** A semaphore stands in for the CPU: one permit = one core a task may occupy. */
	private static void timeShared(int cores, String title) throws InterruptedException {
		Semaphore cpu = new Semaphore(cores, true);
		Timeline timeline = new Timeline();
		long start = System.nanoTime();

		Thread[] threads = new Thread[TASKS.length];
		for (int i = 0; i < TASKS.length; i++) {
			String task = TASKS[i];
			threads[i] = new Thread(() -> {
				long remaining = WORK_MS;
				while (remaining > 0) {
					long slice = Math.min(SLICE_MS, remaining);
					cpu.acquireUninterruptibly();
					try {
						burn(timeline, task, slice);
					} finally {
						cpu.release();
					}
					remaining -= slice;
					Thread.yield();
				}
			}, "task-" + task);
			threads[i].start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		report(title, timeline, start);
	}

	/** Busy loop so the task actually occupies a core instead of sleeping. */
	private static void burn(Timeline timeline, String task, long millis) {
		long end = System.nanoTime() + millis * 1_000_000L;
		double sink = 0;
		timeline.enter();
		while (System.nanoTime() < end) {
			timeline.mark(task);
			sink += Math.sqrt(sink + 1);
		}
		timeline.exit();
		BLACKHOLE.addAndGet((long) sink);
	}

	private static void report(String title, Timeline timeline, long startNanos) {
		System.out.println(title);
		timeline.print();
		System.out.println("   wall clock      : " + (System.nanoTime() - startNanos) / 1_000_000L + " ms");
		System.out.println("   peak at once    : " + timeline.peak() + " task(s)");
		System.out.println();
	}

	private static final class Timeline {

		private final long start = System.nanoTime();
		private final Map<String, boolean[]> rows = new LinkedHashMap<>();
		private final AtomicInteger running = new AtomicInteger();
		private final AtomicInteger peak = new AtomicInteger();

		// Rows are created up front so worker threads only ever write into their own array.
		Timeline() {
			for (String task : TASKS) {
				rows.put(task, new boolean[COLUMNS]);
			}
		}

		void enter() {
			peak.accumulateAndGet(running.incrementAndGet(), Math::max);
		}

		void exit() {
			running.decrementAndGet();
		}

		void mark(String task) {
			int column = (int) ((System.nanoTime() - start) / (COLUMN_MS * 1_000_000L));
			if (column < COLUMNS) {
				rows.get(task)[column] = true;
			}
		}

		int peak() {
			return peak.get();
		}

		void print() {
			for (Map.Entry<String, boolean[]> row : rows.entrySet()) {
				StringBuilder bar = new StringBuilder("   task " + row.getKey() + " |");
				for (boolean busy : row.getValue()) {
					bar.append(busy ? '#' : '.');
				}
				System.out.println(bar);
			}
		}
	}
}
