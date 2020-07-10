package com.example.voziloservice.Controller;

import com.example.voziloservice.Service.ZahtevService;
import com.example.voziloservice.model.Zahtev;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class ZahtevControllerAG {

    Logger LOGGER = LoggerFactory.getLogger(ZahtevControllerAG.class);

    @Autowired
    private ZahtevService zahtevService;

    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/odobriAG/{idZahteva}")
    public ResponseEntity<?> odobriZahtevAG(@PathVariable("idZahteva") Long idZahteva) throws Exception {
        zahtevService.prihvatiZahtev(idZahteva);
        return new ResponseEntity<>("Uspesno odobren zahtev od strane agenta!!", HttpStatus.CREATED);
    }

    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/odbaciAG/{idZahteva}")
    public ResponseEntity<?> odbaciZahtevAG(@PathVariable("idZahteva") Long idZahteva) throws Exception {
        zahtevService.otkaziZahtev(idZahteva);
        return new ResponseEntity<>("Uspesno odbijen zahtev od strane agenta!!", HttpStatus.CREATED);
    }


}
