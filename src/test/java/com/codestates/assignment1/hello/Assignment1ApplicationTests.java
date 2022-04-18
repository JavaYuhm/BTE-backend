package com.codestates.assignment1.hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
class Assignment1ApplicationTests {

    // 1. ["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker”] 를 순서대로 하나의 스트림으로 처리되는 로직 검증
    // 결과값 : "Blenders", "Old", "Johnnie", "Pride", "Monk", "Walker"
    @Test
    void concat_Order() {
        
        // 시퀀스를 생성하는 가장 쉬운 방법은 Flux.just()
        // Mono.just(null)은 안됨. NullPointerException -> Mono.empty() / 값이 있을 수도 있고 없을 수도 있는 Mono를 생성 justOrEmpty() 
        Flux<String> stringFlux1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux <String> stringFlux2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));
        
        //Flux.concat: 첫번째 인자로 들어온 Publisher의 발행이 종료될 때까지 데이터를 전달한 후, 다음 인자의 Publisher를 전달
        Flux <String> concatString = Flux.concat(stringFlux1, stringFlux2)
                .log();

        StepVerifier.create(concatString)
                .expectSubscription()
                .expectNext("Blenders", "Old", "Johnnie", "Pride", "Monk", "Walker")
                .verifyComplete();
    }

    // 2. 1~100 까지의 자연수 중 짝수만 출력하는 로직 검증
    // 2, 4, 6 .... 100
    @Test
    void even_Print(){
        // Flux.range() 메서드를 사용하면 순차적으로 증가하는 Integer를 생성하는 Flux를 생성
        Flux <Integer> evenFlux = Flux.range(1,100)
                .filter(i -> i%2 == 0)
                // 한개의 시퀀스가 전달 될 때마다 doOnNext가 발생되며, 어떤 이벤트가 발생될 지 지정함(짝수 I 출력).
                .doOnNext(i -> System.out.println(i))
                .log();


        StepVerifier.create(evenFlux)
                .thenConsumeWhile(i->i%2==0)
                .verifyComplete();
    }

    // 3. “hello”, “there” 를 순차적으로 publish하여 순서대로 나오는지 검증
    @Test
    void publish_Test(){

        Flux <String> pubString = Flux.just("hello","there")
                .delayElements(Duration.ofSeconds(1))
                // publishOn() 메서드를 이용하면 next, complete, error신호를 별도 쓰레드로 처리, parameter로 스케줄러와 prefetch(미리 가져올 데이터 수)를 받을 수 있음.  
                .publishOn(Schedulers.boundedElastic())
                .log();

        StepVerifier.create(pubString)
                .expectNext("hello", "there")
                .verifyComplete();

    }

    // 4. 아래와 같은 객체가 전달될 때 “JOHN”, “JACK” 등 이름이 대문자로 변환되어 출력되는 로직 검증
    @Test
    void upperFlux(){
        Person person1 =  new Person("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678");
        Person person2 =  new Person("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678");

        Flux<Person> personFlux = Flux.just(person1, person2)
                .doOnNext(p -> System.out.println(p.getName().toUpperCase()))
                .delayElements(Duration.ofSeconds(1))
                .log();

        StepVerifier.create(personFlux)
                .assertNext(p -> p.getName().equals("JOHN"))
                .assertNext(p -> p.getName().equals("JACK"))
                .verifyComplete();
    }

    // 5. ["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker”]를 압축하여 스트림으로 처리 검증
    @Test
    public void zipTest() {
        Flux<String> stringFlux1 = Flux.just("Blenders", "Old", "Johnnie")
                        .delayElements(Duration.ofSeconds(1)).log();

        Flux<String> stringFlux2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1)).log();

        Flux<String> zipFlux = stringFlux1.zipWith(stringFlux2 , (name1,name2) -> name1 + " " + name2)
                .log();

        StepVerifier.create(zipFlux)
                .expectNext("Blenders Pride", "Old Monk", "Johnnie Walker")
                .verifyComplete();
    }

    // 6.["google", "abc", "fb", "stackoverflow”] 의 문자열 중 5자 이상 되는 문자열만 대문자로 비동기로 치환하여 1번 반복하는 스트림으로 처리하는 로직 검증
    @Test
    public void asyncFiveLengthString(){
        Flux <String> stringFlux = Flux.just("google", "abc", "fb", "stackoverflow")
                .delayElements(Duration.ofSeconds(1))
                .filter(s -> s.length()>=5)
                // 비동기로 신호 처리 대문자 치환, repeat() 
                .publishOn(Schedulers.boundedElastic())
                .flatMap(s -> Mono.just(s.toUpperCase()))
                .repeat(1)
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("GOOGLE", "STACKOVERFLOW", "GOOGLE", "STACKOVERFLOW")
                .verifyComplete();
    }
}
