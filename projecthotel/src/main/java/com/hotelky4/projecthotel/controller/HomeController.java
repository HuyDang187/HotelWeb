package com.hotelky4.projecthotel.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "index.html";
    }
    @GetMapping("/about")
    public String about(){return "about.html";}
    @GetMapping("/contact")
    public String contact(){return "contact.html";}
    @GetMapping("/restaurant")
    public String restaurant(){return "restaurant.html";}
    @GetMapping("/blog")
    public String blog(){return "blog.html";}
    @GetMapping("/blogDetail")
    public String blogDetail(){return "blog-single.html";}


}
