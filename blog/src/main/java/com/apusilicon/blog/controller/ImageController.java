package com.apusilicon.blog.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.apusilicon.blog.classes.imaginery.Image;
import com.apusilicon.blog.classes.imaginery.Owner;
import com.apusilicon.blog.classes.imaginery.Response;
import com.apusilicon.blog.classes.imaginery.Token;
import com.apusilicon.blog.data.AdminDao;
import com.apusilicon.blog.data.ImageDao;
import com.apusilicon.blog.data.TempLaptopDAO;
import com.apusilicon.blog.logic.AuthMan;
import com.apusilicon.blog.logic.ImageHandler;
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

@RestController
@RequestMapping(value = "image")
public class ImageController {

    @Autowired
    private ImageDao images;

    @Autowired
    private AdminDao owners;

    @Autowired
    private AuthMan auth;

    @Autowired
    private TempLaptopDAO laptops;

    Logger logger = LoggerFactory.getLogger(blogController.class);

    @CrossOrigin
    @RequestMapping(value = "/all/{pageno}", method = RequestMethod.GET)
    public Page<Image> allImages(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "10") int pageSize,
            @PathVariable("pageno") int pageno) {

        if (!filter.equals("")) {
            return images.findAll(PageRequest.of(pageno, pageSize));
        } else {
            return images.findAll(PageRequest.of(pageno, pageSize));
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(
            HttpServletRequest request,
            @RequestBody String body) {
        // store header info into intermediate variables
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        String fileName = request.getHeader("img");
        //fileName = fileName.substring(0,fileName.length()-4);
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
                // check if file exists and delete it
                for (Image img : images.findAll()) {
                    if (img.getFileName().equals(fileName)) {
                        images.delete(img);
                        break;
                    }
                }

                //if file doesn't exist, create new file
                Image image = new Image();
                image.setFileName(fileName);//+"."+ImageHandler.getExtension(body));
                images.save(image);

                System.out.println(body);
                File file = new File("/Users/earlcameron/Desktop/lc101/apusilicon/blog/src/main/resources/static/images/" + fileName);

                ImageHandler.b64ToImage(body, file);

                //return message
                return new Response()
                        .setSuccess("true")
                        .setMessage("Files Saved Successfully!")
                        .send();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Response()
                    .setSuccess("false")
                    .setError(e.toString())
                    .send();
        }
        return new Response()
                .setSuccess("false")
                .setError("Bad Token/Email.")
                .send();
    }

}
