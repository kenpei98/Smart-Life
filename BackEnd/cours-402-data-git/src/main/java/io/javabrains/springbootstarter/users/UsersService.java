package io.javabrains.springbootstarter.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.relationship.RelationshipRepository;
import io.javabrains.springbootstarter.relationship.RelationshipService;

/**
 * 
 * @author Xin Wang
 * a class for user services
 */
@Service
public class UsersService {
	
	/**
	 * an instance variable for user repository
	 */
	@Autowired
	private UsersRepository usersRepository;
//	private RelationshipRepository relationshipRepository;

	
	/**
	 * get a list for all user
	 * @return a list for all user
	 */
	public List<Users> getAllUsers(){
		
		List<Users> users= new ArrayList<>();
		usersRepository.findAll() //iterating all the users from the database
		.forEach(users::add);
		
		return users;
		
	}
	
	/**
	 * get one user object by user id
	 * @param id user id
	 * @return user object
	 */
	public Users getUser(Integer id)
	{
		return usersRepository.findById(id).get();
	}

	
	
	
	

	public List<Users> getRelationshipOuser(String ouserid)
	{
		List<Users> users= new ArrayList<>();
		usersRepository.findRelationshipOuser(ouserid)
		.forEach(users::add);
		
		return users;
		
	}
	

	
	
	public List<Users> getRelationshipUser(Integer userid)
	{
		List<Users> users= new ArrayList<>();
		usersRepository.findRelationshipUser(userid) 
		.forEach(users::add);
		return users;
	}
	
	
	
	
	
	/**
	 * add one user object to server
	 * @param user user object
	 */
	public void addUser(Users user) {
		usersRepository.save(user);
		//users.add(user);
		
	}

	/**
	 * edit one user information by user id
	 * @param id user id
	 * @param user user object
	 */
	public void updateUser(Integer id, Users user) {
		//save can do add and update
		//id can not be change
		usersRepository.save(user);
		
	}

	/**
	 * delete one user by user id
	 * @param id user id
	 */
	public void deleteUser(Integer id) {
		usersRepository.deleteById(id);
//		relationshipService.removeByOuserid(id);
//		relationshipService.removeByUserid(id);
	}

}
