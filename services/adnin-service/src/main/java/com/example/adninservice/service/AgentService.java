package com.example.adninservice.service;

import com.example.adninservice.model.Agent;
import com.example.adninservice.model.Client;

import java.util.List;

public interface AgentService {

    Agent addClient(Agent agent);
    List<Agent> getAll();
    Agent findById(Long id);
}
