package com.example.adninservice.repository;

import com.example.adninservice.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Long> {
}
