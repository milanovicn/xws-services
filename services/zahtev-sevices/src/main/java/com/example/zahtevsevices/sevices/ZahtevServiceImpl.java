package com.example.zahtevsevices.sevices;

import com.example.zahtevsevices.model.Stanje;
import com.example.zahtevsevices.model.Zahtev;
import com.example.zahtevsevices.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevServiceImpl implements ZahtevService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Override
    public Zahtev create(Zahtev zahtev) {
        LocalDateTime sad=LocalDateTime.now();
        Zahtev novi=new Zahtev(zahtev.getIdVozila(),zahtev.getDatumOd(),zahtev.getDatumDo(),zahtev.getPodnosilac(),zahtev.getIzdavac(),zahtev.getIzdavacMail());
        novi.setVremeKreiranja(sad);

        zahtevRepository.save(novi);
        return novi;
    }

    @Override
    public List<Zahtev> createBundle(List<Zahtev> zahtevi) {
        List<Zahtev> noviZahtevi=new ArrayList<>();
        LocalDateTime sad=LocalDateTime.now();
        for(Zahtev z:zahtevi){
            Zahtev novi=new Zahtev(z.getIdVozila(),z.getDatumOd(),z.getDatumDo(),z.getPodnosilac(),z.getIzdavac(),z.getIzdavacMail());
            novi.setVremeKreiranja(sad);
            zahtevRepository.save(novi);
            noviZahtevi.add(novi);
        }
        return noviZahtevi;
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

    @Scheduled(initialDelayString = "3000", fixedDelayString = "30000")
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
