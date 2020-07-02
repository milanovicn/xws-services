package com.example.adninservice.service;

import com.example.adninservice.model.Chat;
import com.example.adninservice.model.Message;

import java.util.ArrayList;

public interface ChatService {
    public ArrayList<Chat> findAllByAuthorEmail(String email);
    public Chat getById(Long id);
    public ArrayList<Message> findAllMessagesByChatId(Long id);
    public Message kreirajPoruku(Message message);
    public Chat kreirajCet(Chat chat);
}
