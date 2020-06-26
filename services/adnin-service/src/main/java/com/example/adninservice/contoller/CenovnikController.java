package com.example.adninservice.contoller;


import com.example.adninservice.model.Cenovnik;

import com.example.adninservice.repository.CenovnikRepository;
import com.example.adninservice.service.CenovnikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user/cenovnik")
public class CenovnikController {
    Logger LOGGER = LoggerFactory.getLogger(CenovnikController.class);
    @Autowired
    private CenovnikService cenovnikService;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    //get all
    @GetMapping( value = "")
    public Collection<Cenovnik> ucitajSve() {
        Collection<Cenovnik> clients = cenovnikService.findAll();
        if(clients!=null) {
            LOGGER.info("CENOVNICI -returned all");
        } else {
            LOGGER.error("CENOVNICI -not returned all");
        }
        return clients;
    }

    //delete by id
    @DeleteMapping(value="/{id}")
    public void deleteCenovnik(@PathVariable("id") Long id) throws Exception {
        Cenovnik c= cenovnikService.getById(id);
        if(id!=null) {
            LOGGER.info("CENOVNIK:{0}-deleted, USER-ID:{1}", id, c.getAutor());
        } else {
            LOGGER.error("CENOVNIK:{0}-not deleted, USER-ID:{1}", id, c.getAutor());
        }
        cenovnikService.removeCenovnik(id);

    }

    //add
    @PostMapping( value = "")
    public Cenovnik addCenovnik(@RequestBody Cenovnik cenovnik) throws Exception {
        if(cenovnik!=null) {
            LOGGER.info("CENOVNIK-ID:{0}-added, USER-ID:{1}", cenovnik.getId(), cenovnik.getAutor());
        } else {
            LOGGER.error("CENOVNIK-ID:{0}-not added, USER-ID:{1}", cenovnik.getId(), cenovnik.getAutor());
        }
        return cenovnikService.addNewCenovnik(cenovnik);
    }

    //get by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cenovnik> getCenovnikById(@PathVariable("id") Long id) throws Exception {

        Cenovnik cenovnik = cenovnikService.getById(id);

        if(cenovnik!=null) {
            LOGGER.info("CENOVNIK-ID:{0}-returned, USER-ID:{1}", cenovnik.getId(), cenovnik.getAutor());
        } else {
            LOGGER.error("CENOVNIK-ID:{0}-not returned, USER-ID:{1}", cenovnik.getId(), cenovnik.getAutor());
        }

        return new ResponseEntity<>(cenovnik, HttpStatus.CREATED);
    }

    //get by author id
    @GetMapping(value = "/autor/{id}")
    public Collection<Cenovnik> getByAuthorId(@PathVariable("id") Long id) {

        Collection<Cenovnik> cenovnik = cenovnikService.findAllByAuthorId(id);
        if(cenovnik!=null) {
            LOGGER.info("CENOVNIK-returned all, USER-ID:{1}",id);
        } else {
            LOGGER.error("CENOVNIK--not returned all, USER-ID:{1}", id);
        }
        return cenovnik;
    }



}
