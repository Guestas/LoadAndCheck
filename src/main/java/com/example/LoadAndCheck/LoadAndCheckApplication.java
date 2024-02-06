package com.example.LoadAndCheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class LoadAndCheckApplication {

	private static final AtomicBoolean running = new AtomicBoolean(true);
	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(LoadAndCheckApplication.class, args);
		// Start a separate thread to read console input
		new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while (running.get()) {
				String input = scanner.nextLine().trim(); // Read console input
				if ("stop".equalsIgnoreCase(input)) {
					stopApplication();
					break;
				}
			}
			scanner.close();
		}).start();
	}

	private static void stopApplication() {
		running.set(false);
		applicationContext.close();
	}
}
