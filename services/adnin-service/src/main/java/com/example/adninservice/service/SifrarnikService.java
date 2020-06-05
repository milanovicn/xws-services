package com.example.adninservice.service;

import com.example.adninservice.model.*;

import java.util.List;

public interface SifrarnikService {

    void addMarka(Marka m);
    void removeMarka(Long m);
    List<Marka> getAllMarka();

    void addModel(Model m);
    void removeModel(Long m);
    List<Model> getAllModel();

    void addTipMenjaca(TipMenjaca m);
    void removeTipMenjaca(Long m);
    List<TipMenjaca> getAllTipMenjaca();

    void addTipGoriva(TipGoriva m);
    void removeTipGoriva(Long m);
    List<TipGoriva> getAllTipGoriva();

    void addKlasaVozila(KlasaVozila m);
    void removeKlasaVozila(Long m);
    List<KlasaVozila> getAllKlasaVozila();


}
