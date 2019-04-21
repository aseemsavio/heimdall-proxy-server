package com.savio.heimdall.service;

import com.savio.heimdall.template.AwakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * @author Aseem Savio
 */

@Service
public class HeimdallService {

    @Value("${coolio-resource-server-url}")
    private String coolioResourceServerURL;

    @Value("${app-name.coolio-resource-server}")
    private String coolioResourceServerName;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<AwakeResponse> wakeUpCall() {

        List<AwakeResponse> awakeResponseList = null;
        String heartBeat = webClientBuilder.build()
                .get()
                .uri(coolioResourceServerURL + "/all/lub")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        AwakeResponse awakeResponse = new AwakeResponse(coolioResourceServerName, heartBeat);
        return null;
    }
}
