package com.example.adninservice.service;

import com.example.adninservice.model.Cenovnik;

import java.util.Collection;

public interface CenovnikService {

    public Collection<Cenovnik> findAll();
    public void removeCenovnik(Long id);
    public Cenovnik addNewCenovnik(Cenovnik newCenovnik);
    public Cenovnik getById(Long id);
    public Collection<Cenovnik> findAllByAuthorId(Long id);
}
