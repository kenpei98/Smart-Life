package io.javabrains.springbootstarter.users;

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
 * A user class for store user information
 */
@Entity
@Table(name="users")
public class Users {
	/**
	 * user id 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer id;
	
	/**
	 * user name
	 */
	@Column(name="username")
	@NotFound(action=NotFoundAction.IGNORE)
	private String name;
	
	/**
	 * user password
	 */
	@Column(name="password")
	@NotFound(action=NotFoundAction.IGNORE)
	private String password;
	
	/**
	 * user gender
	 */
	@Column(name="gender")
	@NotFound(action=NotFoundAction.IGNORE)
	private String gender;
	
	/**
	 * user email
	 */
	@Column(name="email")
	@NotFound(action=NotFoundAction.IGNORE)
	private String email;
	
	/**
	 * user type
	 */
	@Column(name="type")
	@NotFound(action=NotFoundAction.IGNORE)
	private Integer type;
	
	/**
	 * an empty constructor for users
	 */
	public Users() {
	}
	
	/**
	 * constructor for users
	 * @param id user id
	 * @param name user name
	 * @param passward user password
	 * @param gender user gender
	 * @param email user email
	 * @param type user type
	 */
	public Users(Integer id, String name, String password, String gender, String email, Integer type) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender=gender;
		this.email=email;
		this.type=type;
		
	}
	/**
	 * get user id
	 * @return user id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * set user id
	 * @param id user id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * get user name
	 * @return user name
	 */
	public String getName() {
		return name;
	}
	/**
	 * set user name
	 * @param name user name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get user password
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set user password
	 * @param passward user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * get user gender
	 * @return user gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * set user gender
	 * @param gender user gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * get user email
	 * @return user email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set user email
	 * @param email user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * get user type
	 * @return user type
	 */
	public Integer gettype() {
		return type;
	}
	/**
	 * set user type
	 * @param type user type
	 */
	public void settype(Integer type) {
		this.type = type;
	}

}
