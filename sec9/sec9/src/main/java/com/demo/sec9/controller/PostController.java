package com.demo.sec9.controller;

import com.demo.sec9.dto.PostDTO;
import com.demo.sec9.dto.UserDTO;
import com.demo.sec9.service.PostService;
import com.demo.sec9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("{id}/posts")
    public ResponseEntity<PostDTO> addPost(@PathVariable("id") Long id,@RequestBody PostDTO postDTO){
        PostDTO postDTO1 = postService.addPost(id,postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO1);
    }

    @GetMapping("{id}/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id,@PathVariable("postId") Long postId){
        PostDTO postDTO = postService.getPostById(id,postId);
        return ResponseEntity.status(HttpStatus.OK).body(postDTO);
    }

    @GetMapping("{id}/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(@PathVariable Long id){
        List<PostDTO> postDTO = postService.getAllPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postDTO);
    }

    public ResponseEntity<PostDTO> updatePosts(@PathVariable Long id,@PathVariable Long postId,@RequestBody PostDTO postDTO){
        PostDTO postDTO1 = postService.updatePost(id,postId,postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO1);
    }

    public ResponseEntity<String>  deletePosts(@PathVariable Long id,@PathVariable Long postId){
        postService.deletePost(id,postId);
        return ResponseEntity.status(HttpStatus.OK).body("Post has been deleted");
    }
}
