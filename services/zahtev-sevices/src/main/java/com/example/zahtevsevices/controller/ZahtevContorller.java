package com.example.zahtevsevices.controller;


import com.example.zahtevsevices.model.Zahtev;
import com.example.zahtevsevices.sevices.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZahtevContorller {

    @Autowired
    private ZahtevService zahtevService;

    @PostMapping(value = "/zahtev")
    public ResponseEntity<?> napraviZahtev(@RequestBody Zahtev zahtev) throws Exception {

        Zahtev novi = zahtevService.create(zahtev);
        //TODO: uid ako moze validacija da budu brojevi (a jedinstvenost po bazi?)

        return new ResponseEntity<>(novi, HttpStatus.CREATED);
    }

    @GetMapping(value = "/zahtev")
    public ResponseEntity<?> vratiZahtev() throws Exception {


       List<Zahtev> zahtevi=zahtevService.findAll();

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }
    @GetMapping(value = "/zahtev/{idPodnosioca}")
    public ResponseEntity<?> vratiZahtevePodnosioca(@PathVariable("idPodnosioca") Long idPodnosioca) throws Exception {


        List<Zahtev> zahtevi=zahtevService.findByPodnosilac(idPodnosioca);

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }

    @GetMapping(value = "/zahtev/izdavalac/{idIzdavaoca}")
    public ResponseEntity<?> vratiZahtevIzdavaoca(@PathVariable("idIzdavaoca") Long idIzdavaoca) throws Exception {


        List<Zahtev> zahtevi=zahtevService.findByIzdavac(idIzdavaoca);

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }
    @PostMapping(value = "/odobri/{idZahteva}")
    public void odobriZahtev(@PathVariable("izZahteva") Long izZahteva) throws Exception {


        zahtevService.prihvatiZahtev(izZahteva);


    }

    @PostMapping(value = "/otkazi/{idZahteva}")
    public void otkaziZahtev(@PathVariable("izZahteva") Long izZahteva) throws Exception {


        zahtevService.otkaziZahtev(izZahteva);


    }
}
