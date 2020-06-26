package com.example.adninservice.contoller;


import com.example.adninservice.model.Admin;
import com.example.adninservice.model.Agent;
import com.example.adninservice.model.Client;
import com.example.adninservice.model.LoginZahtev;
import com.example.adninservice.service.AdminService;
import com.example.adninservice.service.AgentService;
import com.example.adninservice.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class LoginController {

  /*  @Autowired
    private AdminService adminSer;*/

    @Autowired
    private ClientService korisnikService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AgentService agentService;

    Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

   /* @RequestMapping(method = POST, value = "/regKorisnika")
    public ResponseEntity<?> dodajKorisnika(@RequestBody KorisnikDTO korisnikRequest) throws Exception {
        Admin existAdmin = adminSer.findByEmail(korisnikRequest.getEmail());
        Korisnik existKor = korisnikService.findByEmail(korisnikRequest.getEmail());

        if (existAdmin != null || existKor != null) {
            return new ResponseEntity<>("Email vec postoji u bazi! Pokusajte drugi email.", HttpStatus.METHOD_NOT_ALLOWED);
        }

        Korisnik korisnik = korisnikService.create(korisnikRequest);

        return new ResponseEntity<Korisnik>(korisnik, HttpStatus.CREATED);
    }*/


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginZahtev zahtev, @Context HttpServletRequest request) {


        Collection<Client> client = korisnikService.getAll();


        Client ak = korisnikService.fingBuEmail(zahtev.getEmail());

        if (ak != null) {

            if (zahtev.getPassword().equals(ak.getPassword())) {
                //if (zahtev.getPassword().equals(ak.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("client", ak);
                if(ak!=null) {
                    LOGGER.info("CLIENT SESSION: CLIEN-ID:{0}-session created, CLIENT-EMAIL:{1}", ak.getId(), ak.getEmail());
                } else {
                    LOGGER.error("CLIENT SESSION: CLIENT-ID{0}-session not created, CLIENT-EMAIL:{1}" , ak.getId(), ak.getEmail());
                }
                return new ResponseEntity<Client>(ak, HttpStatus.CREATED);
            }

        } else {
            Admin admin = adminService.findByEmail(zahtev.getEmail());
            if (admin != null) {
                if(zahtev.getPassword().equals(admin.getPassword())){
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", admin);

                    if(admin!=null) {
                        LOGGER.info("ADMIN SESSION: ADMIN-ID:{0}-session created, ADMIN-EMAIL:{1}", admin.getId(), admin.getEmail());
                    } else {
                        LOGGER.error("ADMIN SESSION: ADMIN-ID:{0}-session not created, ADMIN-EMAIL:{1}" , admin.getId(), admin.getEmail());
                    }

                    return new ResponseEntity<>(admin, HttpStatus.CREATED);
                }
            }
            else {
                Agent agent = agentService.findByEmail(zahtev.getEmail());
                if (agent != null) {
                    if(zahtev.getPassword().equals(agent.getPassword())){
                        HttpSession session = request.getSession();
                        session.setAttribute("agent", agent);

                        if(agent!=null) {
                            LOGGER.info("AGENT SESSION: AGENT-ID:{0}-session created, AGENT-EMAIL:{1}", agent.getId(), agent.getEmail());
                        } else {
                            LOGGER.error("AGENT SESSION: AGENT-ID:{0}-session not created, AGENT-EMAIL:{1}" , agent.getId(), agent.getEmail());
                        }

                        return new ResponseEntity<>(agent, HttpStatus.CREATED);
                    }
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Metoda koja proverava da li je ostcen integritet poruke
    private boolean checkIntegrity(String data, byte[] dataHash) {
        byte[] newDataHash = hash(data);
        return Arrays.equals(dataHash, newDataHash);
    }

    public byte[] hash(String data) {
        //Kao hes funkcija koristi SHA-256
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] dataHash = sha256.digest(data.getBytes());
            return dataHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/login")
    public Object vratiUlogovanog(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();

        Client korisnik = (Client) session.getAttribute("client");

        if (korisnik != null) {

            if(korisnik!=null) {
                LOGGER.info("CLIENT SESSION: CLIENT-ID:{0}-logged in, CLIENT-EMAIL:{1}", korisnik.getId(), korisnik.getEmail());
            } else {
                LOGGER.error("CLIENT SESSION: CLIENT-ID:{0}- not logged in, CLIENT-EMAIL:{1}" , korisnik.getId(), korisnik.getEmail());
            }

            return korisnik;
        }
        else {
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {
                if(admin!=null) {
                    LOGGER.info("ADMIN SESSION: ADMIN-ID:{0}-logged in, ADMIN-EMAIL:{1}", admin.getId(), admin.getEmail());
                } else {
                    LOGGER.error("ADMIN SESSION: ADMIN-ID:{0}-not logged in, ADMIN-EMAIL:{1}" , admin.getId(), admin.getEmail());
                }
                return admin;
            }
            else {
                Agent agent = (Agent) session.getAttribute("agent");
                if(agent!=null) {
                    LOGGER.info("AGENT SESSION: AGENT-ID:{0}-session created, AGENT-EMAIL:{1}", agent.getId(), agent.getEmail());
                } else {
                    LOGGER.error("AGENT SESSION: AGENT-ID:{0}-session not created, AGENT-EMAIL:{1}" , agent.getId(), agent.getEmail());
                }

                return agent;
            }
        }
    }
    @GetMapping(value = "/returnId")
    public Long vratiIdUlogovanog(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();

        Client korisnik = (Client) session.getAttribute("client");

        if (korisnik != null) {
            return korisnik.getId();
        }
        /*else {
            return (Korisnik) session.getAttribute("korisnik");
        }*/
        return null;
    }



    @RequestMapping(method = PUT, value = "/logOut")
    public ResponseEntity logOut(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
      ////  System.out.println("...LOGOUTK... " + session.getAttribute("korisnik"));
       // System.out.println("...LOGOUTA... " + session.getAttribute("admin"));
        session.invalidate();


            LOGGER.info("SESSION: user logged out");



        return ResponseEntity.status(200).build();
    }
}
