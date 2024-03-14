package com.rizky.lessup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizky.lessup.dto.ControllerResponse;
import com.rizky.lessup.dto.LoginDto;
import com.rizky.lessup.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestClientService {

    private RestTemplate restTemplate;

    @Autowired
    public RestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<?> createUser(UserDto userDto) {
        try {
            ControllerResponse<UserDto> response = restTemplate.postForObject("http://localhost:8080/auth/register/admin", userDto, ControllerResponse.class);
            log.info("Response : {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (e instanceof HttpClientErrorException httpClientErrorException) {
                try {
                    ControllerResponse controllerResponse = mapper.readValue(httpClientErrorException.getResponseBodyAsString(), ControllerResponse.class);
                    return ResponseEntity
                            .status(httpClientErrorException.getStatusCode())
                            .body(controllerResponse);
                } catch (Exception ex) {
                    return ResponseEntity
                            .status(500)
                            .body("Failed to cast response");
                }
            }
            return ResponseEntity
                    .status(500)
                    .body("Failed to create user, please try again later!");
        }
    }

    public ResponseEntity<?> loginUser(LoginDto loginDto) {
        try {
            ControllerResponse<LoginDto> response = restTemplate.postForObject("http://localhost:8080/auth/login", loginDto, ControllerResponse.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException httpClientErrorException) {
                try {
                    ControllerResponse controllerResponse = mapper.readValue(httpClientErrorException.getResponseBodyAsString(), ControllerResponse.class);
                    return ResponseEntity
                            .status(httpClientErrorException.getStatusCode())
                            .body(controllerResponse);
                } catch (Exception ex) {
                    return ResponseEntity
                            .status(500)
                            .body("Failed to cast response");
                }
            }
            return ResponseEntity
                    .status(500)
                    .body("Failed to login, please try again later!");
        }
    }

    public ResponseEntity<?> getUserById(String id) {
        try {
            ControllerResponse<UserDto> response = restTemplate.getForObject("http://localhost:8080/api/users" + id, ControllerResponse.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException httpClientErrorException) {
                try {
                    ControllerResponse controllerResponse = mapper.readValue(httpClientErrorException.getResponseBodyAsString(), ControllerResponse.class);
                    return ResponseEntity
                            .status(httpClientErrorException.getStatusCode())
                            .body(controllerResponse);
                } catch (Exception ex) {
                    return ResponseEntity
                            .status(500)
                            .body("Failed to cast response");
                }
            }
            return ResponseEntity
                    .status(500)
                    .body("Failed to get user, please try again later!");
        }
    }

    public ResponseEntity<?> getAllUser() {
        try {
            ControllerResponse<UserDto> response = restTemplate.getForEntity("http://localhost:8080/api/users",ControllerResponse.class).getBody();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException httpClientErrorException) {
                try {
                    ControllerResponse controllerResponse = mapper.readValue(httpClientErrorException.getResponseBodyAsString(), ControllerResponse.class);
                    return ResponseEntity
                            .status(httpClientErrorException.getStatusCode())
                            .body(controllerResponse);
                } catch (Exception ex) {
                    return ResponseEntity
                            .status(500)
                            .body("Failed to cast response");
                }
            }
            return ResponseEntity
                    .status(500)
                    .body("Failed to get user, please try again later!");

        }
    }
}
