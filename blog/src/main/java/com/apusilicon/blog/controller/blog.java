package com.apusilicon.blog.controller;

import com.apusilicon.blog.data.BlogDao;
import com.apusilicon.blog.classes.imaginery.Blog;
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
    
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "works";
    }
    
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBlog(){
        
        Blog blog = new Blog();
        blog.setBody("");
        blog.setTags("");
        blog.setTitle("");
        blog.setTitleImage("");
        blog.setDate("");
        blog.setCategory("");
        blog.setPreview("");
        
        blogSafe.save(blog);
        
        return "Blog Added";
    }
}
