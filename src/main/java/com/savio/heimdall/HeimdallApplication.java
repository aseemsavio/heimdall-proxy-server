package com.savio.heimdall;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class HeimdallApplication {

    /*@Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }*/

    /*@Bean*
    WebClient webClient(){
        return WebClient.create("https://coolio-resource-server.herokuapp.com");
    }

    @Bean
    CommandLineRunner commandLineRunner(WebClient webClient){
        return strings -> {
            webClient.get()
                    .uri("/all/lub")
                    .retrieve()
                    .bodyToFlux(String.class)
                    .subscribe();
        };
    }*/

    public static void main(String[] args) {
        SpringApplication.run(HeimdallApplication.class, args);
    }

}
