package com.studentsnews.dao;

import java.security.MessageDigest;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.studentsnews.models.User;

@Singleton
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public void addUser(User user) {
        user.setPassword(getHashedPassword(user.getPassword()));
        em.persist(user);
    }
    
    public List<User> getAllUsers() {
    	String txtQuery = "SELECT u FROM User u";
    	TypedQuery<User> query = em.createQuery(txtQuery, User.class);
    	return query.getResultList();
    }

    private String getHashedPassword(String password) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            password = new String(mda.digest(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}