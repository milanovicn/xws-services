package com.example.adninservice.contoller;

import com.example.adninservice.model.Agent;
import com.example.adninservice.model.Chat;
import com.example.adninservice.model.Client;
import com.example.adninservice.model.Message;
import com.example.adninservice.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( produces =  MediaType.APPLICATION_JSON_VALUE)
public class ChatMicroController {

    @Autowired
    ChatService chatService;

    @GetMapping(value = "/postChat/{user1}/{user2}")
    public ResponseEntity<Chat> kreirajChat(@PathVariable("user1") String user1, @PathVariable("user2") String user2) throws Exception {

        Chat chat = new Chat();
        chat.setUser1(user1);
        chat.setUser2(user2);
        return new ResponseEntity<>(chatService.kreirajCet(chat), HttpStatus.CREATED);
    }

    @GetMapping(value = "/autorAG/{email}")
    public Collection<Chat> vratiChatovePoKorisniku(@PathVariable("email") String email) {

        Collection<Chat> chats = chatService.findAllByAuthorEmail(email);

        return chats;
    }

    @GetMapping(value = "vratiChat/{id}")
    public ResponseEntity<Chat> vratiChat(@PathVariable("id") Long id) throws Exception {

        Chat chat = chatService.getById(id);

        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @GetMapping(value = "/postMessage/{idChat}/{content}/{sender}")
    public ResponseEntity<Message> kreirajPoruku(@PathVariable("idChat") Long id, @PathVariable("content") String content,@PathVariable("sender") String sender) throws Exception {

        Message message = new Message();
        message.setChatId(id);
        message.setContent(content);
        message.setSenderEmail(sender);
        message.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(chatService.kreirajPoruku(message), HttpStatus.CREATED);
    }

    @GetMapping(value = "/messages/{id}")
    public Collection<Message> vratiPorukePoChatu(@PathVariable("id") Long id) {

        Collection<Message> messages = chatService.findAllMessagesByChatId(id);

        return messages;
    }
}