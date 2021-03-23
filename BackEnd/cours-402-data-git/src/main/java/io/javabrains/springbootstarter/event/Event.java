
package io.javabrains.springbootstarter.event;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name="event")

/**
 * 
 * @author Xin Wang
 *
 */
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer id;
	
	

	@Column(name="userid")
	@NotFound(action=NotFoundAction.IGNORE)
	
	/**
	 * user id
	 */
	private Integer userid;
	
	
	@Column(name="time")
	@NotFound(action=NotFoundAction.IGNORE)
	/**
	 * alarm time
	 */
	private String time;
	
	
	@Column(name="event")
	@NotFound(action=NotFoundAction.IGNORE)
	/**
	 * alarm can repeat or not
	 */
	private String event;
	
	
	public Event() {
	}

	/**
	 * @param id
	 * @param userid
	 * @param time
	 * @param event
	 */
	public Event(Integer id, Integer userid, String time, String event) {
		super();
		this.id = id;
		this.userid = userid;
		this.time = time;
		this.event = event;

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userid
	 */
	public Integer getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}


	

	

}
