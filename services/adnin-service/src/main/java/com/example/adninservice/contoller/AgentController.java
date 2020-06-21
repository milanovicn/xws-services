package com.example.adninservice.contoller;


import com.example.adninservice.model.Agent;
import com.example.adninservice.model.Client;
import com.example.adninservice.repository.AgentRepository;
import com.example.adninservice.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentRepository agentRepository;

    @PostMapping(value = "/agent")
    public ResponseEntity<?> addClient(@RequestBody Agent agent) throws Exception {

        Agent newAgent=agentService.addClient(agent);

        return new ResponseEntity<>(newAgent, HttpStatus.CREATED);
    }
    @GetMapping(value = "/agent")
    public ResponseEntity<List<Agent>> getAllAgents() throws Exception {

        List<Agent> clients=agentService.getAll();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @GetMapping(value = "/agent/{idAgenta}")
    public ResponseEntity<Agent> getAgent(@PathVariable("idAgenta") Long idAgenta) throws Exception {

        Agent clients=agentService.findById(idAgenta);

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }
    @PostMapping(value = "/agent/odobri/{idAgenta}")
    public ResponseEntity<Agent> odobri(@PathVariable("idAgenta") Long idAgenta) throws Exception {

        Agent clients=agentService.findById(idAgenta);
        clients.setOdobren(true);
        agentRepository.save(clients);

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }
}
