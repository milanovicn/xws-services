package com.example.voziloservice.Service;

import com.example.voziloservice.model.Komentar;
import com.example.voziloservice.model.StanjeKomentara;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.Repository.KomentarRepository;
import com.example.voziloservice.Repository.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Komentar create(Long idVozila, String komentar) throws Exception {
        Komentar k=new Komentar(idVozila,komentar);

        komentrRepository.save(k);
        //Vozilo zaIzmenu;
        //zaIzmenu = voziloRepository.findById(idVozila);
        //zaIzmenu.setBrojKomentara(zaIzmenu.getBrojKomentara()+1);
        //zaIzmenu=voziloRepository.update(zaIzmenu);
    return k;
    }

    @Override
    public void odbij(Long id) {
        for(Komentar k : komentrRepository.findAll()){
            if(k.getId() == id){
                k.setStanje(StanjeKomentara.ODBIJEN);
                komentrRepository.save(k);
            }
        }
    }

    @Override
    public void odobri(Long id) {
        for(Komentar k : komentrRepository.findAll()){
            if(k.getId() == id){
                k.setStanje(StanjeKomentara.ODOBREN);
                komentrRepository.save(k);
            }
        }
    }

    @Override
    public Collection<Komentar> findApprovedByIdVozila(Long id) {
        Collection<Komentar> ret = komentrRepository.findByIdVozila(id);
        for(Komentar k : ret){
            if(k.getStanje() != StanjeKomentara.ODOBREN){
                ret.remove(k);
            }
        }

        return ret;
    }
}
