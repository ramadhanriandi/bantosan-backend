package com.blibli.demo;

import java.util.Random;
import java.util.concurrent.*;

public class BelajarThreadPool {

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

		ExecutorService service = new ThreadPoolExecutor(1, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));

		for(int i = 0; i < 10; i++) {
			service.execute(runnable);
		}

		System.out.println("Program");

	}

}
