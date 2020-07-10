package com.example.voziloservice.Service;

import com.example.voziloservice.model.Komentar;
import com.example.voziloservice.model.Vozilo;

import java.util.Collection;

public interface KomentarService {


    public Collection<Komentar> findAll();
    public Collection<Komentar> findByIdVozila(Long id);
    public Komentar findById(Long id);
    public Komentar create(Long idVozila, String komentar) throws Exception;
    public void odbij(Long id);
    public void odobri(Long id);
    public Collection<Komentar> findApprovedByIdVozila(Long id);
    public Collection<Komentar> findApprovedByIdVozilaSoap(Long id);
}
