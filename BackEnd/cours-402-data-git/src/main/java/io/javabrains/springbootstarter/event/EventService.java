package io.javabrains.springbootstarter.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
/**
 * @author Xin Wang
 * a class for friend services
 */
public class EventService {
	
	
	@Autowired
	/**
	 * an instance variable for friend repository
	 */
	private EventRepository eventRepository;
	
	
	
	public List<Event> getAllEvents(){
		
		List<Event> event= new ArrayList<>();
		eventRepository.findAll()
		.forEach(event::add);
		
		return event;
		
	}
	
	/**
	 * get a list of friend list by a user id
	 * @param userId user id
	 * @return a list of friend list by a user id
	 */
	public List<Event> getAllEventByUserid(Integer userId){
		
		List<Event> event= new ArrayList<>();
		eventRepository.findByUserid(userId)
		.forEach(event::add);
		
		return event;
		
	}
	


	/**
	 * add a friend object
	 * @param friend object 
	 */
	public void addEvent(Event event) {

		eventRepository.save(event);
	}
	
	public void updateEvent(Integer userid, Event event) {

		eventRepository.save(event);
	}


	/**
	 * delete user friend by friend user id
	 * @param id friend user id
	 */
	public void deleteEvent(Integer id) {
		eventRepository.deleteById(id);
	}

}
