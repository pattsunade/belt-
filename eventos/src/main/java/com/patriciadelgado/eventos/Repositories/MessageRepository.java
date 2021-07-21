package com.patriciadelgado.eventos.Repositories;

import java.util.List;

import com.patriciadelgado.eventos.Models.Message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAll();
    
}
