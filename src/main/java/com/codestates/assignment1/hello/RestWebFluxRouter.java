package com.codestates.assignment1.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

// proxyBeanMethods는 @Bean으로 빈을 등록하는 메소드에 프록시 패턴
// Spring Framework 5.2에서 생김, @Configuration의 시작 시간과 메모리 사용량 감소 등의 성능향상이 있으나, Configuration Method를 여러 번 호출할 경우 매번 Instance를 생성해 반환
@Configuration(proxyBeanMethods = false)
public class RestWebFluxRouter {

    // Functional Endpoint를 사용 하기 위해서는 함수형 인터페이스인 RouterFunction의 구현체를 만들어서 스프링 빈으로 등록 
    @Bean
    public RouterFunction<ServerResponse> route(RestWebFluxHandler restWebFluxHandler) {

        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), restWebFluxHandler::hello);
    }
}
