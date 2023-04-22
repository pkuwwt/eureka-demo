package com.example.springboot;
import java.io.IOException;

public class ProcessUtils {
	public static Process runCommand(String[] cmd) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(cmd);
		return builder.start();
	}
	public static void waitProcess(Process process) throws InterruptedException {
		process.waitFor();
	}
}
