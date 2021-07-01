package com.ubb.blogapp.web.controller;

import com.ubb.blogapp.core.service.AppUserService;
import com.ubb.blogapp.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/user-hello", method = RequestMethod.POST)
    NameDto getUserName(@RequestBody Long id){
        log.trace("getUserName - method entered, id={}",id);
        //var longId = Long.getLong(id);
        var result = new NameDto(appUserService.find(id).getName());
        log.trace("getUserName: result={}", result);
        return result;
    }

    @RequestMapping(value = "/userAndFollowers/{id}", method = RequestMethod.GET)
    UserAndFollowersDto getUserWithFollowers(@PathVariable Long id){
        log.trace(" getUserWithFollowers - method entered, id={}",id);
        var user = appUserService.findWithFollowers(id);
        var result = new UserAndFollowersDto();
        result.setName(user.getName());
        var followers = new ArrayList<FollowerDto>();
        user.getFollowers().forEach(follower -> {
            followers.add(new FollowerDto(follower.getId(), follower.getName()));
        });
        result.setFollowers(followers);
        log.trace(" getUserWithFollowers: result={}", result);
        return result;
    }

    @RequestMapping(value = "/userAndFollowersAndPosts/{id}", method = RequestMethod.GET)
    UserAndFollowersAndPostsDto getUserWithFollowersAndPosts(@PathVariable Long id){
        log.trace(" getUserWithFollowersAndPosts - method entered, id={}",id);
        var user = appUserService.findWithPostsAndFollowers(id);
        var result = new UserAndFollowersAndPostsDto();
        result.setName(user.getName());
        var followers = new ArrayList<FollowerDto>();
        var posts = new ArrayList<PostDto>();
        user.getFollowers().forEach(follower -> followers.add(new FollowerDto(follower.getId(), follower.getName())));
        user.getPosts().forEach(post -> {
            var postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setTitle(post.getTitle());
            var comments = new ArrayList<String>();
            post.getComments().forEach(c -> comments.add(c.getText()));
            postDto.setComments(comments);
            posts.add(postDto);
        });
        result.setFollowers(followers);
        result.setPosts(posts);
        log.trace(" getUserWithFollowersAndPosts: result={}", result);
        return result;
    }
}
