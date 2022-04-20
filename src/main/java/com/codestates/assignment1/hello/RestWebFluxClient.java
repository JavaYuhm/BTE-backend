package com.codestates.assignment1.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class RestWebFluxClient {

    private final WebClient client;
    public RestWebFluxClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:5555").build();
    }

    public Mono<RestWebFlux> getUserJobInfo(String name) {
        System.out.println("client 실행");
        return this.client.get().uri("/userJobInfo?name="+name).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RestWebFlux.class);

    }
}
