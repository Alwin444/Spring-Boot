package com.demo.sec9.service;

import com.demo.sec9.dto.PostDTO;

import java.util.List;
public interface PostService {
    PostDTO addPost(Long id,PostDTO postDTO);
    PostDTO getPostById(Long id,Long postId);
    List<PostDTO> getAllPost(Long id);
    PostDTO updatePost(Long id,Long postId,PostDTO postDTO);
    void  deletePost(Long id,Long postId);

}
