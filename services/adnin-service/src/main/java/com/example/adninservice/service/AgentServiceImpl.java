package com.example.adninservice.service;

import com.example.adninservice.model.Agent;
import com.example.adninservice.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent addClient(Agent agent) {
        Agent agent1=new Agent(agent.getNazivFirme(),agent.getEmail(),agent.getPassword(),agent.getAdresa());
        agentRepository.save(agent1);
        return agent1;
    }

    @Override
    public List<Agent> getAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent findById(Long id) {
        return agentRepository.findById(id).orElseGet(null);
    }
}
