package com.example.adninservice.contoller;

import com.example.adninservice.model.Chat;
import com.example.adninservice.model.Message;
import com.example.adninservice.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    ChatService chatService;

    @GetMapping(value = "/autor/{email}")
    public Collection<Chat> vratiChatovePoKorisniku(@PathVariable("email") String email) {

        Collection<Chat> chats = chatService.findAllByAuthorEmail(email);

        return chats;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Chat> vratiChat(@PathVariable("id") Long id) throws Exception {

        Chat chat = chatService.getById(id);

        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @GetMapping(value = "/message/{id}")
    public Collection<Message> vratiPorukePoChatu(@PathVariable("id") Long id) {

        Collection<Message> messages = chatService.findAllMessagesByChatId(id);

        return messages;
    }

    @PostMapping( value = "/message")
    public Message posaljiPorukuNaCet(@RequestBody Message message) throws Exception {

        return chatService.kreirajPoruku(message);
    }

    @PostMapping( value = "")
    public Chat kreirajChat(@RequestBody Chat chat) throws Exception {

        return chatService.kreirajCet(chat);
    }


}
