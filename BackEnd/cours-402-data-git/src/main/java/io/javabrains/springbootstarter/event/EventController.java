package io.javabrains.springbootstarter.event;

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

import io.javabrains.springbootstarter.users.Users;


@RestController
/**
 * 
 * @author Xin Wang
 * 
 * A class for user alarm controller
 */
public class EventController {
	
	
	@Autowired
	/**
	 * 
	 * instance variable for user alarm service
	 */
	private EventService eventService;
	
	
	@RequestMapping("event/all")
	/**
	 * get a list of alarms list by a user id
	 * @param id user id
	 * @return a list of alarms list by a user id
	 */
	public List<Event> getAllEvent() {
		return eventService.getAllEvents();
	}
	  
	
	@RequestMapping("event/{userid}")	
	/**
	 * get a alarm object by a user id and his/her friend id
	 * @param id alarm id
	 * @return a alarm object by a user id and his/her friend id
	 */
	public List<Event> getEventByUserid(@PathVariable Integer userid) {	
		return eventService.getAllEventByUserid(userid);
	}
	
	
	@PostMapping(value="event/add")
	/**
	 * add a friend object by user id
	 * @param friend friend object 
	 * @param userid user id 
	 */
	public void addAlarm(@RequestBody Event event) {
		eventService.addEvent(event);
	}
	

	@PutMapping(value="/event/update/{id}")
	public void updateAlarm(@RequestBody Event event, @PathVariable Integer id) {
		eventService.updateEvent(id, event);
	}

	
	
	@DeleteMapping("event/delete/{id}")	
	/**
	 * delete user friend by friend user id
	 * @param id friend user id
	 */
	public void deleteEvent(@PathVariable Integer id) {	
		eventService.deleteEvent(id);
	}
}
