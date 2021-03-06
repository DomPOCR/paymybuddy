            package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.UserService;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.FileHandler;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

   // Liste des users

    @GetMapping(value = "/Users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUsers() {

        return userService.findAll();
    }

    // Liste des users par email

    @GetMapping(value = "/Users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User userByEmail(@PathVariable String email) {

        return userService.findByEmail(email);
    }

    // Comptage des users

    @GetMapping(value = "/UsersCount")
    @ResponseStatus(HttpStatus.OK)
    public long usersCount() {

        return userService.count();
    }

    // Ajout d'un user
    @PostMapping(value = "/AddUser")
    public ResponseEntity<User> addUser(@RequestBody UserDto addUser) throws Exception {

        userService.addUser(addUser);

        logger.info("Add User for userid OK " + addUser.toString());
        return new ResponseEntity(addUser, HttpStatus.CREATED);
    }

    // Ajout d'un buddy
    // champs requis : email d'un user enregistré
    @PostMapping("/AddBuddy/{userId}")
    //public ResponseEntity <UserDto> AddBuddy(@RequestBody UserDto addBuddy, @PathVariable Integer userId ) throws Exception {
    public ResponseEntity <User> addBuddy(@RequestBody User addBuddy, @PathVariable Integer userId ) throws Exception {

        User userUpdated = userService.addBuddy(addBuddy.getEmail(),userId);

        logger.info("Add buddy for userid " + userId + " OK " + userUpdated.toString());
        return new ResponseEntity(userUpdated, HttpStatus.OK);
    }

    // Connect
    @GetMapping(value = "/Connect")
    public ResponseEntity<Boolean> connectUser(@RequestBody UserDto userToConnect) throws Exception {

        if (userService.connectUser(userToConnect)) {
            logger.info("User connected " + userToConnect.toString());
            return new ResponseEntity(userToConnect, HttpStatus.OK);
        } else {
            logger.error("Connect user KO : " + userToConnect.toString());
            throw new DataNotExistException("user or password invalid");
        }
    }
}

