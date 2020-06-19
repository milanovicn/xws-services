package com.example.searchservice.Controller;

import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import com.example.searchservice.Service.AdvancedSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdvancedSearchController {

    @Autowired
    private AdvancedSearchService advancedSearchService;


    @PostMapping(value = "/search")
    public void addVozilo(@RequestBody AdvancedSearch vozilo) throws Exception {

        AdvancedSearch newAdvancedSearch = advancedSearchService.addAdvancedReplica(vozilo);


    }

    @PostMapping(value = "/pretrazi")
    public ResponseEntity<?> searchCar(@RequestBody SearchDTO sDTO) throws Exception {

        List<Long> ids = advancedSearchService.findByAdvancedSearch(sDTO);

        if(!ids.isEmpty())
            return new ResponseEntity<>(ids, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Nista nije pronadjeno :(", HttpStatus.CREATED);
    }

}
