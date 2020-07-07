package com.example.voziloservice.Controller;



import com.example.voziloservice.Repository.ImageRepository;
import com.example.voziloservice.Service.ImageModelService;
import com.example.voziloservice.Service.ZauzeceVozilaService;
//import com.example.voziloservice.conf.EndpointConfiguration;
//import com.example.voziloservice.model.Log;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.model.ImageModel;
import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.model.ZauzeceVozila;
//import com.example.voziloservice.producer.LogProducer;
//import com.example.voziloservice.util.RequestCounter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class VoziloController {

    final static Logger LOGGER = LoggerFactory.getLogger(VoziloController.class);

   /* private static final String LOG_GET_ALL = "action=getAllServers user=%s times=%s";
    private static final String LOG_GET_BY_ID = "action=getServerById id=%s user=%s times=%s";
    private static final String LOG_SAVE = "action=saveVozilo user=%s times=%s";
    private static final String LOG_REMOVE = "action=removeServer id=%s user=%s times=%s";

    private static final String SERVICE_NAME = "servers";
    private static final String DEFAULT_USER = "public";

    private final RequestCounter counter;
    private final LogProducer logProducer;

    public VoziloController(RequestCounter counter, LogProducer logProducer) {
        this.counter = counter;
        this.logProducer = logProducer;
    }
*/
    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ZauzeceVozilaService zauzeceVozilaService;

    @PostMapping( value = "/addVozilo/{rola}")
    public ResponseEntity<?> addVozilo(@RequestBody Vozilo vozilo,@PathVariable("rola") String rola) throws Exception {

        Vozilo newVozilo=voziloService.addVozilo(vozilo,rola);

        if(newVozilo!=null) {
           // String logContent = String.format(LOG_SAVE, DEFAULT_USER, counter.get("/addVozilo/{rola}"));
          //  LOGGER.info(logContent);
          //  logProducer.send(new Log(SERVICE_NAME, logContent));
            LOGGER.info(MessageFormat.format("Car-ID:{0}-added;UserID:{1}", newVozilo.getId(),newVozilo.getIznajmljivacId()));
            return new ResponseEntity<>(newVozilo, HttpStatus.CREATED);

        }
        else {
            LOGGER.info("Car not created");
            return new ResponseEntity<>("Ne mozete dodati vise od 3 vozila", HttpStatus.CREATED);
        }
    }


    @PostMapping( value = "/zauzmiVozilo")
    public ResponseEntity<?> zauzmiVozilo(@RequestBody ZauzeceVozila zauzeceVozila) throws Exception {

        ZauzeceVozila newZauzece=zauzeceVozilaService.addVozilo(zauzeceVozila);

        if(newZauzece!=null){

            LOGGER.info(MessageFormat.format("ZAUZECE-ID:{0}-created; VoziloID:{1}", zauzeceVozila.getId(),zauzeceVozila.getIdVozila()));
            return new ResponseEntity<>(newZauzece, HttpStatus.CREATED);
        }
        else{
            LOGGER.info("ZAUZECE:not created");
            return new ResponseEntity<>("Ne radi", HttpStatus.CREATED);}
    }
    @GetMapping(value = "/getAllCars")
    public ResponseEntity<List<Vozilo>> getAllCars() throws Exception {

        List<Vozilo> cars=voziloService.getAll();
        if(cars!=null) {
            LOGGER.info("CAR - returned all");
        } else {
            LOGGER.error("CAR - not returned all");
        }

        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCar/{idVozila}")
    public ResponseEntity<Vozilo> getCarById(@PathVariable("idVozila") Long idVozila) throws Exception {

        Vozilo car=voziloService.findById(idVozila);

        if(car!=null) {
            LOGGER.info(MessageFormat.format("CAR-ID:{0}-returned by id", car.getId()));
        } else {
            LOGGER.error(MessageFormat.format("CAR-ID:{0}-not returned by id", car.getId()));
        }

        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping(value = "/vratiPoKorisniku/{idIzdavaca}")
    public ResponseEntity<List<Vozilo>> getCarByIdIzavaca(@PathVariable("idIzdavaca") Long idIzdavaca) throws Exception {

        List<Vozilo> cars=voziloService.findByIznajmljivacId(idIzdavaca);
        if(cars!=null) {
            LOGGER.info(MessageFormat.format("CARS: returned all by id izdavaoca, CARS-LIST-SIZE:{0},ID-IZDAVAOCA:{1}", cars.size(), idIzdavaca ));
        } else {
            LOGGER.error(MessageFormat.format("CARS:not returned all by id izdavaoca, ID-IZDAVAOCA:{0}", idIzdavaca));
        }

        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @GetMapping(value = "/vratiPoIzdavacu/{mail}")
    public ResponseEntity<List<Vozilo>> getCarByMailIzavaca(@PathVariable("mail") String mail) throws Exception {

        List<Vozilo> cars=voziloService.findByIznajmljivacMail(mail);
        if(cars!=null) {
            LOGGER.info(MessageFormat.format("CARS: returned all by id izdavaoca, CARS-LIST-SIZE:{0},ID-IZDAVAOCA:{1}", cars.size(), mail ));
        } else {
            LOGGER.error(MessageFormat.format("CARS:not returned all by id izdavaoca, ID-IZDAVAOCA:{0}", mail));
        }

        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @PostMapping(value = "/sortiraj/{sortBy}")
    public ResponseEntity<List<Vozilo>> sort(@RequestBody List<Vozilo> vozila,@PathVariable("sortBy") String sortBy) throws Exception {

        List<Vozilo> cars=voziloService.sortiraj(vozila,sortBy);


        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageModelService imageModelService;

    //cuvanje slike
    @PostMapping("/uploadImage")
    public  ResponseEntity<?>  uploadImage(@RequestParam("imageFile") MultipartFile mpf) throws IOException {
        //System.out.println("File to string  - " + file.toString());
        //System.out.println("Original Image Byte Size - " + fimpfle.getBytes().length);

        //for (MultipartFile mpf : file) {
        ImageModel img = new ImageModel(mpf.getOriginalFilename(), mpf.getContentType(), compressBytes(mpf.getBytes()));
        imageRepository.save(img);
        //}
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //vracanje slike
    @GetMapping(path = { "/getImage/{idVozila}" })
    public ImageModel getImage(@PathVariable("idVozila") String idVozila) throws IOException {

        final Optional<ImageModel> retrievedImage = Optional.ofNullable(imageModelService.findByCarId(idVozila));


        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);}
        try {
            outputStream.close();
        } catch (IOException e) {
        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {
        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }



}
