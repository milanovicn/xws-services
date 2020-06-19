package com.example.voziloservice.Service;

import com.example.voziloservice.model.Komentar;
import com.example.voziloservice.model.StanjeKomentara;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.Repository.KomentarRepository;
import com.example.voziloservice.Repository.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class KomentarServiceImpl implements KomentarService {

    @Autowired
   private KomentarRepository komentrRepository;

    @Autowired
    private VoziloService voziloRepository;

    @Override
    public Collection<Komentar> findAll() {
        return komentrRepository.findAll();
    }

    @Override
    public Collection<Komentar> findByIdVozila(Long id) {

        return komentrRepository.findByIdVozila(id);
    }

    @Override
    public Komentar findById(Long id) {
        return komentrRepository.findById(id).orElseGet(null);
    }

    @Override
    public void create(Long idVozila, String komentar) throws Exception {
        Komentar k=new Komentar(idVozila,komentar, StanjeKomentara.OBJAVLJEN.toString());
        komentrRepository.save(k);
        //Vozilo zaIzmenu;
        //zaIzmenu = voziloRepository.findById(idVozila);
        //zaIzmenu.setBrojKomentara(zaIzmenu.getBrojKomentara()+1);
        //zaIzmenu=voziloRepository.update(zaIzmenu);

    }
}
