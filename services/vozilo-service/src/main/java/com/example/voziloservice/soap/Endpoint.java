package com.example.voziloservice.soap;

import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.xsd.PostProbaRequest;
import com.example.voziloservice.xsd.PostProbaResponse;
import com.example.voziloservice.xsd.PostVoziloRequest;
import com.example.voziloservice.xsd.PostVoziloResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@org.springframework.ws.server.endpoint.annotation.Endpoint
public class Endpoint {

    final static Logger logger = LoggerFactory.getLogger(Endpoint.class);
    public static final String NAMESPACE_URI = "http://example.com/voziloservice/xsd";

    @Autowired
    VoziloService voziloService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postVoziloRequest")
    @ResponsePayload
    public PostVoziloResponse postVoziloResponse(@RequestPayload PostVoziloRequest request) throws Exception {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za postVoziloRequest!\nVOZILO: -->" + request.getVozilo().getMarkaAutomobila() + ", " + request.getVozilo().getModelAutomobila());
        PostVoziloResponse response = new PostVoziloResponse();

        Vozilo vozilo = new Vozilo();
        com.example.voziloservice.xsd.Vozilo voziloXSD = request.getVozilo();

        vozilo.setVaziOd(voziloXSD.getVaziOd().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        vozilo.setVaziDo(voziloXSD.getVaziDo().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        vozilo.setMesto(voziloXSD.getMesto());
        vozilo.setMarka(voziloXSD.getMarkaAutomobila());
        vozilo.setModel(voziloXSD.getModelAutomobila());
        vozilo.setTipMenjaca(voziloXSD.getTipMenjaca());
        vozilo.setTipGoriva(voziloXSD.getTipGoriva());
        vozilo.setKlasaVozila(voziloXSD.getKlasaVozila());
        vozilo.setCenovnikId(voziloXSD.getCenovnikId());
        vozilo.setRedjenaKilometraza(voziloXSD.getPredjenaKilometraza());
        vozilo.setOgranicenaKilometraza(voziloXSD.getOgranicenaKilometraza());
        vozilo.setCDWProtection(voziloXSD.isCDWProtection());
        vozilo.setBrojSedistaDeca(voziloXSD.getBrojSedistaDeca());
        vozilo.setIznajmljivacId(voziloXSD.getIznajmljivacId());
        vozilo.setIznajmljivacMail(voziloXSD.getIznajmljivacMail());

        logger.info("---!Pre addVozilo() : " + vozilo.getMarka() + ", " + vozilo.getModel());

        logger.info("---!Pre addVozilo(), ispis VOZILA: " + vozilo.toString());

        vozilo = voziloService.addVozilo(vozilo, "AGENT"); //TODO: Proveri vamo dal se prosledjuje string

        logger.info("---!Posle addVozilo(), ispis VOZILA: " + vozilo.toString());

        logger.info("---!Prosao addVozilo() i vratio vozilo: " + vozilo.getMarka() + ", " + vozilo.getModel());

        if (vozilo != null) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postProbaRequest")
    @ResponsePayload
    public PostProbaResponse postProbaResponse(@RequestPayload PostProbaRequest request) {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za postProbaRequest");
        PostProbaResponse response = new PostProbaResponse();

        String salje = request.getSalje();

        if (salje.length() > 4)
            response.setPrima(salje + ", i IMA VISE OD 4 KARAKTERA");
        else
            response.setPrima(salje + ", i IMA MANJE ili JEDNAKO OD 4 KARAKTERA");

        return response;
    }

}
