package com.example.adninservice.service;

import com.example.adninservice.model.Client;
import com.example.adninservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Client addClient(Client client) {

        Client newClient=new Client(client.getIme(),client.getPrezime(),client.getEmail(),client.getPassword(),
                client.getRola(),client.getBrojTelefona());
        newClient=clientRepository.save(newClient);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(newClient.getEmail());
        mail.setFrom("mglukic@gmail.com");
        mail.setSubject("Registracija");
        mail.setText("Pozdrav" + ",\nUspesno ste se registrovali na nas sistem:");
        javaMailSender.send(mail);
        return newClient;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients=new ArrayList<>();
        for(Client c : clientRepository.findAll()){
            clients.add(c);
        }
        return clients;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client fingBuEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void blockClient(String email) {
        Client clint=clientRepository.findByEmail(email);
        clint.setBlokiran(true);
        clientRepository.save(clint);

    }

    @Override
    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public boolean proveriBrojOglasa(Long id) {
        Client client=clientRepository.findById(id).orElse(null);
        if(client.getBrojObjavljenihOglasa()<4)
            return true;
        else
            return false;
    }

    @Override
    public void povecajBrojOglasa(Long id) {
        Client client=clientRepository.findById(id).orElse(null);
        client.setBrojObjavljenihOglasa(client.getBrojObjavljenihOglasa()+1);
        clientRepository.save(client);
    }
}
