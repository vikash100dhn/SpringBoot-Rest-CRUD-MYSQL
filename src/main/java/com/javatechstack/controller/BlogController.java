package com.javatechstack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javatechstack.model.Blog;
import com.javatechstack.repository.BlogRepository;

@RestController
public class BlogController {
	
	@Autowired
	private BlogRepository repository;
	
	/**
	 * Test method to check if the servies are running
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/")
    public String index() {
        return "Congratulations from BlogController.java";
    }
	
	/**
	 * 
	 * method to get list of blogs
	 * 
	 * 
	 * @return
	 */
	@GetMapping("/blog")
	public List<Blog> getAllBlog()
	{
		List<Blog> blogs = new ArrayList<>();
		return (List<Blog>) repository.findAll();
	}
	/**
	 * Method to get a blog using id
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/blog/{id}")
	public Blog getBlog(@PathVariable String id)
	{
		Blog blog = new Blog();
		int blogId = Integer.parseInt(id);
		Optional<Blog> dbBlog= repository.findById(blogId);
		if(dbBlog.isPresent())
		{
			blog = dbBlog.get();
		}
		else {
			//send the response that the entity doesn't exist 
		}
		return blog;
	}
	/**
	 * This method searches for blog posts
	 * 
	 * @param body
	 * @return List of Blogs
	 */
	@PostMapping
	public List<Blog> searchBlogs(@RequestBody Map<String, String> body)
	{
		String searchTerm = body.get("text");
		return repository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
	}
	/**
	 * Creates a new blog
	 * 
	 * @param blog
	 * @return
	 */
	@PostMapping("/blog")
	public Blog createBlog(@RequestBody Map<String, String> body)
	{
		String title = body.get("title");
		String content = body.get("content");
		return repository.save(new Blog(title, content));
	}
	
	/**
	 * 
	 * This method updates a blog post
	 * 
	 * @param id
	 * @param blog
	 * @return
	 */
	@PutMapping("/blog")
	public Blog update( @RequestBody Blog blog)
	{
		return repository.save(blog);
	}
	/**
	 * 
	 * This method deletes the blog post
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/blog/{id}")
	public boolean delete(@PathVariable String id)
	{
		int blogId = Integer.parseInt(id);
		repository.deleteById(blogId);
		return true;
	}
}
