package io.javabrains.springbootstarter.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 
 * @author Xin Wang
 *
 * A history class for store user text history
 */
@Entity
@Table(name="relationship")
public class Relationship {
	//primary key
	/**
	 * history id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer id;
	
	/**
	 * sender id
	 */
	@Column(name="ouserid")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer ouserid;
	
	/**
	 * receiver id
	 */
	@Column(name="userid")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer userid;
	
	/**
	 * history text
	 */
	@Column(name="approved")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer approved;
	
	
	public Relationship() {
	}


	/**
	 * @param id
	 * @param ouserid
	 * @param userid
	 * @param approved
	 */
	public Relationship(Integer id, Integer ouserid, Integer userid, Integer approved) {
		super();
		this.id = id;
		this.ouserid = ouserid;
		this.userid = userid;
		this.approved = approved;
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
	 * @return the approved
	 */
	public Integer getApproved() {
		return approved;
	}


	/**
	 * @param approved the approved to set
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	
	
	


}
