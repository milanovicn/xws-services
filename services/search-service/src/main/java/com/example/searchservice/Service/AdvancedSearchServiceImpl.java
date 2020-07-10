package com.example.searchservice.Service;

import com.example.searchservice.Controller.AdvancedSearchController;
import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import com.example.searchservice.Repository.AdvancedSearchRepository;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdvancedSearchServiceImpl implements AdvancedSearchService {
    Logger LOGGER = LoggerFactory.getLogger(AdvancedSearchController.class);
    @Autowired
    private AdvancedSearchRepository advancedSearchRepository;

    @Override
    public AdvancedSearch addAdvancedReplica(AdvancedSearch vozilo){

        AdvancedSearch newVozilo=new AdvancedSearch(vozilo.getMesto(),vozilo.getDatumOd(),vozilo.getDatumDo(),
                vozilo.getMarka(),vozilo.getTipMenjaca(), vozilo.getModel(),vozilo.getTipGoriva(),
                vozilo.getBrojSedistaZaDecu(),vozilo.isCDWProtection(), vozilo.getIdVozila());

        advancedSearchRepository.save(newVozilo);
        LOGGER.info("#####################");
        LOGGER.info("Cuvanje nove replike: " + newVozilo);

        return newVozilo;



    }

    @Override
    public void removeAdvancedReplica(Long idVozila){


    }

    @Override
    public List<Long> findByAdvancedSearch(SearchDTO search){
        ArrayList<Long> retIDs = new ArrayList<Long>();
        List<AdvancedSearch> allCars = advancedSearchRepository.findAll();

         for (AdvancedSearch as : allCars){
            retIDs.add(as.getIdVozila());
        }

        for (AdvancedSearch as : allCars){
            if(!as.getMesto().equals(search.getMesto())){
                if(retIDs.contains(as.getIdVozila())){
                    retIDs.remove(as.getIdVozila());


                }
            } else if(!search.getMarka().equals("sve")) {

                if (!as.getMarka().equals(search.getMarka())) {
                    if (retIDs.contains(as.getIdVozila())) {
                        retIDs.remove(as.getIdVozila());

                    }
                }
            } else if(!search.getModel().equals("sve")) {

                if (!as.getModel().equals(search.getModel())) {
                    if (retIDs.contains(as.getIdVozila())) {
                        retIDs.remove(as.getIdVozila());

                    }
                }
            } else if(!search.getTipMenjaca().equals("sve")) {

                if (!as.getTipMenjaca().equals(search.getTipMenjaca())) {
                    if (retIDs.contains(as.getIdVozila())) {
                        retIDs.remove(as.getIdVozila());

                    }
                }
            } else if(!search.getTipGoriva().equals("sve")) {

                if (!as.getTipGoriva().equals(search.getTipGoriva())) {
                    if (retIDs.contains(as.getIdVozila())) {
                        retIDs.remove(as.getIdVozila());

                    }
                }
            } else if(search.getBrojSedistaZaDecu() != 0) {

                if (as.getBrojSedistaZaDecu() != search.getBrojSedistaZaDecu()) {
                    if (retIDs.contains(as.getIdVozila())) {
                        retIDs.remove(as.getIdVozila());

                    }
                }
            } else if(!search.getCDWProtection().equals("ne")) {
                boolean temp = false;

                if (search.getCDWProtection().equals("da")){
                    temp = true;
                }
                if (as.isCDWProtection() != temp) {
                    if (retIDs.contains(as.getIdVozila())) {
                        retIDs.remove(as.getIdVozila());

                    }
                }
            } else if(!as.getDatumOd().isBefore(search.getDatumDo()) && !search.getDatumOd().isBefore(as.getDatumDo())){
                if (retIDs.contains(as.getIdVozila())) {
                    retIDs.remove(as.getIdVozila());

                }
            }


        }


        return retIDs;
    }

    @Override
    public List<Long> find(SearchDTO search){

        List<AdvancedSearch> allCars = advancedSearchRepository.findAll();
        List<AdvancedSearch> ret = advancedSearchRepository.findAll();

        LOGGER.info("#####################");
        LOGGER.info("SVE REPLIKE KOJE IMAMO: " + ret.toString());

        for (AdvancedSearch as : allCars){
            LOGGER.info("#####################");
            LOGGER.info("Ulazak u for petlju za repliku: " + as.toString());
            //provera poklapanja mesta, trebalo bi da je ovo polje uvek popunjeno
            if(!as.getMesto().equals(search.getMesto())){
                LOGGER.info("Mesto1");
               ret.remove(as);
             //provera tipa menjaca ako vrednost nije sve
            } 
		if (!search.getTipMenjaca().equals("sve")){
                LOGGER.info("Menjac1");
                //ako se ne poklapaju trenutni i tip menjaca searcha izbaci ga iz liste
                if(as.getTipMenjaca().equals(search.getTipMenjaca())){
                    LOGGER.info("MEnjac2");
                    ret.remove(as);
                }
             //provera tipa goriva ako vrednost nije sve
            }
 if(!search.getTipGoriva().equals("sve")){
                LOGGER.info("Gorivo1");
                if(!search.getTipGoriva().equals(as.getTipGoriva())){
                    LOGGER.info("Gorivo2");
                    ret.remove(as);
                }

            }
if(!search.getMarka().equals("sve")){
                LOGGER.info("Marka1");
                if(!search.getMarka().equals(as.getMarka())){
                    LOGGER.info("Marka2");
                    ret.remove(as);
                }

            }
if(!search.getModel().equals("sve")){
                LOGGER.info("Model1");
                 if(!search.getModel().equals(as.getModel())) {
                     LOGGER.info("Model2");
                     ret.remove(as);
                 }

            }
if(search.getBrojSedistaZaDecu() != 0){
                LOGGER.info("Sedista1");
                if(search.getBrojSedistaZaDecu() != as.getBrojSedistaZaDecu()){
                    LOGGER.info("Sedista2");
                    ret.remove(as);
                }

            }
 if(search.getCDWProtection().equals("ne")){
                LOGGER.info("CDW ne1");
                if(as.isCDWProtection()){
                    ret.remove(as);
                    LOGGER.info("CDW ne2");
                }

            }
 if(search.getCDWProtection().equals("da")) {
                LOGGER.info("CDW da1");
                if (!as.isCDWProtection()) {
                    LOGGER.info("CDW da2");
                    ret.remove(as);
                }
             // (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
            }
                LOGGER.info("Datum1");
                if(!(search.getDatumOd().isAfter(as.getDatumOd()) && search.getDatumDo().isBefore(as.getDatumDo()))){
                    LOGGER.info("Datum2");
                    ret.remove(as);
                }


            
            LOGGER.info("LISTA nakon for petlje za repliku: " + ret.toString());
        }

        LOGGER.info("#####################");
        LOGGER.info("LISTA REPLIKA KOJE SU ISPUNILE USLOVE " + ret.toString());

        List<Long> returnIds = new ArrayList<>();

        for (AdvancedSearch as:ret) {
            returnIds.add(as.getIdVozila());

        }
        LOGGER.info("LISTA ID-eva VOZILA: " + returnIds.toString());

        return returnIds;
    }

}
