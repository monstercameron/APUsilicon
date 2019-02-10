package com.apusilicon.blog.controller;

import com.apusilicon.blog.data.BlogDao;
import com.apusilicon.blog.data.AdminDao;
import com.apusilicon.blog.classes.imaginery.Blog;
import com.apusilicon.blog.classes.imaginery.Owner;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String index() {
        return "works";
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ArrayList<Blog> allBlogs(
                                    @RequestParam(defaultValue="") String filter) {
        return blogSafe.findByTagsContaining(filter);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBlog(HttpServletRequest request, @RequestBody String body) {

        Owner owner = new Owner();
        owner.setName("name");
        owner.setEmail("email.com");
        owner.setHash("1111111111111111");
        owner.setRole(Owner.Role.ADMIN);
        admins.save(owner);

        String image = "http://apusilicon.com/wp-content/uploads/2016/04/APUSILICON-LOGO-2016-563x480.png";

        Blog blog = new Blog();
        blog.setBody(body);
        blog.setTags(request.getHeader("tags"));
        blog.setTitle(request.getHeader("title"));
        blog.setTitleImage(image);
        blog.setDate(request.getHeader("aDate"));
        blog.setCategory(request.getHeader("category"));
        blog.setPreview("preview");
        blog.setOwner(owner);

        System.out.println(blog);

        blogSafe.save(blog);

        return "Blog Added";
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBlog(HttpServletRequest request, @RequestBody String body) {

        Blog blog = blogSafe.findFirstByHash(request.getHeader("hash")).get(0);
        blog.setBody(body);
        blog.setTags(request.getHeader("tags"));
        blog.setTitle(request.getHeader("head"));
        blog.setDate(request.getHeader("aDate"));
        blog.setCategory(request.getHeader("category"));
        blog.setPreview("preview");

        //System.out.println(blog);
        blogSafe.save(blog);

        return "Blog Edited.";
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/findbyhash", method = RequestMethod.POST)
    public Blog fetchBlog(HttpServletRequest request) {

        System.out.println("func ran with header " + request.getHeader("hash"));

        ArrayList<Blog> blog= blogSafe.findFirstByHash(request.getHeader("hash"));

        //System.out.println(blog.toString());

        return blog.get(0);
    }
}
