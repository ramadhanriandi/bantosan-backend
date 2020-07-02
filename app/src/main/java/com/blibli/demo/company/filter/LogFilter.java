package com.blibli.demo.company.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return Mono.just(exchange)
            .doOnNext(t -> log.info("Receive request"))
            .flatMap(t -> chain.filter(t))
            .doOnSuccess((aVoid -> log.info("Success process request")));
  }
}
