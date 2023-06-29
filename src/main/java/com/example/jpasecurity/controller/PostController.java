package com.example.jpasecurity.controller;

import com.example.jpasecurity.entity.Post;
import com.example.jpasecurity.repository.PostRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository posts;

    public PostController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Iterable findAll() {
        return posts.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Post findById(@PathVariable("id") Post post) {
        return post;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Post create(@RequestBody Post newPost) {
        return posts.save(newPost);
    }
}
