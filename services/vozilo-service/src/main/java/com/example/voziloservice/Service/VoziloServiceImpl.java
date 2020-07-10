package com.example.voziloservice.Service;

import com.example.voziloservice.Client.UserClient;

import com.example.voziloservice.Repository.VoziloRepository;
import com.example.voziloservice.model.Vozilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class VoziloServiceImpl implements VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private UserClient userClient;

   /* @Autowired
    private SearchClient searchClient;*/
	final static Logger logger = LoggerFactory.getLogger(VoziloServiceImpl.class);


    @Override
    public Vozilo addVozilo(Vozilo vozilo,String rola) {

        if(rola.equals("CLIENT")) {
            if (userClient.chackNuberOfCars(vozilo.getIznajmljivacId()) == true) {
                Vozilo newVozilo = new Vozilo(vozilo.getMarka(), vozilo.getModel(), vozilo.getTipGoriva(), vozilo.getTipMenjaca(),
                        vozilo.getKlasaVozila(), vozilo.getCenovnikId(), vozilo.getRedjenaKilometraza(), vozilo.getOgranicenaKilometraza(),
                        vozilo.isCDWProtection(), vozilo.getBrojSedistaDeca(), vozilo.getVaziOd(), vozilo.getVaziDo(), vozilo.getMesto(), vozilo.getIznajmljivacId(),vozilo.getIznajmljivacMail(), 0L);

                logger.info("---!Pre voziloRepositorySave() (Za Klijenta) ispis vozila:: " + newVozilo.toString());
                voziloRepository.save(newVozilo);
                logger.info("---!Posle voziloRepositorySave() (Za Klijenta) ispis vozila:: " + newVozilo.toString());
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
            } else
                return null;
        }else{
            Vozilo newVozilo = new Vozilo(vozilo.getMarka(), vozilo.getModel(), vozilo.getTipGoriva(), vozilo.getTipMenjaca(),
                    vozilo.getKlasaVozila(), vozilo.getCenovnikId(), vozilo.getRedjenaKilometraza(), vozilo.getOgranicenaKilometraza(),
                    vozilo.isCDWProtection(), vozilo.getBrojSedistaDeca(), vozilo.getVaziOd(), vozilo.getVaziDo(), vozilo.getMesto(), vozilo.getIznajmljivacId(),vozilo.getIznajmljivacMail(), vozilo.getPomId());

            logger.info("---!");
		    logger.info("---!Pre voziloRepositorySave() ispis vozila:: " + newVozilo.toString());

            newVozilo = voziloRepository.save(newVozilo);

            logger.info("---!Posle voziloRepositorySave() ispis vozila:: " + newVozilo.toString());
            return newVozilo;
        }
    }

    @Override
    public List<Vozilo> getAll() {
        return voziloRepository.findAll();
    }

    @Override
    public List<Vozilo> sortiraj(List<Vozilo> vozila, String sortBy) {

        if(sortBy.equals("KILOMETRAZA")){
            vozila.sort(Comparator.comparingDouble(Vozilo :: getRedjenaKilometraza));
            return vozila;
        }
        else if(sortBy.equals("OCENA")){

           // vozila.sort(Comparator.comparingInt(Vozilo :: getOcena));
            return vozila;
        }
        else if(sortBy.equals("CENA")){
            Vozilo temp = new Vozilo() ;
           for (int i = 0; i < vozila.size()-1; i++) {
                for (int j = i+1; j < vozila.size(); j++) {
                    if(userClient.getCenovnikByNaziv(vozila.get(i).getCenovnikId()) > userClient.getCenovnikByNaziv(vozila.get(j).getCenovnikId())) {
                        temp = vozila.get(i);
                        vozila.set(i,vozila.get(j));
                        vozila.set(j,temp);
                    }
                }
            }
           return  vozila;
        }
        return null;
    }

    @Override
    public List<Vozilo> findByIznajmljivacId(Long id) {
        return voziloRepository.findByIznajmljivacId(id);
    }

    @Override
    public List<Vozilo> findByIznajmljivacMail(String mail) {
        return voziloRepository.findByIznajmljivacMail(mail);
    }

    @Override
    public Vozilo findById(Long id) {
        return voziloRepository.findById(id).orElse(null);
    }

    @Override
    public Vozilo findByPomId(Long id) {
        return voziloRepository.findByPomId(id);
    }
}
