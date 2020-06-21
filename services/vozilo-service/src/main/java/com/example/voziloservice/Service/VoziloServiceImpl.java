package com.example.voziloservice.Service;

import com.example.voziloservice.Client.UserClient;

import com.example.voziloservice.Repository.VoziloRepository;
import com.example.voziloservice.model.Vozilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoziloServiceImpl implements VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private UserClient userClient;

   /* @Autowired
    private SearchClient searchClient;*/


    @Override
    public Vozilo addVozilo(Vozilo vozilo) {

    if(userClient.chackNuberOfCars(vozilo.getIznajmljivacId())==true){
        Vozilo newVozilo=new Vozilo(vozilo.getMarka(),vozilo.getModel(),vozilo.getTipGoriva(),vozilo.getTipMenjaca(),
                vozilo.getKlasaVozila(),vozilo.getCenovnikId(),vozilo.getRedjenaKilometraza(),vozilo.getOgranicenaKilometraza(),
                vozilo.isCDWProtection(),vozilo.getBrojSedistaDeca(),vozilo.getVaziOd(),vozilo.getVaziDo(),vozilo.getMesto(),vozilo.getIznajmljivacId());

        voziloRepository.save(newVozilo);
        userClient.uvecajBrojOglasa(newVozilo.getIznajmljivacId());
     /*   String CWDProtection="NE";
        if(newVozilo.isCDWProtection())
            CWDProtection="DA";

        String datum1;
        String datum2;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        datum1=formatter.format(newVozilo.getVaziOd());
        datum2=formatter.format(newVozilo.getVaziDo());
        searchClient.addVozilo(newVozilo.getMesto(),newVozilo.getMarka(),newVozilo.getModel(),newVozilo.getTipMenjaca(),newVozilo.getTipGoriva(),CWDProtection,datum1,
                datum2,newVozilo.getBrojSedistaDeca(),newVozilo.getId());*/
        return newVozilo;
    }
    else
        return  null;
    }

    @Override
    public List<Vozilo> getAll() {
        return voziloRepository.findAll();
    }

    @Override
    public List<Vozilo> findByIznajmljivacId(Long id) {
        return voziloRepository.findByIznajmljivacId(id);
    }

    @Override
    public Vozilo findById(Long id) {
        return voziloRepository.findById(id).orElse(null);
    }
}
