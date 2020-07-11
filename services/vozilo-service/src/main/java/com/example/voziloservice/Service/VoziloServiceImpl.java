package com.example.voziloservice.Service;

import com.example.voziloservice.Client.UserClient;

import com.example.voziloservice.Repository.VoziloRepository;
import com.example.voziloservice.model.Ocena;
import com.example.voziloservice.model.Vozilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VoziloServiceImpl implements VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OcenaService ocenaService;



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
    public Vozilo updateKilometraza(Long id, double kilometraza) {
        Vozilo v=voziloRepository.findByPomId(id);
        v.setRedjenaKilometraza(kilometraza);
        voziloRepository.save(v);
        return v;
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

            HashMap<Long,Double> oceneVozila=new HashMap<>();
            Collection<Ocena> ocene=new ArrayList<>();

            for(Vozilo v:vozila){
                ocene=ocenaService.findByIdVozila(v.getId());
                double srednja=0;
                double zbir=0;
                for(Ocena o:ocene){
                    zbir+=o.getOcena();
                }
                srednja=zbir/ocene.size();
                oceneVozila.put(v.getId(),srednja);
            }
            Map<Long,Double> sortiraneOCene=sortByComparator(oceneVozila);
           // vozila.sort(Comparator.comparingInt(Vozilo :: getOcena));
            List<Vozilo>ret=new ArrayList<>();
            for(Long key:sortiraneOCene.keySet()){
                ret.add(findById(key));
            }
            return ret;


         

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

    private static Map<Long, Double> sortByComparator(Map<Long, Double> unsortMap)
    {

        List<Map.Entry<Long, Double>> list = new LinkedList<Map.Entry<Long, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Long, Double>>()
        {
            public int compare(Map.Entry<Long, Double> o1,
                               Map.Entry<Long, Double> o2)
            {

                    return o1.getValue().compareTo(o2.getValue());

            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Long, Double> sortedMap = new LinkedHashMap<Long, Double>();
        for (Map.Entry<Long, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
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
