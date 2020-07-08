package com.example.searchservice.Controller;

import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import com.example.searchservice.Service.AdvancedSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdvancedSearchController {
    Logger LOGGER = LoggerFactory.getLogger(AdvancedSearchController.class);
    @Autowired
    private AdvancedSearchService advancedSearchService;


    @PostMapping(value = "/search")
    public void addVozilo(@RequestBody AdvancedSearch vozilo) throws Exception {

        AdvancedSearch newAdvancedSearch = advancedSearchService.addAdvancedReplica(vozilo);
        if(newAdvancedSearch!=null) {
            LOGGER.info(MessageFormat.format("SEARCH: SEARCH-ID:{0}-created, VOZILO-ID:{1}", newAdvancedSearch.getId(), newAdvancedSearch.getIdVozila()));
        } else {
            LOGGER.error(MessageFormat.format("SEARCH: SEARCH-ID:{0}-not created, VOZILO-ID:{1}", newAdvancedSearch.getId(), newAdvancedSearch.getIdVozila()));
        }

    }

    @PostMapping(value = "/pretrazi")
    public ResponseEntity<?> searchCar(@RequestBody SearchDTO sDTO) throws Exception {

        LOGGER.debug("####################");
        LOGGER.debug("PretragaDTO po kojoj se pretrazuje: " + sDTO);

        List<Long> ids = advancedSearchService.find(sDTO);

        if(!ids.isEmpty()) {
            LOGGER.info(MessageFormat.format("SEARCH: returned list, LIST-LENGTH:{0}", ids.size()));

            return new ResponseEntity<>(ids, HttpStatus.CREATED);
        } else {
            LOGGER.error(MessageFormat.format("SEARCH: returned empty list, LIST-LENGTH:{0}", ids.size()));
            return new ResponseEntity<>("Nista nije pronadjeno :(", HttpStatus.CREATED);
        }
    }

}
