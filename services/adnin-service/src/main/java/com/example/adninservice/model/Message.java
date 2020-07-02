package com.example.adninservice.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ChatId", nullable = false)
    private Long chatId;

    @Column(name = "SenderEmail", nullable = false)
    private String senderEmail;

    @Column(name = "Timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "Content", nullable = false)
    private String content;

    public Message(Long chatId, String senderEmail, LocalDateTime timestamp, String content) {
        this.chatId = chatId;
        this.senderEmail = senderEmail;
        this.timestamp = timestamp;
        this.content = content;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
