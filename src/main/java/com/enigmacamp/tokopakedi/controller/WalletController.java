package com.enigmacamp.tokopakedi.controller;

import com.enigmacamp.tokopakedi.dto.WalletDTO;
import com.enigmacamp.tokopakedi.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class WalletController {

    final RestTemplate restTemplate;


    @Autowired
    public WalletController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/omo")
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO walletDTO){
        return restTemplate.postForEntity(URI.create(ApiUrlConstant.URL_OMO), walletDTO, WalletDTO.class);
    }
}