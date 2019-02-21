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
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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
public class Admin {

    @Autowired
    private AuthMan authMan;

    @Autowired
    private AdminDao owners;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(
            HttpServletRequest request) {
        //hashmap to store json response
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> responseMap = new HashMap<>();

        // store header info in intermediate variable
        String email = request.getHeader("email");
        String creds = request.getHeader("creds");

//        //query database for user by email
        Owner owner = owners.findFirstByEmail(email);

        //current request hash intermediate variable
        String passwordString = email + creds;

//        //verify users credentials by comparing to user in database
        if (authMan.match(passwordString, owner.getHash())) {
            //success message
            map.put("isSuccess", "true");
            //access directives for the blog
            map.put("blog", "w/p/d");
            //access directives for the database
            map.put("db", "w/p/d");
            // saving email
            responseMap.put("email", owner.getEmail());
            // return new map with the token
            responseMap.put("token", authMan.createToken(map, owner.getHash()));
            return responseMap;
        }else{
            map.put("isSuccess", "false");
            map.put("message", "Email or Password was incorrect.");
            return map;
        }

//        map.put("Email", email);
//        map.put("Password", creds);
//        map.put("input hash", authMan.encode(passwordString));
//        map.put("saved hash", owner.getHash());

        //return map;
    }
    
    @CrossOrigin
    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    public Map<String, String> decode(
                                    HttpServletRequest request){
        String token = request.getHeader("token");
        
        HashMap<String, String> map = new HashMap<>();
        return map;
    }
}
