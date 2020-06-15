package com.example.zahtevsevices.sevices;

import com.example.zahtevsevices.model.Zahtev;

import java.util.List;

public interface ZahtevService {
    Zahtev create(Zahtev zahtev);
    List<Zahtev> findByPodnosilac(Long id);
    List<Zahtev> findByIzdavac(Long id);
    List<Zahtev> findAll();
    void prihvatiZahtev(Long id) throws Exception;
    void otkaziZahtev(Long id);
}
