/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO;

import com.mycompany.Entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author MstfDryl
 */
@Stateless
public class UsersEJB {
    
    @PersistenceContext(name="Deneme_Deneme_PU")
    private EntityManager em;
    
    public List<Users> allUsers(){
        TypedQuery<Users> sorgu = em.createNamedQuery("allUsers", Users.class);
        return sorgu.getResultList();
    }
    
    public Users getXUser(String username){
        return em.find(Users.class, username);
    }
    
    public Users getXEmail(String email){
        return em.find(Users.class, email);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users saveUser(Users user){
        em.persist(user);
        em.flush();
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users mergeUser(Users user){
        
        System.out.println("--------------------5--------------------------------");
        System.out.println(user.getId());
        System.out.println("----------------------6------------------------------");
        em.merge(em.find(Users.class,user.getId()));
        
        System.out.println("------------------------7----------------------------");
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUsers(Users user) throws Exception{
        try
        {
            em.remove(em.merge(user));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users editUsers(Users user) throws Exception {
        try
        {
            em.merge(user);
            em.flush();
            return user;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
    }
    
}
