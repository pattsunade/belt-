package com.patriciadelgado.eventos.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull(message = "required")
    @Size(min = 4, max = 10)
    private String name;
    @NotBlank
   @NotNull(message = "required")
    private String location;
    @NotBlank
     @NotNull(message = "required")
    private String state;
    
     @NotNull(message = "required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User host;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User>  usersAttending = new ArrayList<>();

    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
	private List<Message> messages;
    public Event() {
    }

   

    public Event(Long id, String name, String location, String state, @NotNull(message = "required") Date date,
            User host, List<User> usersAttending, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.state = state;
        this.date = date;
        this.host = host;
        this.usersAttending = usersAttending;
        this.messages = messages;
    }
    



    public Event(String name, String location, String state, @NotNull(message = "required") Date date, User host,
            List<User> usersAttending, List<Message> messages) {
        this.name = name;
        this.location = location;
        this.state = state;
        this.date = date;
        this.host = host;
        this.usersAttending = usersAttending;
        this.messages = messages;
    }

     public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }
    public List<User> getUsersAttending() {
        return usersAttending;
    }

    public void setUsersAttending(List<User> usersAttending) {
        this.usersAttending = usersAttending;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(Message messages) {
        this.messages.add(messages);
    }

    public void joinUser(User user) {
        this.usersAttending.add(user);
    }
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
