/*
Admin controller
 */
package com.apusilicon.blog.controller;

import com.apusilicon.blog.data.AdminDao;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.apusilicon.blog.logic.AuthMan;
import com.apusilicon.blog.classes.imaginery.Owner;
import com.apusilicon.blog.classes.imaginery.Token;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author earlcameron
 */
@RestController
@RequestMapping(value = "auth")
public class AdminController {

    @Autowired
    private AuthMan authMan;

    @Autowired
    private AdminDao owners;

    Logger logger = LoggerFactory.getLogger(blogController.class);

    @CrossOrigin
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, String> login(
            HttpServletRequest request) {
        //hashmap to store json response
        HashMap<String, String> map = new HashMap<>();

        // store header info in intermediate variable
        String email = request.getHeader("email");
        String password = request.getHeader("password");

        //query database for user by email
        Owner owner = owners.findFirstByEmail(email);

        //current request hash intermediate variable
        String passwordString = email + password;

        //verify users credentials by comparing to user in database
        if (authMan.match(passwordString, owner.getSaltedPassword())) {
            Token token = new Token();
            Gson gson = new Gson();
            //success message
            map.put("isSuccess", "true");
            //access directives for the blog
            //w = write
            //p = publish
            //d = delete
            //c = create
            if (owner.getRole().ordinal() == 0) {
                token.setBlog("wpdc");
            } else {
                token.setBlog("");
            }
            //access directives for the database
            if (owner.getRole().ordinal() == 0) {
                token.setDatabase("wpdc");
            } else {
                token.setDatabase("c");
            }
            // saving email
            map.put("email", owner.getEmail());
            // saving role
            map.put("role", owner.getRole().toString());
            // return new map with the token
            map.put("token", authMan.createToken(gson.toJson(token), owner.getSaltedPassword()));
            return map;
        } else {
            map.put("isSuccess", "false");
            map.put("message", "Email or Password was incorrect.");
            return map;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public Map<String, String> signUpUser(
            HttpServletRequest request) {

        try {
            String email = request.getHeader("email");
            String password = request.getHeader("password");

            Owner user = new Owner();
            user.setEmail(email);
            user.setSaltedPassword(authMan.encode(email + password));
            if (owners.count() < 1) {
                user.setRole(Owner.Role.ADMIN);
            } else {
                user.setRole(Owner.Role.DBUSER);
            }
            owners.save(user);

            logger.debug("User with email: " + email + " and role: " + user.getRole() + " saved!");

            return new HashMap<String, String>() {
                {
                    put("isSuccess", "True");
                    put("message", "User Sucessfully Signed Up!");
                }
            };

        } catch (NullPointerException ex) {
            ex.printStackTrace();

            return new HashMap<String, String>() {
                {
                    put("isSuccess", "True");
                    put("exception", ex.toString());
                }
            };
        }

    }
}
