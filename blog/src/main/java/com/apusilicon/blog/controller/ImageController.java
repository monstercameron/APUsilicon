package com.apusilicon.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import com.apusilicon.blog.classes.imaginery.Image;
import com.apusilicon.blog.data.ImageDao;
import com.apusilicon.blog.logic.ImageFunctions;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.SourceType;
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
@RequestMapping(value= "image")
public class ImageController{

    @Autowired
    private ImageDao images;

    @CrossOrigin
    @RequestMapping(value = "/all/{pageno}", method = RequestMethod.GET)
    public Page<Image> allImages(
                                    @RequestParam(defaultValue="") String filter,
                                    @RequestParam(defaultValue="10") int pageSize,
                                    @PathVariable("pageno") int pageno) {
 
        if(!filter.equals("")){
            return images.findAll(PageRequest.of(pageno,pageSize));
        }else{
            return images.findAll(PageRequest.of(pageno,pageSize));
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(
                                    HttpServletRequest request,
                                    @RequestBody String body) {
        try {
            // save header in local var
            String fileName = request.getHeader("name");

            // // check if file exists
            for (Image img : images.findAll()) {
                if(img.getFileName().equals(fileName)){
                    return "File already exists";
                }
            }

            //if file doesn't exist, create new file
            Image image = new Image();
            image.setFileName(fileName);
            images.save(image);

            System.out.println(body);
            File file = new File("blog/src/main/resources/static/images/"+fileName);

            ImageFunctions.b64ToImage(body, file);

            //return message
            return fileName + " Saved.";

        } catch (Exception e) {
            //return error
            return e.toString();
        }
    }

}