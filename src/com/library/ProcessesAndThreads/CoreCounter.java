package com.library.ProcessesAndThreads;

public class CoreCounter {
	public static void main(String[] args) {
		int logicalCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Available logical processors: " + logicalCores);
	}
}
