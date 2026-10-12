package com.demo.sec9.service.impl;

import com.demo.sec9.dto.PostDTO;
import com.demo.sec9.entity.Post;
import com.demo.sec9.entity.User;
import com.demo.sec9.exception.BadException;
import com.demo.sec9.exception.ResourceNotFoundException;
import com.demo.sec9.repository.PostRepository;
import com.demo.sec9.repository.UserRepository;
import com.demo.sec9.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceIMPL implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public PostDTO addPost(Long id, PostDTO postDTO) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());
        post.setUser(user); //Note this line.
        Post post1 = postRepository.save(post);
        PostDTO postDTO1 = new PostDTO();
        postDTO1.setId(post1.getId());
        postDTO1.setTitle(post1.getTitle());
        postDTO1.setContent(post1.getContent());
        postDTO1.setDescription(post1.getDescription());
        postDTO1.setUserId(post1.getUser().getId()); //Note this line.
        return postDTO1;
    }

    @Override
    public PostDTO getPostById(Long id,Long postId) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
        if(!post.getUser().getId().equals(user.getId())){
            throw new ResourceNotFoundException("No accses for this post");
        }
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        postDTO.setUserId(post.getUser().getId());
        return postDTO;
    }

    @Override
    public List<PostDTO> getAllPost(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
        List<Post> posts = postRepository.findByUserId(id);
        return posts.stream().map(post -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setDescription(post.getDescription());
            postDTO.setUserId(post.getUser().getId());
            return postDTO;
        }).toList();
    }

    @Override
    public PostDTO updatePost(Long id, Long postId, PostDTO postDTO) {
        User user =  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
        if(!post.getUser().getId().equals(user.getId())){
            throw new BadException("No accses for this post");
        }
        Post updatePost = new Post();
        updatePost.setTitle(postDTO.getTitle());
        updatePost.setContent(postDTO.getContent());
        updatePost.setDescription(postDTO.getDescription());
        updatePost.setUser(user);

        Post savePost = postRepository.save(post);

        PostDTO returnDTO = new PostDTO();
        returnDTO.setId(savePost.getId());
        returnDTO.setTitle(savePost.getTitle());
        returnDTO.setContent(savePost.getContent());
        returnDTO.setDescription(savePost.getDescription());
        returnDTO.setUserId(savePost.getUser().getId());

        return returnDTO;
    }

    @Override
    public void deletePost(Long id, Long postId) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
        if(!post.getUser().getId().equals(user.getId())){
            throw new BadException("No accses for this post");
        }
        postRepository.delete(post);

    }
}
