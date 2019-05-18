package com.savio.heimdall.controller;

import com.savio.heimdall.service.HeimdallService;
import com.savio.heimdall.template.AwakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aseem Savio
 */

@RestController
public class HeimdallController {

    @Autowired
    HeimdallService heimdallService;

    @GetMapping("/wakeUpCall")
    public ResponseEntity<List<AwakeResponse>> wakeUpCall(){
        //List<AwakeResponse> awakeResponseList = heimdallService.wakeUpCall();


        List<AwakeResponse> awakeResponseList = null;
        awakeResponseList.add(new AwakeResponse("Aseem", "awake"));
        awakeResponseList.add(new AwakeResponse("Savio", "sleeping"));
        return ResponseEntity.ok().header("status", "success").body(awakeResponseList);
    }

}
