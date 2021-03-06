package com.example.zahtevsevices.sevices;

import com.example.zahtevsevices.model.Zahtev;

import java.util.List;

public interface ZahtevService {
    Zahtev create(Zahtev zahtev);
    List<Zahtev> createBundle(List<Zahtev> zahtevi);
    List<Zahtev> findByPodnosilac(Long id);
    List<Zahtev> findByIzdavac(Long id);
    List<Zahtev> findByIzdavacMail(String mail);
    List<Zahtev> findAll();
    void prihvatiZahtev(Long id) throws Exception;
    void otkaziZahtev(Long id);
    void platiZahtev(Long id);
    void komentarisiZahtev(Long id);
}
