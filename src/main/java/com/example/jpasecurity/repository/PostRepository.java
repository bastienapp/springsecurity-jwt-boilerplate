package com.example.jpasecurity.repository;

import com.example.jpasecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
