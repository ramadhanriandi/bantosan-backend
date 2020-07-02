package com.blibli.demo.company.config;

import com.blibli.demo.company.model.Person;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class PersonArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return Person.class.isAssignableFrom(methodParameter.getParameterType());
  }

  @Override
  public Mono<Object> resolveArgument(MethodParameter methodParameter,
                                      BindingContext bindingContext,
                                      ServerWebExchange serverWebExchange) {
    return Mono.fromCallable(() -> {
      String name = serverWebExchange.getRequest().getHeaders().getFirst("X-Person-Name");

      if(StringUtils.isEmpty(name)) {
        throw new IllegalArgumentException(("Unauthorized"));
      }

      return Person.builder()
              .name(name)
              .build();
    });
  }
}
