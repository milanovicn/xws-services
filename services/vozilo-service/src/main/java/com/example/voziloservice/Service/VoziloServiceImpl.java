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


    @Override
    public Vozilo addVozilo(Vozilo vozilo,Long id) {

    if(userClient.chackNuberOfCars(id)==true){
        Vozilo newVozilo=new Vozilo(vozilo.getMarka(),vozilo.getModel(),vozilo.getTipGoriva(),vozilo.getTipMenjaca(),
                vozilo.getKlasaVozila(),vozilo.getCenovnikId(),vozilo.getRedjenaKilometraza(),vozilo.getOgranicenaKilometraza(),
                vozilo.isCDWProtection(),vozilo.getBrojSedistaDeca(),vozilo.getVaziOd(),vozilo.getVaziDo(),vozilo.getMesto(),id);

        voziloRepository.save(newVozilo);
        userClient.uvecajBrojOglasa(id);
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
    public Vozilo findById(Long id) {
        return voziloRepository.findById(id).orElse(null);
    }
}
