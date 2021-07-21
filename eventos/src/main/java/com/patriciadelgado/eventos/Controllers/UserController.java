package com.patriciadelgado.eventos.Controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.patriciadelgado.eventos.Models.Event;
import com.patriciadelgado.eventos.Models.Message;
import com.patriciadelgado.eventos.Models.User;
import com.patriciadelgado.eventos.Services.EventService;
import com.patriciadelgado.eventos.Services.MessageService;
import com.patriciadelgado.eventos.Services.UserService;
import com.patriciadelgado.eventos.Validator.UserValidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
     private final UserService userService;
     private final UserValidator userValidator;
     private final EventService eventService;
     private final MessageService messageService;
    
  
    
    public UserController(UserService userService, UserValidator userValidator, EventService eventService,
            MessageService messageService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.eventService = eventService;
        this.messageService = messageService;
        
    }


    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registroLog.jsp";
    }
    
    
    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
            BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registroLog.jsp";
        }
        User user2 = userService.registerUser(user);
       
        session.setAttribute("userId", user2.getId());
        return "redirect:/events";
    
    
    
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email,
            @RequestParam("password") String password, Model model, HttpSession session) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            User user = userService.findByEmail(email);
            session.setAttribute("userId", user.getId());
            return "redirect:/events";
        } else {
            model.addAttribute("error", "Invalid Credentials. Please try again. ");
            return "redirect:/registration";
        }
     }
   
    @GetMapping("/events")
    public String events(HttpSession session, Model model,
            @ModelAttribute("event") Event newEvent) {
        Long hostId = (Long) session.getAttribute("userId");
        User host = userService.findUserById(hostId);
        List<Event> eventIn = eventService.eventInState(host.getState());
        List<Event> eventOut = eventService.eventOutState(host.getState());
        if (hostId == null) {
            
            return "redirect:/registration";
            
        } else {
            model.addAttribute("user", host);
            model.addAttribute("eventIn", eventIn);
            model.addAttribute("eventOut", eventOut);
            return "events.jsp";
        }

        
     
    }
   @PostMapping("/events")
    public String createEvent(@Valid @ModelAttribute("event") Event event,
            BindingResult result, HttpSession session, Model model) {
        Long hostId = (Long) session.getAttribute("userId");
        User host = userService.findUserById(hostId);
        List<Event> eventIn = eventService.eventInState(host.getState());
        List<Event> eventOut = eventService.eventOutState(host.getState());
        if (result.hasErrors()) {
            model.addAttribute("eventIn", eventIn);
            model.addAttribute("eventOut", eventOut);
            model.addAttribute("user", host);
            return "events.jsp";
        } else {
            Event events = eventService.addEvent(event);
            event.setHost(host);
            event.joinUser(host);
            eventService.createEvent(events);

            return "redirect:/events";
        }
    }

   


       @GetMapping("/events/{eventId}/delete")
       public String deleteEvent(@PathVariable("eventId") Long eventId) {
           Event event = eventService.getEvent(eventId);
           List<User> userAttending = event.getUsersAttending();
           List<Message> messages = event.getMessages();
           for (User user : userAttending) {
               event.getUsersAttending().remove(user);
               userService.update(user);

           }
           for (Message message : messages) {
               message.setEvent(null);
               eventService.deleteEvent(message.getId());
           }
           eventService.deleteEvent(eventId);
           return "redirect:/events";
       }
        
       @GetMapping("/events/{id}/join")
       public String join(@PathVariable("id") Long id, HttpSession session) {
           Event event = eventService.getEvent(id);
           User user = userService.findUserById((Long) session.getAttribute("userId"));
           event.joinUser(user);
           eventService.updateEvent(event);
           return "redirect:/events";
       }

       @GetMapping("/events/{id}/cancel")
       public String cancel(@PathVariable("id") Long id, HttpSession session) {
           Event event = eventService.getEvent(id);
           User user = userService.findUserById((Long) session.getAttribute("userId"));
           List<User> asistentes = event.getUsersAttending();
           asistentes.remove(user);
           event.setUsersAttending(asistentes);
           eventService.updateEvent(event);
           return "redirect:/events";
       }
       
       @GetMapping("/events/{idE}/edit")
       public String edit(@PathVariable("idE") Long idE, Model model, HttpSession session) {
           Event editEvent = eventService.getEvent(idE);
           User user = userService.findUserById((Long) session.getAttribute("userId"));
           if (user.getId() == editEvent.getHost().getId()) {
               model.addAttribute("e", editEvent);
               return "editEvent.jsp";
           } else {
               return "redirect:/events";
           }
           
       }

       @PutMapping("/events/{idE}/edit")
       public String update(@Valid @ModelAttribute("e") Event event, BindingResult result,
               @PathVariable("idE") Long idE, Model model, HttpSession session) {
           if (result.hasErrors()) {
               return "/editEvent.jsp";
           } else {
               Event eventId = eventService.getEvent(idE);
               eventId.setName(event.getName());
               eventId.setDate(event.getDate());
               eventId.setLocation(event.getLocation());
               eventId.setState(event.getState());
               eventService.updateEvent(eventId);
               return "redirect:/events";

           }
       }

       @GetMapping("/events/{idM}")
       public String show(@ModelAttribute("message") Message message, 
       @PathVariable("idM") Long idM, Model model, HttpSession session) {
           Event event = eventService.getEvent(idM);
           List<Message> showMessages = event.getMessages();
           User user = userService.findUserById((Long) session.getAttribute("userId"));
           if (event.getId() == null) {
               return "redirect:/events";
           } else {
               for (Message message2 : showMessages) {
                   User user2 = message2.getUser();
                   model.addAttribute("user2", user2);
               }
               model.addAttribute("user", user);
               model.addAttribute("event", event);
               model.addAttribute("showMessages",showMessages);
               return "show.jsp";
           }
       }

       @PostMapping("/events/{idA}/a")
       public String createMessage(@Valid @ModelAttribute("message") Message message, 
        BindingResult result, @PathVariable("idA") Long idA, Model model, HttpSession session) {
           Event event = eventService.getEvent(idA);
           List<Message> showMessages = event.getMessages();
           if (result.hasErrors()) {
               model.addAttribute("showMessages", showMessages);
               model.addAttribute("event", event);
            return "show.jsp";
        } else {
            User user = userService.findUserById((Long) session.getAttribute("userId"));
            message.setEvent(event);
            message.setUser(user);
        
            messageService.saveComment(message);
            return "redirect:/events/" + idA;
        }
    }

 @RequestMapping("/logout")
 public String logout(HttpSession session) {
     session.invalidate();
     return "redirect:/login";

 }
 }

