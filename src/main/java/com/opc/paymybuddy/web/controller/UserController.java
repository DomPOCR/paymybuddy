            package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.UserService;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE) // Indique que l'API consomme du JSON
public class UserController {

    @Autowired
    private UserService userService;

    // Liste des users

    @GetMapping(value = "/Users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUsers() {

        return userService.findAll();
    }

    // Liste des users par email

    @GetMapping(value = "/Users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User UserByEmail(@PathVariable String email) {

        return userService.findByEmail(email);
    }

    // Comptage des users

    @GetMapping(value = "/UsersCount")
    @ResponseStatus(HttpStatus.OK)
    public long UsersCount() {

        return userService.count();
    }

    // Ajout d'un user
    @RequestMapping(value = "/AddUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto addUser) throws Exception {

        userService.addUser(addUser);

    }
}

