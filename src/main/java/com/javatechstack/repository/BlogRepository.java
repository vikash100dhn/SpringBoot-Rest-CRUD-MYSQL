package com.javatechstack.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javatechstack.model.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer>{
	
	List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);

}
