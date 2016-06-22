package com.studentsnews.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: User
 *
 */
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "user.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "user.getAllUsers", query = "SELECT u FROM User u"),
	@NamedQuery(name = "user.findUserByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName")})
@Entity
@XmlRootElement
public class User implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	

	public User(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	

	public User(String firstName, String lastName, String userName, String password, List<Article> articles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.articles = articles;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String firstName;

	private String lastName;

	private String userName;

	private String password;
	
	private int isAdmin;

	@OneToMany
	private List<Article> articles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int isAdmin() {
		return isAdmin;
	}


	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", isAdmin=" + isAdmin + ", articles=" + articles + "]";
	}

}
