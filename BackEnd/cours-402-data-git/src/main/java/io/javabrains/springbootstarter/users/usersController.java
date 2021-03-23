package io.javabrains.springbootstarter.users;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.relationship.RelationshipService;

/**
 * 
 * @author Xin Wang
 *
 * A class for user controller
 */
@RestController
public class usersController {
	
	/**
	 * instance variable for user service
	 */
	@Autowired
	private UsersService usersService;
//	private RelationshipService relationshipService;
	
	/**
	 *  get all users
	 * @return all users
	 */
	@RequestMapping("/users")
	public List<Users> getAllusers() {
		return usersService.getAllUsers();
	}
	
	/**
	 * get a user by user id
	 * @param id user id 
	 * @return a user object
	 */
	@RequestMapping("/users/{id}")	
	//PathVariable told spring MVC what path of the request need to pick
	public Users getUser(@PathVariable Integer id) {	
		return usersService.getUser(id);
	}
	
	

	
	

	@RequestMapping("/users/relationship/ouserid/{ouserid}")	
	public List<Users> getRelationshipOuser(@PathVariable String ouserid ) {	
		return usersService.getRelationshipOuser(ouserid);
	}

	@RequestMapping("/users/relationship/userid/{userid}")	
	public List<Users> getRelationshipUser(@PathVariable Integer userid) {	
		return usersService.getRelationshipUser(userid);
	}
	
	

	
	
	/**
	 * post a user object to server
	 * @param user user object
	 */
	@PostMapping(value="/users")
	public void addUser(@RequestBody Users user) {
		usersService.addUser(user);
	}
	
	/**
	 * edit a user information by user id
	 * @param user user object
	 * @param id user id
	 */
	@PutMapping(value="/users/{id}")
	public void updateUser(@RequestBody Users user, @PathVariable Integer id) {
		usersService.updateUser(id,user);
	}
	
	/**
	 * delete a user by user id
	 * @param id user id
	 */
	@DeleteMapping("/users/{id}")	
	public void deleteUser(@PathVariable Integer id) {	
		usersService.deleteUser(id);
//		relationshipService.deleteRelationshipByUsersId(id);
	}
}
