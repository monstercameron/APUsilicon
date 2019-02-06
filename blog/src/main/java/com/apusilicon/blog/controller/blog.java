package com.apusilicon.blog.controller;

import com.apusilicon.blog.data.BlogDao;
import com.apusilicon.blog.data.AdminDao;
import com.apusilicon.blog.classes.imaginery.Blog;
import com.apusilicon.blog.classes.imaginery.Owner;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author monstercameron
 */
@Controller
@RequestMapping("blog")
public class blog {
    
    @Autowired
    private BlogDao blogSafe;
    
    @Autowired
    private AdminDao admins;
    
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "works";
    }
    
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Blog> allBlogs(){
       return blogSafe.findAll();
    }
    
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBlog(){
        
        Owner owner = new Owner();
        owner.setName("name");
        owner.setEmail("email.com");
        owner.setHash("1111111111111111");
        owner.setRole(Owner.Role.ADMIN);
        admins.save(owner);
        
        Blog blog = new Blog();
        blog.setBody("a");
        blog.setTags("a");
        blog.setTitle("a");
        blog.setTitleImage("a");
        blog.setDate("a");
        blog.setCategory("a");
        blog.setPreview("a");
        blog.setOwner(owner);
        
        blogSafe.save(blog);
        
        return "Blog Added";
    }
}
