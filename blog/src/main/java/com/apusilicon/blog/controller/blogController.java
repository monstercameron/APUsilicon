package com.apusilicon.blog.controller;

import com.apusilicon.blog.data.BlogDao;
import com.apusilicon.blog.data.AdminDao;
import com.apusilicon.blog.classes.imaginery.Blog;
import com.apusilicon.blog.classes.imaginery.Owner;
import com.apusilicon.blog.logic.AuthMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("blog")
public class blogController {

    @Autowired
    private BlogDao blogSafe;

    @Autowired
    private AdminDao owners;

    @Autowired
    private AuthMan auth;

    Logger logger = LoggerFactory.getLogger(blogController.class);

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(
            HttpServletRequest request) {

        try {
            Owner owner = owners.findFirstByEmail(request.getHeader("email"));

            String token = request.getHeader("auth");
            Map<String, String> map = auth.parseToken(token, owner.getSaltedPassword());

            System.out.println(map.toString());

            return "{\"testing\":\"" + map.toString() + "\"}";

        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/all/{pageno}", method = RequestMethod.GET)
    public Page<Blog> allBlogs(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "10") int pageSize,
            @PathVariable("pageno") int pageno) {

        switch (type) {
            case "tag":
                return blogSafe.findByTagsContaining(filter, PageRequest.of(pageno, pageSize));
            case "category":
                return blogSafe.findByCategoryContaining(filter, PageRequest.of(pageno, pageSize));
            default:
                //System.out.println("default");
                return blogSafe.findAll(PageRequest.of(pageno, pageSize));
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBlog(
            HttpServletRequest request,
            @RequestBody String body) {

        // store header info in intermediate variable
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        //query database for owner matching email
        Owner owner = owners.findFirstByEmail(email);
        //parsing encrypted token
        Map<String, String> map = new HashMap<>();
        //return error message is parsig token fails
        try {
            map = auth.parseToken(token, owner.getSaltedPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("Could not parse incoming token.");
            return "{\"message\":\"No Write Permissions\"}";
        }
        try {
            //check decrypted token for write permissions
            if (auth.canWrite(map)) {
                String image = "http://apusilicon.com/wp-content/uploads/2016/04/APUSILICON-LOGO-2016-563x480.png";
                Blog blog = new Blog();
                blog.setBody(body);
                blog.setTags(request.getHeader("tags"));
                blog.setTitle(request.getHeader("head"));
                blog.setDate(request.getHeader("aDate"));
                blog.setCategory(request.getHeader("category"));
                blog.setPreview("preview");
                blog.setOwner(owner);
                if (request.getHeader("image") == null || request.getHeader("image") == "") {
                    //use default image
                    blog.setTitleImage(image);
                } else {
                    //use specified image
                    blog.setTitleImage(request.getHeader("image"));
                }
                System.out.println(blog);
                blogSafe.save(blog);
                return "{\"message\":\"Blog added\"}";
            }

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return "{\"message\":\"No Write Permissions\"}";
    }

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBlog(
            HttpServletRequest request,
            @RequestBody String body) {
        
        // store header info in intermediate variable
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        //query database for owner matching email
        Owner owner = owners.findFirstByEmail(email);
        //parsing encrypted token
        Map<String, String> map = new HashMap<>();
        //return error message is parsig token fails
        try {
            map = auth.parseToken(token, owner.getSaltedPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("Could not parse incoming token.");
            return "{\"message\":\"No Write Permissions\"}";
        }
        try {
            //check decrypted token for write permissions
            if (auth.canWrite(map)) {
                Blog blog = blogSafe.findFirstByHash(request.getHeader("hash")).get(0);
                blog.setBody(body);
                blog.setTags(request.getHeader("tags"));
                blog.setTitleImage(request.getHeader("image"));
                blog.setTitle(request.getHeader("head"));
                blog.setDate(request.getHeader("aDate"));
                blog.setCategory(request.getHeader("category"));
                blog.setPreview("preview");
                //System.out.println(blog);
                blogSafe.save(blog);
                return "{\"message\":\"Blog Updated.\"}";
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return "{\"message\":\"No Write Permissions\"}";
    }

    @CrossOrigin
    @RequestMapping(value = "/findbyhash", method = RequestMethod.POST)
    public Blog fetchBlog(HttpServletRequest request) {

        System.out.println("func ran with header " + request.getHeader("hash"));

        ArrayList<Blog> blog = blogSafe.findFirstByHash(request.getHeader("hash"));

        //System.out.println(blog.toString());
        return blog.get(0);
    }
}
