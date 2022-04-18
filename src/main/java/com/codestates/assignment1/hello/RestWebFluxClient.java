package com.codestates.assignment1.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestWebFluxClient {

    private final WebClient client;


    public RestWebFluxClient(WebClient.Builder builder){
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessage(){
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RestWebFlux.class)
                .map(RestWebFlux::getName);
    }
}
