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
    private VoziloService voziloService;

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


    @Override
    public Collection<Komentar> findApprovedByIdVozilaSoap(Long id) {
//        Collection<Komentar> tempAgentski = new ArrayList<>();
//
//        Collection<Komentar> temp = komentrRepository.findByIdVozila(id);
//
//        for (Komentar komentar : temp) {
//            if (checkForAgent(komentar.getIdVozila())) { // proverava dal je pomId != 0 tj. dal je vozilo od agenta
//                tempAgentski.add(komentar);
//            }
//        }
//
//        for (Komentar komentar : tempAgentski) {
//            if (komentar.getIdVozila().equals(id)) {
//                ret.add(komentar);
//            }
//        }
        Vozilo v = voziloService.findByPomId(id);
        Collection<Komentar> temp = komentrRepository.findByIdVozila(v.getId());

        for(Komentar k : temp){
            if(k.getStanje() != StanjeKomentara.ODOBREN){
                temp.remove(k);
            }
        }

        return temp;
    }

    @Override
    public Komentar createAG(Komentar komentar) {

        Komentar k = new Komentar();
        k.setIdVozila(komentar.getIdVozila());
        k.setStanje(komentar.getStanje());
        k.setKomentar(komentar.getKomentar());
        k = komentrRepository.save(k);
        return k;
    }

//    private boolean checkForAgent(Long idVozila) {
//        Vozilo v = voziloService.findById(idVozila);
//        if (v.getPomId().equals(0L)) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
