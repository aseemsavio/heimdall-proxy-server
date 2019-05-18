package com.savio.heimdall.controller;

import com.savio.heimdall.service.HeimdallService;
import com.savio.heimdall.template.AwakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aseem Savio
 */

@RestController
public class HeimdallController {

    @Autowired
    HeimdallService heimdallService;

    WebClient webClient;

    @PostConstruct
    public void init(){
        webClient = WebClient.builder().baseUrl("https://coolio-resource-server.herokuapp.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @GetMapping("/wakeUpCall")
    public ResponseEntity<List<AwakeResponse>> wakeUpCall(){

        Mono<String> dub = aseem();
        String a = getValue(dub);
        //List<AwakeResponse> awakeResponseList = heimdallService.wakeUpCall();
        List<AwakeResponse> awakeResponseList = new ArrayList<>();
        awakeResponseList.add(new AwakeResponse(a, "awake"));
        awakeResponseList.add(new AwakeResponse("Savio", "sleeping"));
        return ResponseEntity.ok().header("status", "success").body(awakeResponseList);
    }

    public Mono<String> aseem(){
        return webClient.get()
                .uri("/all/lub")
                .retrieve()
                .bodyToMono(String.class);
    }

    String getValue(Mono<String> mono) {
        return mono.block();
    }

}
