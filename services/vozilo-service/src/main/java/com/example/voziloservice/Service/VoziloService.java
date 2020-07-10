package com.example.voziloservice.Service;

import com.example.voziloservice.model.Vozilo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;


public interface VoziloService {
    Vozilo addVozilo(Vozilo vozilo,String rola);
    List<Vozilo> getAll();
    List<Vozilo> sortiraj(List<Vozilo> vozila,String sortBy);
    List<Vozilo> findByIznajmljivacId(Long id);
    List<Vozilo> findByIznajmljivacMail(String mail);
    Vozilo findById(Long id);

    Vozilo findByPomId(Long id);
}
