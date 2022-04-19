package com.codestates.assignment1.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestWebFluxClient {
    /*
    * Spring WebClient : 웹으로 API를 호출하기 위해 사용되는 Http Client 모듈 중 하나
      RestTemplate 와 비교 
      RestTemplate 멀티쓰레드, Blocking 방식 / WebClient 싱글쓰레드 Non-Blocking 방식
      
      WebClient 는 요청은 Event Loop내에 Job으로 등록 , Event Loop는 각 Job을 제공자에게 요청한 후 결과를 기다리지 않고 다른 Job을 처리
      제공자로부터  Callback 응답이 오면, 그 결과를 요청자에게 제공
      
      해당 Client를 통해 과제 구현/적용은 하지 못함.
    */
    private final WebClient client;
    public RestWebFluxClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessage() {
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RestWebFlux.class)
                .map(RestWebFlux::getMessage);
    }
}
