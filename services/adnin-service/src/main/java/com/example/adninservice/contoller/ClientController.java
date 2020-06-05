package com.example.adninservice.contoller;


import com.example.adninservice.model.Client;
import com.example.adninservice.service.ClientService;
import com.example.adninservice.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientService clientServiceImpl;


    @PostMapping( value = "/addClient")
    public ResponseEntity<?> addClient(@RequestBody Client client) throws Exception {

       Client newClient=clientServiceImpl.addClient(client);

        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAllClients")
    public ResponseEntity<List<Client>> getAllClients() throws Exception {

        List<Client> clients=clientServiceImpl.getAll();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }
    @GetMapping(value = "/chackNuberOfCars/{id}")
    public boolean chackNuberOfCars(@PathVariable("id") Long id) throws Exception {


        return clientServiceImpl.proveriBrojOglasa(id);
    }

    @PutMapping(value = "/uvecajBrojOglasa/{id}")
    public void uvecajBrojOglasa(@PathVariable("id") Long id) throws Exception {


        clientServiceImpl.povecajBrojOglasa(id);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Client>> removeClients(@PathVariable("id") Long id) throws Exception {

        clientServiceImpl.removeClient(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
