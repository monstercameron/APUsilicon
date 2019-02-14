package com.apusilicon.blog.logic;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class AuthMan{

    public String encode(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public Boolean match(String input, String storage){
        BCrypt.Result result = BCrypt.verifyer().verify(input.toCharArray(), storage);
        if(result.verified){
            return true;
        }
        return false;
    }
}