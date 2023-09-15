package com.vusalaxndzde.redisspringboot.service;

import com.vusalaxndzde.redisspringboot.entity.Post;
import com.vusalaxndzde.redisspringboot.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Cacheable("posts")
    public List<Post> findAll() {
        sleep();
        return postRepository.findAll();
    }

    @Cacheable(value = "post", key = "#id")
    public Post findById(Long id) {
        sleep();
        return postRepository.findById(id).orElseThrow();
    }

    @CacheEvict(value = "posts", allEntries = true)
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @CacheEvict(value = "posts", allEntries = true)
    @CachePut(value = "post", key = "#id")
    public Post update(Long id, Post post) {
        postRepository.findById(id).orElseThrow();
        post.setId(id);
        return postRepository.save(post);
    }

    @CacheEvict(value = {"post", "posts"}, allEntries = true)
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
