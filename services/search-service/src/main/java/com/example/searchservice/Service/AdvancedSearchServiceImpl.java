package com.example.searchservice.Service;

import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import com.example.searchservice.Repository.AdvancedSearchRepository;

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

    @Autowired
    private AdvancedSearchRepository advancedSearchRepository;

    @Override
    public AdvancedSearch addAdvancedReplica(AdvancedSearch vozilo){

        AdvancedSearch newVozilo=new AdvancedSearch(vozilo.getMesto(),vozilo.getDatumOd(),vozilo.getDatumDo(),vozilo.getMarka(),vozilo.getTipMenjaca(),
                vozilo.getModel(),vozilo.getTipGoriva(),vozilo.getBrojSedistaZaDecu(),vozilo.isCDWProtection(),vozilo.getIdVozila()
        );

        advancedSearchRepository.save(newVozilo);
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
            } else if(!search.getCDWProtection().equals("sve")) {
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


}
