package com.example.adninservice.contoller;


import com.example.adninservice.model.Client;
import com.example.adninservice.service.ClientService;
import com.example.adninservice.service.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.MessageFormat;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientServiceImpl;


    @PostMapping( value = "/addClient")
    public ResponseEntity<?> addClient(@RequestBody Client client) throws Exception {

        Client newClient=clientServiceImpl.addClient(client);
        if(newClient!=null) {
            LOGGER.info(MessageFormat.format("CLIENT -ID:{0}-created, CLIENT-EMAIL:{1}", newClient.getId(), newClient.getEmail()));
        } else {
            LOGGER.error(MessageFormat.format("CLIENT-ID:{0}-not created, CLIENT-EMAIL:{1}" , newClient.getId(), newClient.getEmail()));
        }


        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAllClients")
    public ResponseEntity<List<Client>> getAllClients() throws Exception {

        List<Client> clients=clientServiceImpl.getAll();

        if(clients!=null) {
            LOGGER.info("CLIENT - returned all");
        } else {
            LOGGER.error("CLIENT - not returned all");
        }

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

        if(id!=null) {
            LOGGER.info("CLIENT:{0}-deleted");
        } else {
            LOGGER.error("CLIENT:{0}-not deleted ");
        }

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
