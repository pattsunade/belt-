package com.patriciadelgado.eventos.Repositories;

import java.util.List;

import com.patriciadelgado.eventos.Models.Event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();

     List<Event> findByStateContaining(String state);

     List<Event> findByStateNotContaining(String state);
    
   
}
