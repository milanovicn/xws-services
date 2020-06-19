package com.example.voziloservice.Controller;


import com.example.voziloservice.Repository.ImageRepository;
import com.example.voziloservice.Service.ImageModelService;
import com.example.voziloservice.Service.ZauzeceVozilaService;
import com.example.voziloservice.model.ImageModel;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.model.ZauzeceVozila;
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
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class VoziloController {

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ZauzeceVozilaService zauzeceVozilaService;

    @PostMapping( value = "/addVozilo")
    public ResponseEntity<?> addVozilo(@RequestBody Vozilo vozilo) throws Exception {

        Vozilo newVozilo=voziloService.addVozilo(vozilo);

        if(newVozilo!=null)
            return new ResponseEntity<>(newVozilo, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Ne mozete dodati vise od 3 vozila", HttpStatus.CREATED);
    }


    @PostMapping( value = "/zauzmiVozilo")
    public ResponseEntity<?> zauzmiVozilo(@RequestBody ZauzeceVozila zauzeceVozila) throws Exception {

        ZauzeceVozila newZauzece=zauzeceVozilaService.addVozilo(zauzeceVozila);

        if(newZauzece!=null)
            return new ResponseEntity<>(newZauzece, HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Ne radi", HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAllCars")
    public ResponseEntity<List<Vozilo>> getAllCars() throws Exception {

        List<Vozilo> cars=voziloService.getAll();

        return new ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCar/{idVozila}")
    public ResponseEntity<Vozilo> getCarById(@PathVariable("idVozila") Long idVozila) throws Exception {

        Vozilo car=voziloService.findById(idVozila);

        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping(value = "/vratiPoKorisniku/{idIzdavaca}")
    public ResponseEntity<List<Vozilo>> getCarByIdIzavaca(@PathVariable("idIzdavaca") Long idIzdavaca) throws Exception {

        List<Vozilo> cars=voziloService.findByIznajmljivacId(idIzdavaca);

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
