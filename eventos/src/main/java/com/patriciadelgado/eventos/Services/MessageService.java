package com.patriciadelgado.eventos.Services;

import java.util.List;

import com.patriciadelgado.eventos.Models.Message;
import com.patriciadelgado.eventos.Repositories.MessageRepository;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveComment(Message message) {
        return messageRepository.save(message);
    }
     public List<Message> getAllMessages() {
    	return messageRepository.findAll();
    } 
}
