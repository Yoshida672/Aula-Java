package br.com.fiap.apisecurity.controller;

import br.com.fiap.apisecurity.entity.User;
import br.com.fiap.apisecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
