/*
Admin controller
 */
package com.apusilicon.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.apusilicon.blog.logic.AuthMan;

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
    
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(
                            HttpServletRequest request){
        
        String email = request.getHeader("email");
        String creds = request.getHeader("creds");

        HashMap<String, String> map = new HashMap<>();
        map.put(email, creds);

        map.put(email, authMan.encode(creds));

        return map;
    }
}
