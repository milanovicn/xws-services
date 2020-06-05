package com.example.adninservice.service;

import com.example.adninservice.model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);
    List<Client> getAll();
    Client findById(Long id);
    Client fingBuEmail(String email);
    void blockClient(String emaail);
    void removeClient(Long id);
    boolean proveriBrojOglasa(Long id);
    void povecajBrojOglasa(Long id);

}
