package com.ubb.blogapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAndFollowersAndPostsDto {
    private String name;
    private List<FollowerDto> followers;
    private List<PostDto> posts;
}
