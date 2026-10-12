package com.library.ProcessesAndThreads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CoreCounter {

	public static void main(String[] args) throws Exception {
		int processors = 0;
		int cores = 0;
		int threads = 0;
		int clockSpeed = 0;

		// The JVM only exposes logical processor count, so core and clock data come from the OS.
		String script = "Get-CimInstance Win32_Processor | ForEach-Object "
				+ "{ $_.NumberOfCores, $_.NumberOfLogicalProcessors, $_.MaxClockSpeed -join ',' }";

		ProcessBuilder builder = new ProcessBuilder(
				"powershell.exe", "-NoProfile", "-NonInteractive", "-Command", script);
		Process process = builder.start();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.trim().split(",");
				if (parts.length != 3) {
					continue;
				}
				processors++;
				cores += Integer.parseInt(parts[0]);
				threads += Integer.parseInt(parts[1]);
				clockSpeed = Math.max(clockSpeed, Integer.parseInt(parts[2]));
			}
		}
		process.waitFor();

		System.out.println("Processors       : " + processors);
		System.out.println("Cores            : " + cores);
		System.out.println("Threads          : " + threads);
		System.out.println("Clock speed      : " + clockSpeed + " MHz");
		System.out.println("Available to JVM : " + Runtime.getRuntime().availableProcessors());
	}
}
