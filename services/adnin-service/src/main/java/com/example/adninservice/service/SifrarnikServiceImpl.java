package com.example.adninservice.service;


import com.example.adninservice.model.*;
import com.example.adninservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SifrarnikServiceImpl implements SifrarnikService {

    @Autowired
    private MarkaRepository markaRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private KlasaVozilaRepository klasaVozilaRepository;

    @Autowired
    private TipMenjacaRepository tipMenjacaRepository;

    @Autowired
    private TipGorivaRepository tipGorivaRepository;


    @Override
    public void addMarka(Marka m) {
        Marka marka=new Marka(m.getNaziv());
        markaRepository.save(marka);
    }

    @Override
    public void removeMarka(Long m) {
        markaRepository.deleteById(m);
    }

    @Override
    public List<Marka> getAllMarka() {
        return markaRepository.findAll();
    }

    @Override
    public void addModel(Model m) {
        Model marka=new Model(m.getNaziv(),m.getMarka());
        modelRepository.save(marka);

    }

    @Override
    public void removeModel(Long m) {
        modelRepository.deleteById(m);
    }

    @Override
    public List<Model> getAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public void addTipMenjaca(TipMenjaca m) {
        TipMenjaca marka=new TipMenjaca(m.getNaziv());
        tipMenjacaRepository.save(marka);

    }

    @Override
    public void removeTipMenjaca(Long m) {
        tipMenjacaRepository.deleteById(m);
    }

    @Override
    public List<TipMenjaca> getAllTipMenjaca() {
        return tipMenjacaRepository.findAll();
    }

    @Override
    public void addTipGoriva(TipGoriva m) {
        TipGoriva marka=new TipGoriva(m.getNaziv());
        tipGorivaRepository.save(marka);
    }

    @Override
    public void removeTipGoriva(Long m) {
        tipGorivaRepository.deleteById(m);
    }

    @Override
    public List<TipGoriva> getAllTipGoriva() {
        return tipGorivaRepository.findAll();
    }

    @Override
    public void addKlasaVozila(KlasaVozila m) {
        KlasaVozila marka=new KlasaVozila(m.getNaziv());
        klasaVozilaRepository.save(marka);
    }

    @Override
    public void removeKlasaVozila(Long m) {
            klasaVozilaRepository.deleteById(m);
    }

    @Override
    public List<KlasaVozila> getAllKlasaVozila() {
        return klasaVozilaRepository.findAll();
    }
}
