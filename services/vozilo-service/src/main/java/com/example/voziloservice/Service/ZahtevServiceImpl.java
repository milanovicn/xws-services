package com.example.voziloservice.Service;


import com.example.voziloservice.Repository.ZahtevRepository;
import com.example.voziloservice.model.Stanje;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.model.Zahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ZahtevServiceImpl implements ZahtevService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Override
    public Zahtev create(Zahtev zahtev) {
        LocalDateTime sad=LocalDateTime.now();
        Zahtev novi=new Zahtev();
        Set<Vozilo> vozila=new HashSet<>();
        for(Vozilo v:zahtev.getVozila()){
            vozila.add(v);
        }
        novi.setDatumDo(zahtev.getDatumDo());
        novi.setDatumOd(zahtev.getDatumOd());
        novi.setPodnosilac(zahtev.getPodnosilac());
        novi.setIzdavac(zahtev.getIzdavac());
        novi.setIzdavacMail(zahtev.getIzdavacMail());
        novi.setStanje(Stanje.PENDING);
                    novi.setVozila(vozila);
        //Zahtev novi=new Zahtev(zahtev.getVozila(),zahtev.getDatumOd(),zahtev.getDatumDo(),zahtev.getPodnosilac(),zahtev.getIzdavac(),zahtev.getIzdavacMail());
        novi.setVremeKreiranja(sad);

        zahtevRepository.save(novi);
        return novi;
    }



    @Override
    public List<Zahtev> findByPodnosilac(Long id) {
        return zahtevRepository.findByPodnosilac(id);
    }

    @Override
    public List<Zahtev> findByIzdavac(Long id) {
        return zahtevRepository.findByIzdavac(id);
    }

    @Override
    public List<Zahtev> findByIzdavacMail(String mail) {
        return zahtevRepository.findByIzdavacMail(mail);
    }

    @Override
    public List<Zahtev> findAll() {
        return zahtevRepository.findAll();
    }

    @Override
    public void prihvatiZahtev(Long id) throws Exception {
        Zahtev zaIzemnu=zahtevRepository.findById(id).orElseGet(null);

        zaIzemnu.setStanje(Stanje.RESERVED);
        zaIzemnu.setVremeOdobrenja(LocalDateTime.now());
        zahtevRepository.save(zaIzemnu);

    }

   /* @Scheduled(initialDelayString = "3000", fixedDelayString = "30000")
    public void proveraUplate() throws Exception {
        LocalDateTime trenutnoVreme=LocalDateTime.now();
        List<Zahtev> zahtevi=zahtevRepository.findAll();
        for(Zahtev zahtev:zahtevi) {
            if (zahtev.getStanje().equals("RESERVED")) {
                if (trenutnoVreme.isAfter(zahtev.getVremeOdobrenja().plusHours(12)) && !zahtev.getStanje().equals("PAID")) {
                    zahtev.setStanje(Stanje.CANCELED);
                    zahtevRepository.save(zahtev);
                }else if (zahtev.getStanje().equals("PAID")) {
                    List<Zahtev> zahteviNovi = zahtevRepository.findByIdVozila(zahtev.getIdVozila());
                    for (Zahtev z : zahteviNovi) {
                        if (z.getDatumOd().isBefore(zahtev.getDatumDo()) && zahtev.getDatumOd().isBefore(z.getDatumDo())) {
                            z.setStanje(Stanje.CANCELED);
                            zahtevRepository.save(z);
                        }
                    }
                }

            }
        }




    }
*/
    @Scheduled(initialDelayString = "3000", fixedDelayString = "30000")
    public void proveriDaliJeProslo24() throws Exception {
        List<Zahtev> zahtevi=zahtevRepository.findAll();
        LocalDateTime trenutnoVreme=LocalDateTime.now();
        for(Zahtev z:zahtevi){
            if(z.getVremeKreiranja().plusHours(24).isBefore(trenutnoVreme) && z.getStanje().equals("PENDING")){
                z.setStanje(Stanje.CANCELED);
                zahtevRepository.save(z);
            }
        }


        }

    @Override
    public void otkaziZahtev(Long id) {
        Zahtev zaIzemnu=zahtevRepository.findById(id).orElseGet(null);

        zaIzemnu.setStanje(Stanje.CANCELED);
        zahtevRepository.save(zaIzemnu);

    }

    @Override
    public void obrisiZahtev(Long id) {
        zahtevRepository.deleteById(id);
    }

    @Override
    public void platiZahtev(Long id) {
        Zahtev zaIzemnu=zahtevRepository.findById(id).orElseGet(null);

        zaIzemnu.setStanje(Stanje.PAID);
        zahtevRepository.save(zaIzemnu);

    }

    @Override
    public void komentarisiZahtev(Long id) {
        Zahtev zaIzemnu=zahtevRepository.findById(id).orElseGet(null);

        zaIzemnu.setStanje(Stanje.REVIEWED);
        zahtevRepository.save(zaIzemnu);

    }
    @Scheduled(initialDelayString = "3000", fixedDelayString = "30000")
    public void zavrsenoIznajmljivanje() throws Exception {
        List<Zahtev> zahtevi=zahtevRepository.findAll();
        LocalDateTime trenutnoVreme=LocalDateTime.now();
        for(Zahtev z:zahtevi){
            if(z.getDatumDo().isBefore(trenutnoVreme) && z.getStanje().equals("PAID")){
                z.setStanje(Stanje.WAITING_REVIEW);
                zahtevRepository.save(z);
            }
        }


    }


}
