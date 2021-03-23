package io.javabrains.springbootstarter.relationship;

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

/**
 * 
 * @author Xin Wang
 *
 * A class for history controller
 */
@RestController
public class RelationshipController {
	
	/**
	 * instance variable for history service
	 */
	@Autowired
	private RelationshipService relationshipService;
	
	/**
	 * get a list of all history text
	 * @return a list of all history text
	 */
	@RequestMapping("/relationship")
	public List<Relationship> getAllhistory() {
		return relationshipService.getAllRelationship();
	}
	
	@RequestMapping("/relationship/{id}")
	public Relationship getAllhistory(@PathVariable Integer id) {
		return relationshipService.getbyid(id);
	}
	
	@RequestMapping("/relationship/ouserid/{id}")
	public List<Relationship> getAllrelationshipbyouserid(@PathVariable Integer id) {
		return relationshipService.getAllrelationshipbyouserid(id);
	}
	
	@RequestMapping("/relationship/userid/{id}")
	public List<Relationship> getAllrelationshipbyuserid(@PathVariable Integer id) {
		return relationshipService.getAllrelationshipbyuserid(id);
	}
	


	/**
	 * add a history object 
	 * @param history history object
	 */
	@PostMapping(value="/relationship/add")
	public void addRelationship(@RequestBody Relationship relationship) {
		relationshipService.addRelationship(relationship);
	}

	/**
	 * delete history text from sender to receiver
	 * @param sender sender id
	 * @param receiver receiver id
	 */
	@DeleteMapping("/relationship/delete/{ouserid}/{userid}")	
	public void deleteUser(@PathVariable Integer ouserid, @PathVariable Integer userid) {	
		relationshipService.deleteRelationship(ouserid, userid);
	}
	/**
	 * delete history text from user id
	 * @param sender user id
	 */
	@DeleteMapping("/relationship/delete/{id}")	
	public void deleteUser(@PathVariable Integer id) {	
		relationshipService.deleteRelationshipByUsersId(id);
	}
	
	
	
	
}
