package com.rizky.lessup.controller;

import com.rizky.lessup.dto.LoginDto;
import com.rizky.lessup.service.RestClientService;
import com.rizky.lessup.util.ApiConstans;
import com.rizky.lessup.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstans.BASE_URL)
@Slf4j
public class RestClientController {

    private RestClientService restClientService;
    @Autowired
    public RestClientController(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    @PostMapping
    public ResponseEntity<?> createUserDto(@RequestBody UserDto userDto){
        log.info("Request : {}",userDto);
        return restClientService.createUser(userDto);
    }
    @PostMapping(ApiConstans.BASE_URL_LOGIN)
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        log.info("Request : {}",loginDto);
        return restClientService.loginUser(loginDto);
    }
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return restClientService.getAllUser();
    }

}
