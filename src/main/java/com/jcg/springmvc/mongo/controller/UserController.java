package com.jcg.springmvc.mongo.controller;

import java.util.List;

import javax.annotation.Resource;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcg.springmvc.mongo.model.User;
import com.jcg.springmvc.mongo.service.UserService;

@Controller
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="groupService")
    private GroupService groupService;
	
	@Resource(name="postService")
	private PostService postService;
	
	/**
     * This method returns profile page.
     */
	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String profile(ModelMap model) {
		List<Group> groupList = groupService.getAll();
        model.addAttribute("groups", groupList);	
        List<Post> postList = postService.getAll();
        model.addAttribute("posts", postList);
		return "/profile";
	}
	
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
	public String showForm(ModelMap model) {		
		User user = new User();
		model.addAttribute("userAttr", user);
		return "addUser";
	}
	
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public String saveUser(ModelMap model, @ModelAttribute("userAttr") User user) {		
		if(user.getId() != null && !user.getId().trim().equals("")) {
			userService.edit(user);
		} else {
			userService.add(user);
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String showUserList(ModelMap model) {		
		User user = new User();
		model.addAttribute("userAttr", user);
		log.debug("Request to fetch all users from the mongo database");
		List<User> userList = userService.getAll();		
		model.addAttribute("users", userList);
		return "admin";
	}
     
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
    public String updateUser(
            ModelMap model, @PathVariable String id) {
    	User user = userService.findUserId(id);
    		model.addAttribute("userAttr", user);
        model.addAttribute("edit", true);
        return "addUser";
    }
    
	 /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(User user, 
            ModelMap model, @PathVariable String id) {
 
    	if(user.getId() != null && !user.getId().trim().equals("")) {
			userService.edit(user);
		} else {
			userService.add(user);
		}
        return "admin";
    }
    
    /**
     * This method will delete an user by it's ID value.
     */
    @RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
    
   	userService.delete(id);
        return "redirect:/admin";
    }
	
}