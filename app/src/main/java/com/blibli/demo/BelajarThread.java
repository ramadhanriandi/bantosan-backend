package com.blibli.demo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BelajarThread {

	public static void cepat(long waktu) {
		System.out.println(Thread.currentThread().getName());
		System.out.println("Cepat : " + waktu);
	}

	public static void lambat() throws InterruptedException {
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(5_000L);
		System.out.println("Lambat");
	}

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = () -> {
			try {
				lambat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

//		ExecutorService service = Executors.newFixedThreadPool(5);
//
//		for(int i = 0; i < 10; i++) {
//			service.execute(runnable);
//		}

//		Runnable runnableCepat = ()-> cepat();

		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

		Random random = new Random();

		for(int i = 0; i < 10; i++) {
//			service.schedule(runnableCepat, 5, TimeUnit.SECONDS);
			int delay = random.nextInt(10);
			service.schedule(() -> cepat(delay), delay, TimeUnit.SECONDS);
		}

		System.out.println("Program");

//		service.shutdown();
	}

}
