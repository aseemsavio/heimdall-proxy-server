package com.savio.heimdall.controller;

import com.savio.heimdall.service.HeimdallService;
import com.savio.heimdall.template.AwakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseem Savio
 */

@RestController
public class HeimdallController {

    @Autowired
    HeimdallService heimdallService;

    WebClient webClient;

    @Value("${coolio-resource-server-url}")
    private String coolioResourceServerURL;

    @Value("${app-name.coolio-resource-server}")
    private String coolioResourceServerName;

    @PostConstruct
    public void init(){
        webClient = WebClient.builder().baseUrl(coolioResourceServerURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @GetMapping("/wakeUpCall")
    public ResponseEntity<List<AwakeResponse>> wakeUpCall(){

        Mono<String> coolioResourceServerMono = getMono();
        String coolioResourceServerString = getValue(coolioResourceServerMono);
        List<AwakeResponse> awakeResponseList = new ArrayList<>();
        awakeResponseList.add(new AwakeResponse(coolioResourceServerName, getStatus(coolioResourceServerString)));
        awakeResponseList.add(new AwakeResponse("Savio", "sleeping"));
        return ResponseEntity.ok().header("status", "success").body(awakeResponseList);
    }

    public Mono<String> getMono(){
        return webClient.get()
                .uri("/all/lub")
                .retrieve()
                .bodyToMono(String.class);
    }

    String getValue(Mono<String> mono) {
        return mono.block();
    }

    String getStatus(String monoString){
        if(monoString.equals("dub"))
            return "alive";
        return "sleeping";
    }

}
