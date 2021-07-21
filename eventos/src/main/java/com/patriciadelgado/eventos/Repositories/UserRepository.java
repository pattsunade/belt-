package com.patriciadelgado.eventos.Repositories;

import java.util.List;

import com.patriciadelgado.eventos.Models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   User findByEmail(String email);

   List<User> findAll();
}
