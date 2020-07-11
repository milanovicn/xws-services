package com.example.voziloservice.Service;

import com.example.voziloservice.Repository.OcenaRepository;
import com.example.voziloservice.model.Ocena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OcenaServiceImpl implements OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    @Override
    public Collection<Ocena> findAll() {
        return ocenaRepository.findAll();
    }

    @Override
    public Collection<Ocena> findByIdVozila(Long id) {
        return ocenaRepository.findByIdVozila(id);
    }

    @Override
    public Ocena findById(Long id) {
        return ocenaRepository.findById(id).orElseGet(null);
    }

    @Override
    public Ocena create(Long idVozila, int ocena) throws Exception {
        Ocena o=new Ocena(idVozila,ocena);
        o=ocenaRepository.save(o);
        return o;
    }
}
