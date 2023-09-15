package com.vusalaxndzde.redisspringboot.controller;

import com.vusalaxndzde.redisspringboot.entity.Post;
import com.vusalaxndzde.redisspringboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findById(postId));
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> update(@RequestBody Post post, @PathVariable Long postId) {
        return ResponseEntity.ok(postService.update(postId, post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }

}
