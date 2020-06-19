package com.example.voziloservice.Controller;
import com.example.voziloservice.Service.KomentarService;
import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.model.Komentar;
import com.example.voziloservice.model.Vozilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class KomentarController {

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private KomentarService komentarService;

    //dodavanje komentara
    @PostMapping( value = "/comment/{idVozila}")
    public ResponseEntity<?> addComment(@PathVariable("idVozila") Long idVozila, @RequestBody String tekstKomentara) throws Exception {

        Komentar noviKom =komentarService.create(idVozila, tekstKomentara);

        if(noviKom!=null)
            return new ResponseEntity<>(noviKom, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Nije dodat komentar", HttpStatus.NO_CONTENT);
    }

    //get all
    @GetMapping( value = "/comment/")
    public Collection<Komentar> ucitajSve() {

        return komentarService.findAll();
    }


    //odbijanje komentara na osnovu id
    @PostMapping( value = "/comment/reject/{id}")
    public ResponseEntity<?> odbijKomentar(@PathVariable("id") Long id) throws Exception {
            komentarService.odbij(id);

            return new ResponseEntity<>(HttpStatus.OK);

    }

    //odobravanje komentara na osnovu id
    @PostMapping( value = "/comment/approve/{id}")
    public ResponseEntity<?> odobriKomentar(@PathVariable("id") Long id) throws Exception {
        komentarService.odobri(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //vraca odobrene komentare na osnovu id vozila
    @GetMapping(value = "/comment/{idVozila}")
    public Collection<Komentar> vratiKometareVozila(@PathVariable Long idVozila) {


        return komentarService.findApprovedByIdVozila(idVozila);
    }



}
