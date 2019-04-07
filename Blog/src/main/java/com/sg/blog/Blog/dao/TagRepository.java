package com.sg.blog.Blog.dao;

import com.sg.blog.Blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Stuart
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String>{

}