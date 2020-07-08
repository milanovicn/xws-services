package com.example.searchservice.Service;

import com.example.searchservice.Controller.AdvancedSearchController;
import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import com.example.searchservice.Repository.AdvancedSearchRepository;


import org.graalvm.compiler.lir.alloc.lsra.Interval;
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

        LOGGER.debug("#####################");
        LOGGER.debug("SVE REPLIKE KOJE IMAMO: " + ret);

        for (AdvancedSearch as : allCars){
            LOGGER.debug("#####################");
            LOGGER.debug("Ulazak u for petlju za repliku: " + as);
            //provera poklapanja mesta, trebalo bi da je ovo polje uvek popunjeno
            if(!as.getMesto().equals(search.getMesto())){
               ret.remove(as);
             //provera tipa menjaca ako vrednost nije sve
            } else if (!search.getTipMenjaca().equals("sve")){
                //ako se ne poklapaju trenutni i tip menjaca searcha izbaci ga iz liste
                if(as.getTipMenjaca().equals(search.getTipMenjaca())){
                    ret.remove(as);
                }
             //provera tipa goriva ako vrednost nije sve
            }else if(!search.getTipGoriva().equals("sve")){
                if(!search.getTipGoriva().equals(as.getTipGoriva())){
                    ret.remove(as);
                }

            }else if(!search.getMarka().equals("sve")){
                if(!search.getMarka().equals(as.getMarka())){
                    ret.remove(as);
                }

            }else if(!search.getModel().equals("sve")){
                 if(!search.getModel().equals(as.getModel())) {
                     ret.remove(as);
                 }

            }else if(search.getBrojSedistaZaDecu() != 0){
                if(search.getBrojSedistaZaDecu() != as.getBrojSedistaZaDecu()){
                    ret.remove(as);
                }
            }else if(search.getBrojSedistaZaDecu() != 0){
                if(search.getBrojSedistaZaDecu() != as.getBrojSedistaZaDecu()){
                    ret.remove(as);
                }
            }else if(search.getCDWProtection().equals("ne")){
                if(as.isCDWProtection()){
                    ret.remove(as);
                }

            }else if(search.getCDWProtection().equals("da")) {
                if (!as.isCDWProtection()) {
                    ret.remove(as);
                }
             // (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
            }else {
                if(!(search.getDatumOd().isAfter(as.getDatumOd()) && search.getDatumDo().isBefore(as.getDatumDo()))){
                    ret.remove(as);
                }


            }
            LOGGER.debug("LISTA nakon for petlje za repliku: " + ret);
        }

        LOGGER.debug("#####################");
        LOGGER.debug("LISTA REPLIKA KOJE SU ISPUNILE USLOVE " + ret);

        List<Long> returnIds = new ArrayList<>();

        for (AdvancedSearch as:ret) {
            returnIds.add(as.getIdVozila());

        }
        LOGGER.debug("LISTA ID-eva VOZILA: " + returnIds);

        return returnIds;
    }

}
