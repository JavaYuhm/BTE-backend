package com.codestates.assignment1.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class RestWebFluxHandler {
    // router에서 넘겨준  ServerRequest 객체를 받음.
    // bodyToMono() 메소드를 통해 json 데이터를 Dto로 매핑 가능하다고 하는데, Test 해볼 것,,
    public Mono<ServerResponse> hello(ServerRequest request) {

        Optional<String> queryString =  request.queryParam("name");
        if(!queryString.isPresent()){
            return ServerResponse.badRequest().build();
        }

        String name = queryString.get();
        RestWebFlux greeting = new RestWebFlux(name, "hello! " + name);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(greeting));
    }
}
