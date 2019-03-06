package com.apusilicon.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author earlcameron
 */
@Controller
public class PageServeController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){
        return "app";
    }
}
