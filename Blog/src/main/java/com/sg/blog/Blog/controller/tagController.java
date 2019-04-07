/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.Blog.controller;

import com.sg.blog.Blog.dao.PostRepository;
import com.sg.blog.Blog.dao.RoleRepository;
import com.sg.blog.Blog.dao.TagRepository;
import com.sg.blog.Blog.dao.UserRepository;
import com.sg.blog.Blog.entity.Post;
import com.sg.blog.Blog.entity.Tag;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kahin
 */
@Controller
public class tagController {
    @Autowired
    PostRepository postR;

    @Autowired
    RoleRepository roleR;

    @Autowired
    TagRepository tagR;

    @Autowired
    UserRepository userR;
    
    @GetMapping("/tags")
    public String tagPost(HttpServletRequest request, Model model){
      String tag =  request.getParameter("tag");
      Tag object = tagR.findById(tag).orElse(null);
      List <Post> posts = postR.findByApprovedTrueAndTagsContainingOrderByTimestamp(object);
      
      posts = posts.stream()
                // This is a bit of a cludge to avoid a more complex DB query
                .filter(p -> 
                        p.getStartdate().isBefore(LocalDate.now()) && p.getEnddate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Post::getTimestamp)
                        .reversed())
                .collect(Collectors.toList());
      
      model.addAttribute("posts", posts);
      model.addAttribute("selectedTag",tag);
      return "tagPage";
    }
}
