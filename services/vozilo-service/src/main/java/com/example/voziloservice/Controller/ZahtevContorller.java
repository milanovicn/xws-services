package com.example.voziloservice.Controller;



import com.example.voziloservice.Client.UserClient;
import com.example.voziloservice.Service.ZahtevService;
import com.example.voziloservice.model.PretragaZauzecaDTO;
import com.example.voziloservice.model.Zahtev;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class ZahtevContorller {
    Logger LOGGER = LoggerFactory.getLogger(ZahtevContorller.class);

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private UserClient userClient;

    @PostMapping(value = "/zahtev")
    public ResponseEntity<?> napraviZahtev(@RequestBody Zahtev zahtev) throws Exception {

        Zahtev novi = zahtevService.create(zahtev);
        //TODO: uid ako moze validacija da budu brojevi (a jedinstvenost po bazi?)
        if(novi!=null) {
            LOGGER.info(MessageFormat.format("ZAHTEV: ZAHTEV-ID:{0}-created, PODNOSILAC-ID:{1}", novi.getId(), novi.getPodnosilac()));
        } else {
            LOGGER.error(MessageFormat.format("ZAHTEV: ZAHTEV-ID:{0}-not created, PODNOSILAC-ID:{1}", novi.getId(), novi.getPodnosilac()));
        }



        return new ResponseEntity<>(novi, HttpStatus.CREATED);
    }


    @GetMapping(value = "/zahtev")
    public ResponseEntity<?> vratiZahtev() throws Exception {


       List<Zahtev> zahtevi=zahtevService.findAll();
        if(zahtevi!=null) {
            LOGGER.info(MessageFormat.format("ZAHTEV: returned all, ZAHTEVI-SIZE:{0}", zahtevi.size()) );
        } else {
            LOGGER.error("ZAHTEV:not returned all");
        }

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }
    @GetMapping(value = "/zahtev/{idPodnosioca}")
    public ResponseEntity<?> vratiZahtevePodnosioca(@PathVariable("idPodnosioca") Long idPodnosioca) throws Exception {


        List<Zahtev> zahtevi=zahtevService.findByPodnosilac(idPodnosioca);
        if(zahtevi!=null) {
            LOGGER.info(MessageFormat.format("ZAHTEV: returned all by id podnosioca, ZAHTEVI-SIZE:{0},ID-PODNOSIOCA:{1}", zahtevi.size(), idPodnosioca ));
        } else {
            LOGGER.error(MessageFormat.format("ZAHTEV:not returned all by id podnosioca, ID-PODNOSIOCA:{0}", idPodnosioca));
        }

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }

    @GetMapping(value = "/zahtev/izdavalac/{idIzdavaoca}")
    public ResponseEntity<?> vratiZahtevIzdavaoca(@PathVariable("idIzdavaoca") Long idIzdavaoca) throws Exception {


        List<Zahtev> zahtevi=zahtevService.findByIzdavac(idIzdavaoca);

        if(zahtevi!=null) {
            LOGGER.info(MessageFormat.format("ZAHTEV: returned all by id izdavaoca, ZAHTEVI-SIZE:{0},ID-IZDAVALAC:{1}", zahtevi.size(), idIzdavaoca) );
        } else {
            LOGGER.error(MessageFormat.format("ZAHTEV:not returned all by id izdavaoca, ID-IZDVALAC:{0}", idIzdavaoca));
        }

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }

    @GetMapping(value = "/zahtev/izdavalacMail/{mail}")
    public ResponseEntity<?> vratiZahtevIzdavaocaByMail(@PathVariable("mail") String  mail) throws Exception {


        List<Zahtev> zahtevi=zahtevService.findByIzdavacMail(mail);

        if(zahtevi!=null) {
            LOGGER.info(MessageFormat.format("ZAHTEV: returned all by id izdavaoca, ZAHTEVI-SIZE:{0},ID-IZDAVALAC:{1}", zahtevi.size(), mail) );
        } else {
            LOGGER.error(MessageFormat.format("ZAHTEV:not returned all by id izdavaoca, ID-IZDVALAC:{0}", mail));
        }

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }

    @GetMapping(value = "/zahtev/izdavalacMailAG/") // za agenta kad gadja ovaj mikroservis
    public ResponseEntity<?> vratiZahtevIzdavaocaByMailAG() throws Exception {

        String mail = userClient.getEmailUlogovanogAgenta();

        List<Zahtev> zahtevi=zahtevService.findByIzdavacMail(mail);

        if(zahtevi!=null) {
            LOGGER.info(MessageFormat.format("ZAHTEV: returned all by id izdavaoca za AG, ZAHTEVI-SIZE:{0},ID-IZDAVALAC:{1}", zahtevi.size(), mail) );
        } else {
            LOGGER.error(MessageFormat.format("ZAHTEV:not returned all by id izdavaoca za AG, ID-IZDVALAC:{0}", mail));
        }

        return new ResponseEntity<>(zahtevi, HttpStatus.CREATED);
    }


    @PostMapping(value = "/odobri/{idZahteva}")
    public void odobriZahtev(@PathVariable("idZahteva") Long idZahteva) throws Exception {


        zahtevService.prihvatiZahtev(idZahteva);


    }

    @PostMapping(value = "/odbaci/{idZahteva}")
    public void odbaciZahtev(@PathVariable("idZahteva") Long idZahteva) throws Exception {


        zahtevService.otkaziZahtev(idZahteva);

            LOGGER.info(MessageFormat.format("ZAHTEV: otkazan, ZAHTEVI-ID:{0}", idZahteva ));




    }

    //@CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping(value = "/odobriAG/{idZahteva}")
//    public void odobriZahtevAG(@PathVariable("idZahteva") Long idZahteva, @RequestBody Zahtev zahtev) throws Exception {
//        zahtevService.prihvatiZahtev(idZahteva);
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping(value = "/odbaciAG/{idZahteva}")
//    public void odbaciZahtevAG(@PathVariable("idZahteva") Long idZahteva, @RequestBody Zahtev zahtev) throws Exception {
//        zahtevService.otkaziZahtev(idZahteva);
//        LOGGER.info(MessageFormat.format("ZAHTEV: otkazan, ZAHTEVI-ID:{0}", idZahteva ));
//    }

    @DeleteMapping(value = "/otkazi/{idZahteva}")
    public void otkaziZahtev(@PathVariable("idZahteva") Long idZahteva) throws Exception {


        zahtevService.otkaziZahtev(idZahteva);

        LOGGER.info(MessageFormat.format("ZAHTEV: otkazan, ZAHTEVI-ID:{0}", idZahteva ));




    }

    @PostMapping(value = "/plati/{idZahteva}")
    public void platiZahtev(@PathVariable("idZahteva") Long idZahteva) throws Exception {


        zahtevService.platiZahtev(idZahteva);
        LOGGER.info(MessageFormat.format("ZAHTEV: placen, ZAHTEVI-ID:{0}", idZahteva ));

    }

    //promena statusa zahteva u REVIEWED
    @PostMapping(value = "/komentarisi/{idZahteva}")
    public void komentarisiZahtev(@PathVariable("idZahteva") Long idZahteva) throws Exception {


        zahtevService.komentarisiZahtev(idZahteva);
        LOGGER.info(MessageFormat.format("ZAHTEV: komentarisan, ZAHTEVI-ID:{0}", idZahteva ));


    }

    @PostMapping(value = "/checkAvailability/")
    public ResponseEntity<?> checkAvailability(@RequestBody PretragaZauzecaDTO pzDTO) throws Exception {


        List<Long> ids = zahtevService.checkAvailability(pzDTO);


        if(!ids.isEmpty()) {
            return new ResponseEntity<>(ids, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Nista nije pronadjeno :(", HttpStatus.CREATED);
        }


    }


}
