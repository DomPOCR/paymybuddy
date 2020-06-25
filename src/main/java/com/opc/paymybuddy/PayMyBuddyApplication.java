package com.opc.paymybuddy;

import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.User;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@EnableEncryptableProperties
@SpringBootApplication
public class PayMyBuddyApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(PayMyBuddyApplication.class, args);

        // Test de la connexion JPA
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println(userDao.findAll());

        // Test liaison entre user et BankAccount
        List<User> userList = userDao.findAll();
        System.out.println(userList.get(0).getListBankAccounts().get(0).getIban());


        // SpringApplication.run(PayMyBuddyApplication.class, args);//
    }

}
