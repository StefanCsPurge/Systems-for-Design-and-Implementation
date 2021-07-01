package com.ubb.blogapp.core.service;

import com.ubb.blogapp.core.model.AppUser;
import com.ubb.blogapp.core.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService{
    private static final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser find(Long id) {
        log.trace("find user - method entered: id={}", id);
        var opt = appUserRepository.findById(id);
        AppUser result = null;
        if(opt.isPresent())
            result = opt.get();
        log.trace("find user: result={}",result);
        return result;
    }

    @Override
    public AppUser findWithFollowers(Long id) {
        log.trace("findWithFollowers - method entered: id={}", id);
        var result = appUserRepository.findWithFollowers(id);
        log.trace("findWithFollowers: result={}",result);
        return result;
    }

    @Override
    public AppUser findWithPostsAndFollowers(Long id) {
        log.trace("findWithPostsAndFollowers - method entered: id={}", id);
        var result = appUserRepository.findWithPostsAndFollowers(id);
        log.trace("findWithPostsAndFollowers: result={}",result);
        return result;
    }
}
