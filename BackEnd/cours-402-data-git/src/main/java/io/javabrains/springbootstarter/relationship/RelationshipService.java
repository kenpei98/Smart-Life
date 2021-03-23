package io.javabrains.springbootstarter.relationship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import io.javabrains.springbootstarter.users.Users;

/**
 * 
 * @author Xin Wang
 * a class for history services
 */
@Service
public class RelationshipService {
	
	/**
	 * an instance variable for history repository
	 */
	@Autowired
	private RelationshipRepository relationshipRepository;
	
	/**
	 * get a list of all history text
	 * @return a list of all history text
	 */
	public List<Relationship> getAllRelationship(){
		
		List<Relationship> relationship= new ArrayList<>();
		relationshipRepository.findAll() //iterating all the users from the database
		.forEach(relationship::add);
		
		return relationship;
		
	}
	
	
	public Relationship getbyid(Integer id)
	{
		return relationshipRepository.findById(id).get();          //.findById(id).get();
	}

	
	public List<Relationship> getAllrelationshipbyouserid(Integer id)
	{
		List<Relationship> relationship= new ArrayList<>();
		relationshipRepository.findByOuserid(id)
		.forEach(relationship::add);
		
		return relationship;
		
	}
	
	public List<Relationship> getAllrelationshipbyuserid(Integer id)
	{
		List<Relationship> relationship= new ArrayList<>();
		relationshipRepository.findByUserid(id)
		.forEach(relationship::add);
		
		return relationship;
		
	}
	
	

	/**
	 * add a history to server
	 * @param history history object
	 */
	public void addRelationship(Relationship relationship) {

		relationshipRepository.save(relationship);
	}


	/**
	 * delete history text from sender to receiver
	 * @param sender sender id
	 * @param receiver receiver id
	 */
	public void deleteRelationship(Integer ouserid, Integer userid){
		relationshipRepository.removeByOuseridAndUserid(ouserid, userid);
	}
	
	/**
	 * delete history text from user id
	 * @param sender user id
	 */
	public void deleteRelationshipByUsersId(Integer id){
		relationshipRepository.removeByOuserid(id);
		relationshipRepository.removeByUserid(id);
	}
	


}
