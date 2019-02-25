package com.apusilicon.blog.controller;

import com.apusilicon.blog.logic.AuthMan;
import com.apusilicon.blog.logic.DatabaseManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author monstercameron
 */
@RestController
@RequestMapping(value = "database")
public class DatabaseController {
    
    @Autowired
    private AuthMan authMan;
    @Autowired
    private DatabaseManager dataMan;
    
    @CrossOrigin
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, String> saveLaptop(
            HttpServletRequest request,
            @RequestBody String body
    ){
        System.out.println("In the controller");
        System.out.println(body);
        
        ArrayList<String> dict = dataMan.kvPairs(body);
        
        dataMan.kvPairsToMap(dict);
    
        return new HashMap<String, String>();
    }
    
}
