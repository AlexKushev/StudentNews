package com.studentsnews.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import com.studentsnews.models.User;

@SessionScoped
public class UserContext implements Serializable {

	private static final long serialVersionUID = -5185469629320384569L;

	private User currentUser;
	
	public User getCurrentUser() {
		if(currentUser == null) {
			return new User();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
