
package io.javabrains.springbootstarter.alarm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import io.javabrains.springbootstarter.users.Users;


@Entity
@Table(name="alarm")

/**
 * 
 * @author Xin Wang
 *
 */
public class Alarm {
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
	
	
	@Column(name="ouserid")
	@NotFound(action=NotFoundAction.IGNORE)
	/**
	 * alarm can repeat or not
	 */
	private Integer ouserid;
	
	
	@Column(name="message")
	@NotFound(action=NotFoundAction.IGNORE)
	/**
	 * repeat day.
	 */
	private String message;
	
	
	@Column(name="type")
	@NotFound(action=NotFoundAction.IGNORE)
	/**
	 * alarm type.
	 */
	private Integer type;

	
	public Alarm() {
	}

	/**
	 * @param id
	 * @param userid
	 * @param time
	 * @param repeatable
	 * @param repeatday
	 * @param type
	 */
	public Alarm(Integer id, Integer userid, String time, Integer ouserid, String message, Integer type) {
		super();
		this.id = id;
		this.userid = userid;
		this.time = time;
		this.ouserid = ouserid;
		this.message = message;
		this.type = type;

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
	 * @return the ouserid
	 */
	public Integer getOuserid() {
		return ouserid;
	}

	/**
	 * @param ouserid the ouserid to set
	 */
	public void setOuserid(Integer ouserid) {
		this.ouserid = ouserid;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}


	
	
	
	

	

	

}
