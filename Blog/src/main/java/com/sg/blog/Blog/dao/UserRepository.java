package com.sg.blog.Blog.dao;

import com.sg.blog.Blog.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Stuart
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findByUsername(String username);
}
