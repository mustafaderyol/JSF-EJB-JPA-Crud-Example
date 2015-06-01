package com.mycompany.Controller;

import com.mycompany.DAO.UsersEJB;
import com.mycompany.Entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author MstfDryl
 */

@ManagedBean(name="users")
@RequestScoped
public class UsersController {
    
    FacesContext context = FacesContext.getCurrentInstance();
        
    private Users user;
    
    @EJB
    UsersEJB userEJB;
    
    private List<Users> userList = new ArrayList();
    
    @PostConstruct
    public void getAllUsersList(){
        user = new Users();
        userList = userEJB.allUsers();
        
    }
    public String editUserss(Users userrrr){
        this.user = userrrr; 
        return "edit.xhtml";
    }
    public String editUser(){
                System.out.println("--------------------1--------------------------------");
        System.out.println("--------------------1--------------------------------");
        System.out.println(this.user.getId());
        System.out.println("--------------------1--------------------------------");
        System.out.println("--------------------1--------------------------------");

        
        if(!this.user.getEmail().contains("@") || !this.user.getEmail().contains("."))
        {
            context.addMessage(null, new FacesMessage("Geçersiz e-Mail adresi"));
            return null;
        }

        try
        {
        System.out.println("--------------------1--------------------------------");
        System.out.println("---------------------2-------------------------------");
            userEJB.editUsers(this.user);
            
        System.out.println("--------------------3--------------------------------");
            userList = userEJB.allUsers();
            
        System.out.println("---------------------4-------------------------------");
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage("Kullanıcı Güncellerken Hata Oluştu... \n "+e));
            return null;
        }
        
    }
    
    public String addUser(){
        try
        {
            if(!user.getEmail().contains("@") || !user.getEmail().contains("."))
            {
                context.addMessage(null, new FacesMessage("Geçerli e-Mail değil"));
                return null;
            }
                
            
            user = userEJB.saveUser(user);
            
            userList = userEJB.allUsers();
            
            context.addMessage(null, new FacesMessage("Kullanıcı Başarı ile Eklendi..."));
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage("Kullanıcı Eklenirken Hata Oluştu... \n "+e));
            return null;
        }
    }

    public String deleteUser(Users getUser){
        try
        {
            userEJB.deleteUsers(getUser);
            userList = userEJB.allUsers();
            
            context.addMessage(null, new FacesMessage("Kullanıcı Başarı ile Silindi..."));
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage("Kullanıcı Silinirken Hata Oluştu... \n "+e));
            return null;
        }
    }
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
    
}
