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



    @Autowired
    private WebClient.Builder webClientBuilder;


}
