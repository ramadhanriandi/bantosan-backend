package com.blibli.demo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.concurrent.Executors;

public class BelajarBuffer {

  public static Flux<Integer> createFlux(int total) {
    return Flux.create(sink -> {
      try {
        for(int i = 0; i < total; i++) {
          sink.next(i);
        }
        sink.complete();
      } catch (Exception e) {
        sink.error(e);
      }
    });
  }

  public static void main(String[] args) throws IOException {
//    createFlux(100)
//            .doOnNext(integer -> System.out.println(integer))
//            .buffer(10)
//            .doOnNext(integers -> System.out.println(integers))
//            .subscribe();

//    createFlux(100)
//            .map(integer -> kali(integer))
//            .onErrorReturn(-1)
//            .doOnNext(integer -> System.out.println(integer))
//            .subscribe();

    Scheduler scheduler = Schedulers.fromExecutorService(Executors.newFixedThreadPool(5));

    createFlux(100)
            .doOnNext(integer -> printThread())
            .subscribeOn(scheduler)
            .doOnNext(integer -> printThread())

            .publishOn(Schedulers.parallel())
            .doOnNext(integer -> printThread())

            .subscribe();

    System.in.read();
  }

  public static void printThread() {
    System.out.println(Thread.currentThread().getName());
  }

  public static Integer kali(Integer integer) {
    if (integer > 50) {
      throw new IllegalArgumentException();
    }
    return integer * 2;
  }
}
