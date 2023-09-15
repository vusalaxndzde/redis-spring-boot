package com.vusalaxndzde.redisspringboot.repository;

import com.vusalaxndzde.redisspringboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
