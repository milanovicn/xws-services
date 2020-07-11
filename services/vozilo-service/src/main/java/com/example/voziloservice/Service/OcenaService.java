package com.example.voziloservice.Service;


import com.example.voziloservice.model.Ocena;

import java.util.Collection;

public interface OcenaService {

    public Collection<Ocena> findAll();
    public Collection<Ocena> findByIdVozila(Long id);
    public Ocena findById(Long id);
    public Ocena create(Long idVozila, int ocena) throws Exception;
    public double findAverageRate(Long id);
}
