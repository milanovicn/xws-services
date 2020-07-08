package com.example.voziloservice.Service;



import com.example.voziloservice.model.Zahtev;

import java.util.List;

public interface ZahtevService {
    Zahtev create(Zahtev zahtev);
    List<Zahtev> findByPodnosilac(Long id);
    List<Zahtev> findByIzdavac(Long id);
    List<Zahtev> findByIzdavacMail(String mail);
    List<Zahtev> findAll();
    void prihvatiZahtev(Long id) throws Exception;
    void otkaziZahtev(Long id);
    void obrisiZahtev(Long id);
    void platiZahtev(Long id);
    void komentarisiZahtev(Long id);
}
