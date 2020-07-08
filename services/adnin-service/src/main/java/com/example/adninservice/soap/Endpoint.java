package com.example.adninservice.soap;

import com.example.adninservice.model.Chat;
import com.example.adninservice.model.Message;
import com.example.adninservice.service.ChatService;
import com.example.adninservice.xsd.*;
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
import java.util.GregorianCalendar;
import java.util.List;

@org.springframework.ws.server.endpoint.annotation.Endpoint
public class Endpoint {

    final static Logger logger = LoggerFactory.getLogger(Endpoint.class);
    public static final String NAMESPACE_URI = "http://example.com/voziloservice/xsd";

    @Autowired
    ChatService chatService;




    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postChatRequest")
    @ResponsePayload
    public PostChatResponse postVoziloResponse(@RequestPayload PostChatRequest request) throws Exception {
        //logger.info("---Izvrsava u Endpoint(u mikroservisu) za postVoziloRequest!\nVOZILO: -->" + request.getVozilo().getMarkaAutomobila() + ", " + request.getVozilo().getModelAutomobila());
        PostChatResponse response = new PostChatResponse();

        Chat vozilo = new Chat();
        com.example.adninservice.xsd.Chat chatXSD = request.getChat();

        vozilo.setId(chatXSD.getId());
        vozilo.setUser1(chatXSD.getUser1());
        vozilo.setUser2(chatXSD.getUser2());

       // logger.info("---!Pre addVozilo() : " + vozilo.getMarka() + ", " + vozilo.getModel());
// logger.info("---!Pre addVozilo(), ispis VOZILA: " + vozilo.toString());

        vozilo = chatService.kreirajCet(vozilo); //TODO: Proveri vamo dal se prosledjuje string

       // logger.info("---!Posle addVozilo(), ispis VOZILA: " + vozilo.toString());

       // logger.info("---!Prosao addVozilo() i vratio vozilo: " + vozilo.getMarka() + ", " + vozilo.getModel());

        if (vozilo != null) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postMessageRequest")
    @ResponsePayload
    public PostMessageResponse postVoziloResponse(@RequestPayload PostMessageRequest request) throws Exception {
        //logger.info("---Izvrsava u Endpoint(u mikroservisu) za postVoziloRequest!\nVOZILO: -->" + request.getVozilo().getMarkaAutomobila() + ", " + request.getVozilo().getModelAutomobila());
        PostMessageResponse response = new PostMessageResponse();

        Message vozilo = new Message();
        com.example.adninservice.xsd.Message chatXSD = request.getMessage();


        vozilo.setChatId(chatXSD.getChatId());
        vozilo.setSenderEmail(chatXSD.getSenderEmail());
        vozilo.setTimestamp(chatXSD.getTimestamp().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        vozilo.setContent(chatXSD.getContent());

        // logger.info("---!Pre addVozilo() : " + vozilo.getMarka() + ", " + vozilo.getModel());
// logger.info("---!Pre addVozilo(), ispis VOZILA: " + vozilo.toString());

        vozilo = chatService.kreirajPoruku(vozilo); //TODO: Proveri vamo dal se prosledjuje string

        // logger.info("---!Posle addVozilo(), ispis VOZILA: " + vozilo.toString());

        // logger.info("---!Prosao addVozilo() i vratio vozilo: " + vozilo.getMarka() + ", " + vozilo.getModel());

        if (vozilo != null) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getChatByAgentMailRequest")
    @ResponsePayload
    public GetChatByAgentMailResponse getChatByAgentMailResponse(@RequestPayload GetChatByAgentMailRequest request) throws Exception {
        //logger.info("---Izvrsava u Endpoint(u mikroservisu) za getZahteveByUsernameRequset, za MEJL: " + request.getIzdavacMail());
        GetChatByAgentMailResponse response = new GetChatByAgentMailResponse();

        String username = request.getMail();


        logger.info("###ENDPOINT > getChatByAgentMailRequest > pocetak..");


        for (com.example.adninservice.model.Chat chat : chatService.findAllByAuthorEmail(username)) {
            //logger.info("###ENDPOINT > getChatByAgentMailRequest > Pre add(zahtev).." + zahtev.toString());
            response.getChatovi().add(napraviChatXSD(chat));
          //  logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > Posle add(zahtev).." + zahtev.toString());
        }


        return response;


    }

    private com.example.adninservice.xsd.Chat napraviChatXSD(Chat chat) {
        com.example.adninservice.xsd.Chat zahtevXSD = new com.example.adninservice.xsd.Chat();


       zahtevXSD.setUser1(chat.getUser1());
       zahtevXSD.setUser2(chat.getUser2());
       zahtevXSD.setId(chat.getId());



        return zahtevXSD;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageByChatIdRequest")
    @ResponsePayload
    public GetMessageByChatIdResponse getChatByAgentMailResponse(@RequestPayload GetMessageByChatIdRequest request) throws Exception {
        //logger.info("---Izvrsava u Endpoint(u mikroservisu) za getZahteveByUsernameRequset, za MEJL: " + request.getIzdavacMail());
        GetMessageByChatIdResponse response = new GetMessageByChatIdResponse();

        Long chatID = request.getChatId();


        logger.info("###ENDPOINT > getMessageByChatIdRequest > pocetak..");


        for (com.example.adninservice.model.Message message : chatService.findAllMessagesByChatId(chatID)) {
            //logger.info("###ENDPOINT > getChatByAgentMailRequest > Pre add(zahtev).." + zahtev.toString());
            response.getMessages().add(napraviMessageXSD(message));
            //  logger.info("###ENDPOINT > getZahteveByIzdavacMailRequset > Posle add(zahtev).." + zahtev.toString());
        }


        return response;


    }

    private com.example.adninservice.xsd.Message napraviMessageXSD(Message message) throws DatatypeConfigurationException {
        com.example.adninservice.xsd.Message messageXSD = new com.example.adninservice.xsd.Message();

        LocalDateTime dateTimestamp = message.getTimestamp();
        GregorianCalendar gcalTimestamp = GregorianCalendar.from(dateTimestamp.atZone(ZoneId.systemDefault()));
        XMLGregorianCalendar xcalTimestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalTimestamp);

        messageXSD.setContent(message.getContent());
        messageXSD.setSenderEmail(message.getSenderEmail());
        messageXSD.setTimestamp(xcalTimestamp);
        messageXSD.setChatId(message.getChatId());




        return messageXSD;
    }


}
