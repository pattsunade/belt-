package com.patriciadelgado.eventos.Services;

import java.util.List;
import java.util.Optional;

import com.patriciadelgado.eventos.Models.User;
import com.patriciadelgado.eventos.Repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

     public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // registrar el usuario y hacer Hash a su password
    
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // encontrar un usuario por su email
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
     public List<User> allUser() {
        return userRepository.findAll();
    }
    
    // encontrar un usuario por su id

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public User update(User user) {
        return userRepository.save(user);
    }
    
    // autenticar usuario

    public boolean authenticateUser(String email, String password) {

        // primero encontrar el usuario por su email

        User user = userRepository.findByEmail(email);

        // si no lo podemos encontrar por su email, retornamos false

        if(user == null) {
            return false;
        } else {
            // si el password coincide devolvemos true, sino, devolvemos false
            
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
