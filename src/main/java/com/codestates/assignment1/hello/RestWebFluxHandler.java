package com.codestates.assignment1.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RestWebFluxHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {

        String name = request.queryParam("name").get();
        RestWebFlux greeting = new RestWebFlux(name, "hello! " + name);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(greeting));
    }
}
