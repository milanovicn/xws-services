package com.example.voziloservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@FeignClient(name = "user")
public interface UserClient {

    @GetMapping("/chackNuberOfCars/{id}")
    boolean chackNuberOfCars(@PathVariable("id") Long id);

    @PutMapping("/uvecajBrojOglasa/{id}")
    void uvecajBrojOglasa(@PathVariable("id") Long id);

}
