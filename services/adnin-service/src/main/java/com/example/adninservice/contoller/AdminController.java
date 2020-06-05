package com.example.adninservice.contoller;


import com.example.adninservice.model.*;
import com.example.adninservice.service.SifrarnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private SifrarnikService sifrarnikService;

    @PostMapping( value = "/addMarka")
    public void addMarka(@RequestBody Marka marka) throws Exception {

        sifrarnikService.addMarka(marka);


    }
    @GetMapping(value = "/getAllMarka")
    public ResponseEntity<List<Marka>> getAllMarka() throws Exception {

        List<Marka> clients=sifrarnikService.getAllMarka();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteMarka/{id}")
    public void deleteMarka(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeMarka(id);

    }


    @PostMapping( value = "/addModel")
    public void addModel(@RequestBody Model marka) throws Exception {

        sifrarnikService.addModel(marka);


    }
    @GetMapping(value = "/getAllModel")
    public ResponseEntity<List<Model>> getAllModel() throws Exception {

        List<Model> clients=sifrarnikService.getAllModel();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteModel/{id}")
    public void deleteModel(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeModel(id);

    }


    @PostMapping( value = "/addTipGoriva")
    public void addTipGoriva(@RequestBody TipGoriva marka) throws Exception {

        sifrarnikService.addTipGoriva(marka);


    }
    @GetMapping(value = "/getAllTipGoriva")
    public ResponseEntity<List<TipGoriva>> getAllTipGoriva() throws Exception {

        List<TipGoriva> clients=sifrarnikService.getAllTipGoriva();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteTipGoriva/{id}")
    public void deleteTipGoriva(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeTipGoriva(id);

    }

    @PostMapping( value = "/addTipMenjaca")
    public void addTipMenjaca(@RequestBody TipMenjaca marka) throws Exception {

        sifrarnikService.addTipMenjaca(marka);


    }
    @GetMapping(value = "/getAllTipMenjaca")
    public ResponseEntity<List<TipMenjaca>> getAllTipMenjaca() throws Exception {

        List<TipMenjaca> clients=sifrarnikService.getAllTipMenjaca();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteTipMenjaca/{id}")
    public void deleteTipMenjaca(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeTipMenjaca(id);

    }

    @PostMapping( value = "/klasaVozila")
    public void addKlasaVozila(@RequestBody KlasaVozila marka) throws Exception {

        sifrarnikService.addKlasaVozila(marka);


    }
    @GetMapping(value = "/klasaVozila")
    public ResponseEntity<List<KlasaVozila>> getAllKlasaVozila() throws Exception {

        List<KlasaVozila> clients=sifrarnikService.getAllKlasaVozila();

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/klasaVozila/{id}")
    public void deleteKlasaVozila(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeKlasaVozila(id);

    }


}
