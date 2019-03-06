package com.apusilicon.blog.controller;

import com.apusilicon.blog.classes.imaginery.Owner;
import com.apusilicon.blog.classes.imaginery.Response;
import com.apusilicon.blog.classes.imaginery.Token;
import com.apusilicon.blog.classes.real.APU;
import com.apusilicon.blog.classes.real.TempLaptop;
import com.apusilicon.blog.data.APUDao;
import com.apusilicon.blog.data.AdminDao;
import com.apusilicon.blog.data.TempLaptopDAO;
import com.apusilicon.blog.logic.AuthMan;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author monstercameron
 */
@RestController
@RequestMapping(value = "database")
public class DatabaseController {

    @Autowired
    private AdminDao owners;

    @Autowired
    private AuthMan auth;

    @Autowired
    private TempLaptopDAO laptops;

    @Autowired
    private APUDao apus;

    Logger logger = LoggerFactory.getLogger(blogController.class);

    @CrossOrigin
    @RequestMapping(value = "laptops/{pageno}", method = RequestMethod.GET)
    public Page<TempLaptop> getAllDevices(
            @RequestParam(defaultValue = "false") String enabled,
            @RequestParam(defaultValue = "100") int pageSize,
            @PathVariable("pageno") int pageno) {

        int count = (laptops.count() < 1) ? 1 : (int) laptops.count();

        switch (enabled) {
            case "enabled":
                return laptops.findByEnabled(enabled, PageRequest.of(pageno, count));
            default:
                return laptops.findByEnabled(enabled, PageRequest.of(pageno, count));
        }
    }

    @CrossOrigin
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String saveLaptop(
            HttpServletRequest request,
            @RequestBody String body) {

        // store header info into intermediate variables
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        // query database for owner matching email
        Owner owner;
        // declare the token
        Token tokenObj;
        // init custom lightweight response class
        Response response = new Response();

        try {
            owner = owners.findFirstByEmail(email);
            tokenObj = auth.parseToken(token, owner.getSaltedPassword());

            if (auth.canWriteBlog(tokenObj)) {
                //deserialize json into object
                Gson gson = new Gson();
                TempLaptop laptop = gson.fromJson(body, TempLaptop.class);
                laptops.save(laptop);
                logger.debug("Laptop saved, ID:" + laptop.getId());
                logger.debug("Laptop saved, ID:" + laptop.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // if error
            return response
                    .setError(e.toString())
                    .setStatus("false")
                    .send();
        }
        // if no write permissions
        return response
                .setError("No Write Permissions")
                .setStatus("false")
                .send();
    }

    @CrossOrigin
    @RequestMapping(value = "/apu/add", method = RequestMethod.POST)
    public String addAPU(
            HttpServletRequest request,
            @RequestBody String body) {
        // store header info into intermediate variables
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        // query database for owner matching email
        Owner owner;
        // declare the token
        Token tokenObj;
        // init custom lightweight response class
        Response response = new Response();
        // init gson
        Gson gson = new Gson();

        try {
            owner = owners.findFirstByEmail(email);
            tokenObj = auth.parseToken(token, owner.getSaltedPassword());
            if (auth.canWriteBlog(tokenObj)) {
                System.out.println(body);
                APU apu = gson.fromJson(body, APU.class);
                apus.save(apu);
                return response
                        .setSuccess("true")
                        .setMessage("APU Saved Sucessfully!")
                        .send();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return response
                    .setSuccess("false")
                    .setError(e.toString())
                    .send();
        }
        return response
                .setSuccess("false")
                .setError("No Write Permissions!")
                .send();
    }

    @RequestMapping(value = "apu", method = RequestMethod.GET)
    public Page<APU> getAllAPU(
            @RequestParam(defaultValue = "false") String enabled,
            @RequestParam(defaultValue = "100") int pageSize){
    return apus.findAll(PageRequest.of(0, pageSize));
    }
}
