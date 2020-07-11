package com.example.adninservice.service;

import com.example.adninservice.model.Chat;
import com.example.adninservice.model.Message;
import com.example.adninservice.repository.ChatRepository;
import com.example.adninservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class ChatServiceImpl implements ChatService {


    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public ArrayList<Chat> findAllByAuthorEmail(String email) {
        ArrayList<Chat> ret = new ArrayList<>();
        for (Chat c: chatRepository.findAll()) {
            if(c.getUser1().equals(email) || c.getUser2().equals(email)){
                ret.add(c);
            }
        }

        return ret;
    }

    @Override
    public Chat getById(Long id) {
        return chatRepository.findById(id).orElseGet(null);
    }

    @Override
    public ArrayList<Message> findAllMessagesByChatId(Long id) {
        ArrayList<Message> ret = new ArrayList<>();
        for (Message m: messageRepository.findAll()) {
            if(m.getChatId() == id){
                ret.add(m);
            }
        }
        //nadam se da radi
        ret.sort(Comparator.comparing(o -> o.getTimestamp()));
        return ret;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Message kreirajPoruku(Message message) {
        Message ret = new Message();
        ret.setChatId(message.getChatId());
        ret.setContent(message.getContent());
        ret.setSenderEmail(message.getSenderEmail());
        ret.setTimestamp(message.getTimestamp());

        ret = messageRepository.save(ret);

       /* String newClient ="";
        Chat c = chatRepository.findById(message.getChatId()).orElseGet(null);
        if(c.getUser1().equals(message.getSenderEmail())){
            newClient=c.getUser2();
        }else{
            newClient=c.getUser1();
        }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(newClient);
        mail.setFrom("isaPSW1@gmail.com");
        mail.setSubject("Nova poruka sa sajta Rent-a-Car");
        mail.setText("OD: "+ message.getSenderEmail() + ",\nPORUKA: " + message.getContent());
        javaMailSender.send(mail);*/


        return ret;
    }

    @Override
    public Chat kreirajCet(Chat chat) {
        Chat ret = new Chat();
        ret.setUser1(chat.getUser1());
        ret.setUser2(chat.getUser2());

        ret = chatRepository.save(ret);
        return ret;
    }





}
