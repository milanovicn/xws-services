package com.example.voziloservice.Controller;


import com.example.voziloservice.Service.ZauzeceVozilaService;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.model.ZauzeceVozila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class VoziloController {

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ZauzeceVozilaService zauzeceVozilaService;

    @PostMapping( value = "/addVozilo")
    public ResponseEntity<?> addVozilo(@RequestBody Vozilo vozilo) throws Exception {

        Vozilo newVozilo=voziloService.addVozilo(vozilo);

        if(newVozilo!=null)
            return new ResponseEntity<>(newVozilo, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Ne mozete dodati vise od 3 vozila", HttpStatus.CREATED);
    }


    @PostMapping( value = "/zauzmiVozilo")
    public ResponseEntity<?> zauzmiVozilo(@RequestBody ZauzeceVozila zauzeceVozila) throws Exception {

        ZauzeceVozila newZauzece=zauzeceVozilaService.addVozilo(zauzeceVozila);

        if(newZauzece!=null)
            return new ResponseEntity<>(newZauzece, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Ne radi", HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAllCars")
    public ResponseEntity<List<Vozilo>> getAllCars() throws Exception {

        List<Vozilo> cars=voziloService.getAll();

        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCar/{idVozila}")
    public ResponseEntity<Vozilo> getCarById(@PathVariable("idVozila") Long idVozila) throws Exception {

        Vozilo car=voziloService.findById(idVozila);

        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping(value = "/vratiPoKorisniku/{idIzdavaca}")
    public ResponseEntity<List<Vozilo>> getCarByIdIzavaca(@PathVariable("idIzdavaca") Long idIzdavaca) throws Exception {

        List<Vozilo> cars=voziloService.findByIznajmljivacId(idIzdavaca);

        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }
}
