package com.patriciadelgado.eventos.Models;


import java.util.Date;
// import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull(message = "required")
    private String firstName;

    @NotBlank
    @NotNull(message = "required")
    private String lastName;

    @NotBlank
    @NotNull(message = "required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank
    @NotNull(message = "required")
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;

    @NotBlank
    @NotNull(message = "required")
    private String location;
    
    @NotBlank
    @NotNull(message = "required")
    private String state;

    @NotBlank
    @NotNull(message = "required")
    @Transient
    private String passwordConfirmation;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date updatedAt;

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private List<Event> hostedEvents;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_users", 
        joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @OneToMany(mappedBy = "user", fetch =  FetchType.LAZY)
    private List<Message> messages;
    

    

    public User() {
    }
 
    public User(Long id, String firstName, String lastName, @Email(message = "Email must be valid") String email,
            @Size(min = 5, message = "Password must be greater than 5 characters") String password, String location,
            String state, String passwordConfirmation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.state = state;
        this.passwordConfirmation = passwordConfirmation;
    }

    public User(String firstName, String lastName, @Email(message = "Email must be valid") String email,
            @Size(min = 5, message = "Password must be greater than 5 characters") String password, String location,
            String state, String passwordConfirmation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.state = state;
        this.passwordConfirmation = passwordConfirmation;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
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


    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
