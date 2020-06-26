package com.example.adninservice.contoller;


import com.example.adninservice.model.*;
import com.example.adninservice.service.SifrarnikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdminController {
    Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private SifrarnikService sifrarnikService;

    @PostMapping( value = "/addMarka")
    public void addMarka(@RequestBody Marka marka) throws Exception {

        sifrarnikService.addMarka(marka);
        if(marka!=null) {
            LOGGER.info("MARKA-ID:{0}-added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        } else {
            LOGGER.error("MARKA-ID:{0}-not added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        }

    }
    @GetMapping(value = "/getAllMarka")
    public ResponseEntity<List<Marka>> getAllMarka() throws Exception {

        List<Marka> clients=sifrarnikService.getAllMarka();

        if(clients!=null) {
            LOGGER.info("MARKA - returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        } else {
            LOGGER.error("MARKA - not returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteMarka/{id}")
    public void deleteMarka(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeMarka(id);
        if(id!=null) {
            LOGGER.info("MARKA-ID:{0}-deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        } else {
            LOGGER.error("MARKA-ID:{0}-not deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        }

    }


    @PostMapping( value = "/addModel")
    public void addModel(@RequestBody Model marka) throws Exception {

        sifrarnikService.addModel(marka);
        if(marka!=null) {
            LOGGER.info("MODEL-ID:{0}-added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        } else {
            LOGGER.error("MODEL-ID:{0}-not added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        }


    }
    @GetMapping(value = "/getAllModel")
    public ResponseEntity<List<Model>> getAllModel() throws Exception {

        List<Model> clients=sifrarnikService.getAllModel();

        if(clients!=null) {
            LOGGER.info("MODEL - returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        } else {
            LOGGER.error("MODEL - not returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteModel/{id}")
    public void deleteModel(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeModel(id);
        if(id!=null) {
            LOGGER.info("MODEL-ID:{0}-deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        } else {
            LOGGER.error("MODEL-ID:{0}-not deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        }

    }


    @PostMapping( value = "/addTipGoriva")
    public void addTipGoriva(@RequestBody TipGoriva marka) throws Exception {


        sifrarnikService.addTipGoriva(marka);

        if(marka!=null) {
            LOGGER.info("TIP-GORIVA-ID:{0}-added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        } else {
            LOGGER.error("TIP-GORIVA-ID:{0}-not added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        }
    }
    @GetMapping(value = "/getAllTipGoriva")
    public ResponseEntity<List<TipGoriva>> getAllTipGoriva() throws Exception {

        List<TipGoriva> clients=sifrarnikService.getAllTipGoriva();
        if(clients!=null) {
            LOGGER.info("TIP-GORIVA - returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        } else {
            LOGGER.error("TIP-GORIVA - not returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteTipGoriva/{id}")
    public void deleteTipGoriva(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeTipGoriva(id);
        if(id!=null) {
            LOGGER.info("TIP-GORIVA :{0}-deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        } else {
            LOGGER.error("TIP-GORIVA :{0}-not deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        }

    }

    @PostMapping( value = "/addTipMenjaca")
    public void addTipMenjaca(@RequestBody TipMenjaca marka) throws Exception {

        sifrarnikService.addTipMenjaca(marka);
        if(marka!=null) {
            LOGGER.info("TIP-MENJACA-ID:{0}-added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        } else {
            LOGGER.error("TIP-MENJACA-ID:{0}-not added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        }

    }
    @GetMapping(value = "/getAllTipMenjaca")
    public ResponseEntity<List<TipMenjaca>> getAllTipMenjaca() throws Exception {

        List<TipMenjaca> clients=sifrarnikService.getAllTipMenjaca();

        if(clients!=null) {
            LOGGER.info("TIP-MENJACA - returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        } else {
            LOGGER.error("TIP-MENJACA - not returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        }
        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteTipMenjaca/{id}")
    public void deleteTipMenjaca(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeTipMenjaca(id);
        if(id!=null) {
            LOGGER.info("TIP-MENJACA-ID:{0}-deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        } else {
            LOGGER.error("TIP-MENJACA-ID:{0}-not deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        }

    }

    @PostMapping( value = "/klasaVozila")
    public void addKlasaVozila(@RequestBody KlasaVozila marka) throws Exception {

        sifrarnikService.addKlasaVozila(marka);

        if(marka!=null) {
            LOGGER.info("KLASA-VOZILA-ID:{0}-added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        } else {
            LOGGER.error("KLASA-VOZILA-ID:{0}-not added, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", marka.getId());
        }


    }
    @GetMapping(value = "/klasaVozila")
    public ResponseEntity<List<KlasaVozila>> getAllKlasaVozila() throws Exception {

        List<KlasaVozila> clients=sifrarnikService.getAllKlasaVozila();

        if(clients!=null) {
            LOGGER.info("KLASA-VOZILA - returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        } else {
            LOGGER.error("KLASA-VOZILA - not returned all, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1");
        }

        return new ResponseEntity<>(clients, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/klasaVozila/{id}")
    public void deleteKlasaVozila(@PathVariable("id") Long id) throws Exception {

        sifrarnikService.removeKlasaVozila(id);
        if(id!=null) {
            LOGGER.info("KLASA-VOZILA-ID:{0}-deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        } else {
            LOGGER.error("KLASA-VOZILA-ID:{0}-not deleted, USER:admin, USER-EMAIL:a@gmail.com, USER-ID:1", id);
        }

    }


}
