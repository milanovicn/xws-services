package com.example.voziloservice.Service;

import com.example.voziloservice.Controller.KomentarController;
import com.example.voziloservice.Repository.OcenaRepository;
import com.example.voziloservice.Repository.VoziloRepository;
import com.example.voziloservice.model.Ocena;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collection;

@Service
public class OcenaServiceImpl implements OcenaService {
    Logger LOGGER = LoggerFactory.getLogger(KomentarController.class);
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
        ocenaRepository.save(o);
        return o;
    }

    @Override
    public double findAverageRate(Long id) {
        int suma = 0;
        LOGGER.info("######################################## ");
        LOGGER.info("suma: ", suma);
        Collection<Ocena> ocene = ocenaRepository.findByIdVozila(id);
        LOGGER.info("OCENE VRACENE PO ID: ", ocene.toString());
        for (Ocena o : ocene) {
            suma += o.getOcena();
            LOGGER.info("suma: "+suma+" nakon dodate ocene: ", o.toString());
        }
        double ret = 0.0;
        ret = (double) suma / (double) ocene.size();
        LOGGER.info("########################## RET: ", ret);
        return ret;
    }
}
