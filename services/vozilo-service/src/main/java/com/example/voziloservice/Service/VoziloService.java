package com.example.voziloservice.Service;

import com.example.voziloservice.model.Vozilo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;


public interface VoziloService {
    Vozilo addVozilo(Vozilo vozilo,Long id);
    List<Vozilo> getAll();
    Vozilo findById(Long id);

}
