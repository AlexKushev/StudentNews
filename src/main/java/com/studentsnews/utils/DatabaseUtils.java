package com.studentsnews.utils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.studentsnews.dao.UserDAO;
import com.studentsnews.models.User;

@Stateless
public class DatabaseUtils {
    
    private static User[] USERS = {
            new User("Alex", "Kushev", "alle3x", "123")};

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private UserDAO userDAO;
    
    public void addTestDataToDB() {
        deleteData();
        addTestUsers();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestUsers() {
        for (User user : USERS) {
            userDAO.addUser(user);
        }
    }

   
}
