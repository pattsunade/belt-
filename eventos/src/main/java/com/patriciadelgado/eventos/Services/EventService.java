package com.patriciadelgado.eventos.Services;

import java.util.List;
import java.util.Optional;

import com.patriciadelgado.eventos.Models.Event;
import com.patriciadelgado.eventos.Repositories.EventRepository;

import org.springframework.stereotype.Service;


@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> eventInState(String state) {
    	return eventRepository.findByStateContaining(state);
    } 
    
  
    public List<Event> eventOutState(String state) {
    	return eventRepository.findByStateNotContaining(state);
    } 
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event addEvent(Event event) {
        event.setName(event.getName());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            return null;
        }

    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }
    
      
    
}