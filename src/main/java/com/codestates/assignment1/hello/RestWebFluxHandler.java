package com.codestates.assignment1.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class RestWebFluxHandler {

    @Autowired
    private RestWebFluxClient restWebFluxClient;

    public Mono<ServerResponse> hello(ServerRequest request) {

        Optional<String> queryString =  request.queryParam("name");
        if(!queryString.isPresent()){
            return ServerResponse.badRequest().build();
        }

        String name = queryString.get();
        Mono<RestWebFlux> userInfo = restWebFluxClient.getUserJobInfo(name)
                .map(res -> new RestWebFlux(name, "hello "+name, res.getJob())
                );

        userInfo.switchIfEmpty(Mono.just(new RestWebFlux()));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userInfo, RestWebFlux.class);
                //.body(BodyInserters.fromValue(userInfo));


    }
}
