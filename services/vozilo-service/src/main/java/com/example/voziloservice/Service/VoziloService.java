package com.example.voziloservice.Service;

import com.example.voziloservice.model.Vozilo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;


public interface VoziloService {
    Vozilo addVozilo(Vozilo vozilo,String rola);
    List<Vozilo> getAll();
    List<Vozilo> findByIznajmljivacId(Long id);
    Vozilo findById(Long id);

}
