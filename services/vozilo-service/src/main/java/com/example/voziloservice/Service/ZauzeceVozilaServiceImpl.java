package com.example.voziloservice.Service;


import com.example.voziloservice.Repository.VoziloRepository;
import com.example.voziloservice.Repository.ZauzeceVozilaRepository;
import com.example.voziloservice.model.ZauzeceVozila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZauzeceVozilaServiceImpl implements ZauzeceVozilaService {

    @Autowired
    private ZauzeceVozilaRepository zauzeceVozilaRepository;

    @Override
    public ZauzeceVozila addVozilo(ZauzeceVozila zauzeceVozila) {
        ZauzeceVozila zv= new ZauzeceVozila(zauzeceVozila.getOd(),zauzeceVozila.getZauzetoDo(),zauzeceVozila.getIdVozila());
        zauzeceVozilaRepository.save(zv);
        return zv;
    }
}
