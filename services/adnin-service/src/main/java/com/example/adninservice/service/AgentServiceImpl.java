package com.example.adninservice.service;

import com.example.adninservice.model.Agent;
import com.example.adninservice.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

   /* @Autowired
    private JavaMailSender javaMailSender;
*/
    @Override
    public Agent addClient(Agent agent) {
        Agent agent1=new Agent(agent.getNazivFirme(),agent.getEmail(),agent.getPassword(),agent.getAdresa());
        //agent1.setSaltValue(agent.getSaltValue());
        //agent1.setHashedPassAndSalt(agent.getHashedPassAndSalt());
        agentRepository.save(agent1);

       /* SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(agent1.getEmail());
        //mail.setFrom("isaPSW1@gmail.com");
        mail.setSubject("Registracija");
        mail.setText("Pozdrav" + ",\nUspesno ste se registrovali na nas sistem:");
        javaMailSender.send(mail);*/
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

    @Override
    public Agent findByEmail(String email) {
        return agentRepository.findByEmail(email);
    }
}
