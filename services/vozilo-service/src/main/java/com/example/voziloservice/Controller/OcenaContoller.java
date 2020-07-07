package com.example.voziloservice.Controller;


import com.example.voziloservice.Service.OcenaService;
import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.model.Ocena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class OcenaContoller {

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private OcenaService ocenaService;

    //dodavanje komentara
    @PostMapping( value = "/ocena/{idVozila}")
    public ResponseEntity<?> addOcena(@PathVariable("idVozila") Long idVozila, @RequestBody int ocena) throws Exception {

        Ocena novaOcena =ocenaService.create(idVozila, ocena);

        if(novaOcena!=null) {
            return new ResponseEntity<>(novaOcena, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Nije dodata ocena", HttpStatus.NO_CONTENT);
    }

    //get all
    @GetMapping( value = "/ocena")
    public Collection<Ocena> ucitajSve() {

        return ocenaService.findAll();
    }


    @GetMapping(value = "/ocena/srednja/{idVozila}")
    public double vratiSrednju(@PathVariable Long idVozila) {

        Collection<Ocena> ocene=ocenaService.findByIdVozila(idVozila);
        ArrayList<Integer> oceneBroj=new ArrayList<>();
        for(Ocena o:ocene){
            oceneBroj.add(o.getOcena());
        }
        int zbir=0;
        for(int o:oceneBroj){
            zbir+=o;
        }
        return zbir/oceneBroj.size();
    }



    @GetMapping(value = "/ocena/vozilo/{idVozila}")
    public Collection<Ocena> vratiKometareVozila(@PathVariable Long idVozila) {


        return ocenaService.findByIdVozila(idVozila);
    }
}
