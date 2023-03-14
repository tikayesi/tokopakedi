package com.enigmacamp.tokopakedi.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(){
        return "Hallo Andi!";
    }

    // PATH PARAMETER / PATH VARIABLE
    @GetMapping("/path-var/{value}")
    public String pathVar(@PathVariable String value){
        return "Path Variable " + value;
    }

    // QUERY PARAMETER / REQUEST PARAM
    @GetMapping("/query-par")
    public String queryPar(@RequestParam String var){
        return "Query parameter: " + var;
    }

    @PostMapping("/req-body")
    public String reqBody(@RequestBody HashMap<String, String> mapBody){
        return "Request Body: " + mapBody;
    }
}
