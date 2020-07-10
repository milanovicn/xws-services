package com.example.voziloservice.soap;

import com.example.voziloservice.Client.UserClient;
import com.example.voziloservice.Service.KomentarService;
import com.example.voziloservice.Service.VoziloService;
import com.example.voziloservice.Service.ZahtevService;
import com.example.voziloservice.model.Vozilo;
import com.example.voziloservice.xsd.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

@org.springframework.ws.server.endpoint.annotation.Endpoint
public class Endpoint {

    final static Logger logger = LoggerFactory.getLogger(Endpoint.class);
    public static final String NAMESPACE_URI = "http://example.com/voziloservice/xsd";

    @Autowired
    VoziloService voziloService;

    @Autowired
    ZahtevService zahtevService;

    @Autowired
    KomentarService komentarService;

    @Autowired
    UserClient userClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postVoziloRequest")
    @ResponsePayload
    public PostVoziloResponse postVoziloResponse(@RequestPayload PostVoziloRequest request) throws Exception {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za postVoziloRequest!\nVOZILO: -->" + request.getVozilo().getMarkaAutomobila() + ", " + request.getVozilo().getModelAutomobila());
        PostVoziloResponse response = new PostVoziloResponse();

        String mejl = userClient.getEmailUlogovanogAgenta();

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
        vozilo.setIznajmljivacMail(mejl);
        vozilo.setPomId(voziloXSD.getPomId());

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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getZahteveByIzdavacMailRequset")
    @ResponsePayload
    public GetZahteveByIzdavacMailResponse getZahteveByUsernameResponse(@RequestPayload GetZahteveByIzdavacMailRequset request) throws Exception {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za getZahteveByUsernameRequset, za MEJL: " + request.getIzdavacMail());
        GetZahteveByIzdavacMailResponse response = new GetZahteveByIzdavacMailResponse();

        String username = request.getIzdavacMail();


        logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > pocetak..");

//        for (com.example.voziloservice.model.Zahtev zahtev : zahtevService.findByIzdavacMail(request.getIzdavacMail())) {
//            response.getZahtevi().add(napraviZahtevXSD(zahtev));
//        }
        List<com.example.voziloservice.model.Zahtev> zahtevi=zahtevService.findAll();
        for (com.example.voziloservice.model.Zahtev zahtev : zahtevi) {
            logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > Pre add(zahtev).." + zahtev.toString());
            response.getZahtevi().add(napraviZahtevXSD(zahtev));
            logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > Posle add(zahtev).." + zahtev.toString());
        }

        logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > kraj..");


        logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > " + response.toString());

        return response;



//        List<com.example.voziloservice.model.Zahtev> zahteviRet = zahtevService.findByIzdavacMail(username);
//        //List<com.example.voziloservice.model.Zahtev> zahteviRet = zahtevService.findAll();  // TODO: vrati na gore...
//
//        System.out.println("---ZAHTEVret:::: " + zahteviRet);
//
//        logger.info("---ZAHTEVret:::: " + zahteviRet);
//        logger.info("---Ispis Nadjenih Zahteva po mejlu: " + username + "-- (Posle findByIzdavacMail()): ");
//        for (com.example.voziloservice.model.Zahtev zahtev : zahteviRet) {
//            logger.info("---#" + zahtev.toString());
//        }
//
//        //response.getZahtevi().addAll(zahteviRet);
//
//        for (com.example.voziloservice.model.Zahtev zahtev : zahteviRet) {
//            Zahtev zahtevXSD = napraviZahtevXSD(zahtev);
//            response.getZahtevi().add(zahtevXSD);
//        }
//
//        logger.info("---Ispis napravljenih zahtevaXSD u responsu: ");
//        for (Zahtev zahtev : response.getZahtevi()) {
//            logger.info("---#" + zahtev.toString());
//        }
//
//        return response;
    }

    private Zahtev napraviZahtevXSD(com.example.voziloservice.model.Zahtev zahtev) throws DatatypeConfigurationException {
        Zahtev zahtevXSD = new Zahtev();

        logger.info("###USAO u NAPRAVIXSD... za zahtev: " + zahtev.toString());

        //zahtevXSD.setVozila(zahtev.getVozila());
        //zahtevXSD.getVozila();
        for (Vozilo vozilo : zahtev.getVozila()) {
            com.example.voziloservice.xsd.Vozilo voziloXSD = new com.example.voziloservice.xsd.Vozilo();

            LocalDateTime dateTimeOd = vozilo.getVaziOd();
            LocalDateTime dateTimeDo = vozilo.getVaziDo();
            GregorianCalendar gcalOd = GregorianCalendar.from(dateTimeOd.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
            GregorianCalendar gcalDo = GregorianCalendar.from(dateTimeDo.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
            XMLGregorianCalendar xcalOd = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalOd);
            XMLGregorianCalendar xcalDo = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalDo);

            voziloXSD.setVaziOd(xcalOd);
            voziloXSD.setVaziDo(xcalDo);
            voziloXSD.setMesto(vozilo.getMesto());
            voziloXSD.setMarkaAutomobila(vozilo.getMarka());
            voziloXSD.setModelAutomobila(vozilo.getModel());
            voziloXSD.setTipMenjaca(vozilo.getTipMenjaca());
            voziloXSD.setTipGoriva(vozilo.getTipGoriva());
            voziloXSD.setKlasaVozila(vozilo.getKlasaVozila());
            voziloXSD.setCenovnikId(vozilo.getCenovnikId());
            voziloXSD.setPredjenaKilometraza(vozilo.getRedjenaKilometraza());
            voziloXSD.setOgranicenaKilometraza(vozilo.getOgranicenaKilometraza());
            voziloXSD.setCDWProtection(vozilo.isCDWProtection());
            voziloXSD.setBrojSedistaDeca(vozilo.getBrojSedistaDeca());
            voziloXSD.setIznajmljivacId(vozilo.getIznajmljivacId());
            voziloXSD.setIznajmljivacMail(vozilo.getIznajmljivacMail());
            voziloXSD.setPomId(vozilo.getPomId());

            zahtevXSD.getVozila().add(voziloXSD);
        }

        if (zahtev.getStanje().equals(com.example.voziloservice.model.Stanje.PENDING)) {
            zahtevXSD.setStanje(Stanje.PENDING);
        } else if (zahtev.getStanje().equals(com.example.voziloservice.model.Stanje.RESERVED)) {
            zahtevXSD.setStanje(Stanje.RESERVED);
        } else if (zahtev.getStanje().equals(com.example.voziloservice.model.Stanje.PAID)) {
            zahtevXSD.setStanje(Stanje.PAID);
        } else if (zahtev.getStanje().equals(com.example.voziloservice.model.Stanje.CANCELED)) {
            zahtevXSD.setStanje(Stanje.CANCELED);
        } else if (zahtev.getStanje().equals(com.example.voziloservice.model.Stanje.WAITING_REVIEW)) {
            zahtevXSD.setStanje(Stanje.WAITING_REVIEW);
        } else if (zahtev.getStanje().equals(com.example.voziloservice.model.Stanje.REVIEWED)) {
            zahtevXSD.setStanje(Stanje.REVIEWED);
        }

        LocalDateTime dateTimeOd = zahtev.getDatumOd();
        LocalDateTime dateTimeDo = zahtev.getDatumDo();
        GregorianCalendar gcalOd = GregorianCalendar.from(dateTimeOd.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
        GregorianCalendar gcalDo = GregorianCalendar.from(dateTimeDo.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xcalOd = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalOd);
        XMLGregorianCalendar xcalDo = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalDo);

        zahtevXSD.setDatumOd(xcalOd);
        zahtevXSD.setDatumDo(xcalDo);

        LocalDateTime dateTimeVremeOdobrenja = zahtev.getVremeOdobrenja();
        LocalDateTime dateTimeVremeKreiranja = zahtev.getVremeKreiranja();
        GregorianCalendar gcalVremeOdobrenja = GregorianCalendar.from(dateTimeVremeOdobrenja.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
        GregorianCalendar gcalVremeKreiranja = GregorianCalendar.from(dateTimeVremeKreiranja.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xcalVremeOdobrenja = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalVremeOdobrenja);
        XMLGregorianCalendar xcalVremeKreiranja = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalVremeKreiranja);// TODO: PROVERI VAMO DAL RADI ZA VREME....

        zahtevXSD.setVremeOdobrenja(xcalVremeOdobrenja);
        zahtevXSD.setVremeKreiranja(xcalVremeKreiranja);

        zahtevXSD.setPodnosilac(zahtev.getPodnosilac());
        zahtevXSD.setIzdavac(zahtev.getIzdavac());
        zahtevXSD.setIzdavacMail(zahtev.getIzdavacMail());

        return zahtevXSD;

    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMailUlogovanogAgentaRequest")
    @ResponsePayload
    public GetMailUlogovanogAgentaResponse getMailUlogovanogAgentaResponse(@RequestPayload GetMailUlogovanogAgentaRequest request) {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za getMailUlogovanogAgentaRequest!");


        String mejl = userClient.getEmailUlogovanogAgenta();

        logger.info("---Izvrsava u Endpoint(u mikroservisu) za getMailUlogovanogAgentaRequest >> od userClient-a dobio mejl: " + mejl);

        GetMailUlogovanogAgentaResponse response = new GetMailUlogovanogAgentaResponse();

        response.setVraceniMejl(mejl);

        return response;

    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getKomentareByIdVozilaRequest")
    @ResponsePayload
    public GetKomentareByIdVozilaResponse getKomentareByIdVozila(@RequestPayload GetKomentareByIdVozilaRequest request) {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za getKomentareByIdVozilaRequest!");

        GetKomentareByIdVozilaResponse response = new GetKomentareByIdVozilaResponse();

        Long idVozila = request.getIdVozila();
        logger.info("###ENDPOINT > getKomentareByIdVozilaRequest > pre komentarService.findApprovedByIdVozilaSoap()..");

        Collection<com.example.voziloservice.model.Komentar> komentari = komentarService.findApprovedByIdVozilaSoap(idVozila);

        logger.info("###ENDPOINT > getKomentareByIdVozilaRequest > posle komentarService.findApprovedByIdVozilaSoap()..\nI vratio size:: " + komentari.size());

        for (com.example.voziloservice.model.Komentar komentar : komentari) {
            response.getKomentari().add(napraviKomentarXSD(komentar));
        }

        logger.info("###ENDPOINT > getKomentareByIdVozilaRequest > posle napravioKomentarXSD()..\nI vratio size:: " + response.getKomentari().size());

        return response;
    }

    private Komentar napraviKomentarXSD(com.example.voziloservice.model.Komentar komentar) {

        Komentar komentarXSD = new Komentar();

        komentarXSD.setIdVozila(komentar.getIdVozila());
        komentarXSD.setKomentar(komentar.getKomentar());

        if (komentar.getStanje().equals(com.example.voziloservice.model.StanjeKomentara.OBJAVLJEN)) {
            komentarXSD.setStanje(StanjeKomentara.OBJAVLJEN);
        } else if (komentar.getStanje().equals(com.example.voziloservice.model.StanjeKomentara.ODBIJEN)) {
            komentarXSD.setStanje(StanjeKomentara.ODBIJEN);
        } else if (komentar.getStanje().equals(com.example.voziloservice.model.StanjeKomentara.ODOBREN)) {
            komentarXSD.setStanje(StanjeKomentara.ODOBREN);
        } else if (komentar.getStanje().equals(com.example.voziloservice.model.StanjeKomentara.ODGOVOREN)) {
            komentarXSD.setStanje(StanjeKomentara.ODGOVOREN);
        }

        return komentarXSD;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postKomentarRequest")
    @ResponsePayload
    public PostKomentarResponse postKomentarResponse(@RequestPayload PostKomentarRequest request) {
        logger.info("---Izvrsava u Endpoint(u mikroservisu) za postKomentarRequest!\nKomentar: -->" + request.getKomentar().getKomentar() + ", " + request.getKomentar().getStanje());
        PostKomentarResponse response = new PostKomentarResponse();

        com.example.voziloservice.model.Komentar komentar = new com.example.voziloservice.model.Komentar();

        Komentar komentarXSD = request.getKomentar();

        komentar.setIdVozila(komentarXSD.getIdVozila());
        komentar.setKomentar(komentarXSD.getKomentar());
        komentar.setStanje(com.example.voziloservice.model.StanjeKomentara.ODOBREN);

        Vozilo vozilo = voziloService.findByPomId(komentarXSD.getIdVozila());

        komentar.setIdVozila(vozilo.getId());

        komentar = komentarService.createAG(komentar);

        if (komentar != null) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;

    }


}
