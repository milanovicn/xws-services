package com.example.zahtevsevices.sevices;

import com.example.zahtevsevices.model.Stanje;
import com.example.zahtevsevices.model.Zahtev;
import com.example.zahtevsevices.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZahtevServiceImpl implements ZahtevService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Override
    public Zahtev create(Zahtev zahtev) {
        LocalDateTime sad=LocalDateTime.now();
        Zahtev novi=new Zahtev(zahtev.getIdVozila(),zahtev.getDatumOd(),zahtev.getDatumDo(),zahtev.getPodnosilac(),zahtev.getIzdavac());
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
                    zahtev.setStanje(Stanje.CANDELED);
                    zahtevRepository.save(zahtev);
                }else if (zahtev.getStanje().equals("PAID")) {
                    List<Zahtev> zahteviNovi = zahtevRepository.findByIdVozila(zahtev.getIdVozila());
                    for (Zahtev z : zahteviNovi) {
                        if (z.getDatumOd().isBefore(zahtev.getDatumDo()) && zahtev.getDatumOd().isBefore(z.getDatumDo())) {
                            z.setStanje(Stanje.CANDELED);
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
                z.setStanje(Stanje.CANDELED);
                zahtevRepository.save(z);
            }
        }


        }





    @Override
    public void otkaziZahtev(Long id) {
        Zahtev zaIzemnu=zahtevRepository.findById(id).orElseGet(null);

        zaIzemnu.setStanje(Stanje.CANDELED);
        zahtevRepository.save(zaIzemnu);

    }
}
