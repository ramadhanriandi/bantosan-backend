package com.blibli.demo;

import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BelajarReactive {
  public static void main(String[] args) {
    Mono<Integer> mono = Mono.just("Riandi")
            .doOnNext(string -> System.out.println(string))
            .flatMap(string -> toUppercase(string))
            .doOnNext(string -> System.out.println(string))
            .flatMap(string -> length(string))
            .doOnNext(string -> System.out.println(string));
    mono.subscribe();

    List<String> names = Arrays.asList("Riandi", "Ramadhan");

    Flux<Integer> flux = Flux.fromIterable(names)
            .filter(string -> string.length() > 5)
            .doOnNext(string -> System.out.println(string))
            .flatMap(string -> toUppercase(string))
            .doOnNext(string -> System.out.println(string))
            .flatMap(string -> length(string))
            .doOnNext(string -> System.out.println(string))
            .doOnComplete(() -> System.out.println("Selesai"))
            .doOnError(throwable -> System.out.println("Error"));
    flux.subscribe();

//    Flux<String> flux = Flux.just("Riandi", "Ramadhan")
//            .doOnNext(string -> System.out.println(string));

//    List<String> namesUppercase = new ArrayList<>();
//    for(String name : names) {
//      namesUppercase.add(name.toUpperCase());
//    }
//
//    List<Integer> namesCount = new ArrayList<>();
//    for(String nameUpper : namesUppercase) {
//      namesCount.add(nameUpper.length());
//    }
  }

  public static Mono<String> toUppercase(String value) {
//    if (value.equals("Riandi")) {
//      return Mono.error(new NullPointerException());
//    }
    return Mono.just(value.toUpperCase());
  }

  public static Mono<Integer> length(String value) {
//    if (StringUtils.isEmpty(value)) {
//      return
//    }
    return Mono.just(value.length());
  }
}
