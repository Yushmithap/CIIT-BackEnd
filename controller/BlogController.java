package com.ciit.collaborativebackend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ciit.collaborativebackend.dao.BlogDAO;
import com.ciit.collaborativebackend.model.Blog;

@RestController
public class BlogController {
	
	@Autowired
	private Blog blog;
	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private HttpSession session;
	
	
	
	//post -> create
	//put --> update
	//get --> get
	//delete --> delete
	
	//@RequestMapping--> By default it is get mapping
	@GetMapping("/getAllBlogs")
	public List<Blog> getAllBlogs(){
		return blogDAO.getAllBlogs();
	}
	
	@PutMapping("/approveBlog/{id}")
	public Blog approveBlog(@PathVariable("id") Integer id){
		//get the blog based on blogId
		//if the blog does not exist dont approve
		//if the blog is already exists dont approve again
		//set the status as 'A'
		//call update method
		//check whether user is logged in or not
		//check whether user is admin or not
		if(session.getAttribute("loggedInUserId")==null){
			blog.setErrorCode("404");
			blog.setErrorMessage("Please login to approve the blog");
			return blog;
		}
		if(!session.getAttribute("loggedInUserRole").equals("admin")){
			blog.setErrorCode("404");
			blog.setErrorMessage("You are not authorized to approve this blog");
			return blog;
		}
		blog = blogDAO.getBlog(id);
		if(blog==null){
			blog = new Blog(); //to remove null pointer exception from console we write this statement
			blog.setErrorMessage("No blog exists with this id:"+id);
			blog.setErrorCode("404");
		}
		if(blog.getStatus().equals("A"))
		{
			blog.setErrorCode("404");
			blog.setErrorMessage("This blog is already approved:"+id);
			return blog;
		}
		blog.setStatus("A");
		if(blogDAO.update(blog)){
			blog.setErrorCode("200");
			blog.setErrorMessage("Successfully approved");
		}
		else{
			blog.setErrorCode("404");
			blog.setErrorMessage("Not able to approve the blog");
		}
		return blog;
		
		
	}
	
	@PutMapping("/rejectBlog/{id}")
	public Blog rejectBlog(@PathVariable("id") Integer id){
		if(session.getAttribute("loggedInUserId")==null){
			blog.setErrorCode("404");
			blog.setErrorMessage("Please login to reject the blog");
			return blog;
		}
		if(!session.getAttribute("loggedInUserRole").equals("admin")){
			blog.setErrorCode("404");
			blog.setErrorMessage("You are not authorized to reject this blog");
			return blog;
		}
		blog = blogDAO.getBlog(id);
		if(blog==null){
			blog = new Blog(); //to remove null pointer exception from console we write this statement
			blog.setErrorMessage("No blog exists with this id:"+id);
			blog.setErrorCode("404");
		}
		if(blog.getStatus().equals("A"))
		{
			blog.setErrorCode("404");
			blog.setErrorMessage("This blog is already approved:"+id);
			return blog;
		}
		blog.setStatus("R");
		blog.setReason("This blog doesn't contain necessary information");
		if(blogDAO.update(blog)){
			blog.setErrorCode("200");
			blog.setErrorMessage("You have successfully rejected the blog");
		}
		else{
			blog.setErrorCode("404");
			blog.setErrorMessage("You have not able to reject the blog");
		}
		return blog;
		
	}
	
	@PostMapping("/createBlog/")
	public Blog createBlog(@RequestBody Blog blog)
	{
		//First user should login to create a blog
		String loggedInUserID = (String) session.getAttribute("loggedInUserId");
		if(loggedInUserID==null){
			blog.setErrorCode("404");
			blog.setErrorMessage("Please login to create a blog");
			return blog;
		}
		blog.setStatus("N");
		blog.setUserId(loggedInUserID);
		blog.setDateTime(new Date());
		if(blogDAO.save(blog)){
			blog.setErrorCode("202");
			blog.setErrorMessage("You are successfully created blog");
			return blog;
		}
		else{
			blog.setErrorCode("404");
			blog.setErrorMessage("Not able to create the blog");
			return blog;
		}
	}
	
	
	
	
}
