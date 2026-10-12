package com.demo.sec9.repository;


import com.demo.sec9.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    // Use a custom repository method when you need to retrieve or modify data based on specific conditions not covered by built-in JpaRepository methods.
    // Example: Find all posts belonging to a specific user.
    // Use @Query when you need to define a custom JPQL or SQL query or when a derived query method name is unsuitable.
    List<Post> findByUserId(Long id);
}
