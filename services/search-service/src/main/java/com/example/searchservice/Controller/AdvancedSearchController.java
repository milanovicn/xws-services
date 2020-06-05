package com.example.searchservice.Controller;

import com.example.searchservice.Model.AdvancedSearch;
import com.example.searchservice.Model.Dto.SearchDTO;
import com.example.searchservice.Service.AdvancedSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdvancedSearchController {

    @Autowired
    private AdvancedSearchService advancedSearchService;


    @PostMapping(value = "/dodaj")
    public ResponseEntity<?> addVozilo(@RequestBody AdvancedSearch as) throws Exception {

        AdvancedSearch newAdvancedSearch = advancedSearchService.addAdvancedReplica(as);

        if(newAdvancedSearch!=null)
            return new ResponseEntity<>(newAdvancedSearch, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("fejl", HttpStatus.CREATED);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchDTO sDTO) throws Exception {

        List<Long> ids = advancedSearchService.findByAdvancedSearch(sDTO);

        if(!ids.isEmpty())
            return new ResponseEntity<>(ids, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Nista nije pronadjeno :(", HttpStatus.CREATED);
    }

}
