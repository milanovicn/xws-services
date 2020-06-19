package com.example.voziloservice.Service;

import com.example.voziloservice.model.Komentar;
import com.example.voziloservice.model.Vozilo;

import java.util.Collection;

public interface KomentarService {

    Collection<Komentar> findAll();
    Collection<Komentar> findByIdVozila(Long id);
    Komentar findById(Long id);
    void create(Long idVozila, String komentar) throws Exception;
}
