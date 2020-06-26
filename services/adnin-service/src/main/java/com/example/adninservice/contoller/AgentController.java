package com.example.adninservice.contoller;


import com.example.adninservice.model.Agent;
import com.example.adninservice.model.Client;
import com.example.adninservice.repository.AgentRepository;
import com.example.adninservice.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AgentController {
    Logger LOGGER = LoggerFactory.getLogger(AgentController.class);
    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentRepository agentRepository;

    @PostMapping(value = "/agent")
    public ResponseEntity<?> addClient(@RequestBody Agent agent) throws Exception {
        agent.setSaltValue( generateSaltString());
        agent.setHashedPassAndSalt(hash(agent.getPassword().concat(agent.getSaltValue())));

        Agent newAgent=agentService.addClient(agent);

        if(newAgent!=null) {
            LOGGER.info(MessageFormat.format("AGENT -ID:{0}-created, AGENT-EMAIL:{1}", newAgent.getId(), newAgent.getEmail()));
        } else {
            LOGGER.error(MessageFormat.format("AGENT-ID:{0}-not created, AGENT-EMAIL:{1}" , newAgent.getId(), newAgent.getEmail()));
        }

        return new ResponseEntity<>(newAgent, HttpStatus.CREATED);
    }
    @GetMapping(value = "/agent")
    public ResponseEntity<List<Agent>> getAllAgents() throws Exception {

        List<Agent> clients=agentService.getAll();

        if(clients!=null) {
            LOGGER.info("AGENT - returned all");
        } else {
            LOGGER.error("AGENT - not returned all");
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @GetMapping(value = "/agent/{idAgenta}")
    public ResponseEntity<Agent> getAgent(@PathVariable("idAgenta") Long idAgenta) throws Exception {

        Agent clients=agentService.findById(idAgenta);

        if(clients!=null) {
            LOGGER.info(MessageFormat.format("AGENT -ID:{0}-returned, AGENT-EMAIL:{1}", clients.getId(), clients.getEmail()));
        } else {
            LOGGER.error(MessageFormat.format("AGENT-ID:{0}-not returned, AGENT-EMAIL:{1}" , clients.getId(), clients.getEmail()));
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }
    @PostMapping(value = "/agent/odobri/{idAgenta}")
    public ResponseEntity<Agent> odobri(@PathVariable("idAgenta") Long idAgenta) throws Exception {

        Agent clients=agentService.findById(idAgenta);
        clients.setOdobren(true);
        agentRepository.save(clients);

        if(clients!=null) {
            LOGGER.info(MessageFormat.format("AGENT -ID:{0}-registration approved, AGENT-EMAIL:{1}", clients.getId(), clients.getEmail()));
        } else {
            LOGGER.error(MessageFormat.format("AGENT-ID:{0}-registration approved,  AGENT-EMAIL:{1}" , clients.getId(), clients.getEmail()));
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    public byte[] hash(String data) {
        //Kao hes funkcija koristi SHA-256
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] dataHash = sha256.digest(data.getBytes());
            return dataHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateSaltString(){
        byte[] array = new byte[16];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
