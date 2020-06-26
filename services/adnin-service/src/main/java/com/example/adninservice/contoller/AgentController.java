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

import java.util.List;

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

        Agent newAgent=agentService.addClient(agent);

        if(newAgent!=null) {
            LOGGER.info("AGENT -ID:{0}-created, AGENT-EMAIL:{1}", newAgent.getId(), newAgent.getEmail());
        } else {
            LOGGER.error("AGENT-ID:{0}-not created, AGENT-EMAIL:{1}" , newAgent.getId(), newAgent.getEmail());
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
            LOGGER.info("AGENT -ID:{0}-returned, AGENT-EMAIL:{1}", clients.getId(), clients.getEmail());
        } else {
            LOGGER.error("AGENT-ID:{0}-not returned, AGENT-EMAIL:{1}" , clients.getId(), clients.getEmail());
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }
    @PostMapping(value = "/agent/odobri/{idAgenta}")
    public ResponseEntity<Agent> odobri(@PathVariable("idAgenta") Long idAgenta) throws Exception {

        Agent clients=agentService.findById(idAgenta);
        clients.setOdobren(true);
        agentRepository.save(clients);

        if(clients!=null) {
            LOGGER.info("AGENT -ID:{0}-registration approved, AGENT-EMAIL:{1}", clients.getId(), clients.getEmail());
        } else {
            LOGGER.error("AGENT-ID:{0}-registration approved,  AGENT-EMAIL:{1}" , clients.getId(), clients.getEmail());
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }
}
