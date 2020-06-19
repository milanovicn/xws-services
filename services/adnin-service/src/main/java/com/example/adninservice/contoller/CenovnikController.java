package com.example.adninservice.contoller;

import com.example.adninservice.model.Cenovnik;
import com.example.adninservice.repository.CenovnikRepository;
import com.example.adninservice.service.CenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user/cenovnik")
public class CenovnikController {

    @Autowired
    private CenovnikService cenovnikService;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    //get all
    @GetMapping( value = "")
    public Collection<Cenovnik> ucitajSve() {

        return cenovnikService.findAll();
    }

    //delete by id
    @DeleteMapping(value="/{id}")
    public void deleteCenovnik(@PathVariable("id") Long id) throws Exception {

        cenovnikService.removeCenovnik(id);

    }

    //add
    @PostMapping( value = "")
    public Cenovnik addCenovnik(@RequestBody Cenovnik cenovnik) throws Exception {

        return cenovnikService.addNewCenovnik(cenovnik);
    }

    //get by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cenovnik> getCenovnikById(@PathVariable("id") Long id) throws Exception {

        Cenovnik c = cenovnikService.getById(id);

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    //get by author id
    @GetMapping(value = "/autor/{id}")
    public Collection<Cenovnik> getByAuthorId(@PathVariable("id") Long id) {

        return cenovnikService.findAllByAuthorId(id);
    }



}
