/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author MstfDryl
 */
@Entity(name="USERS")
@NamedQueries({
    @NamedQuery(name="allUsers", query="SELECT u FROM USERS u"),
    @NamedQuery(name="getXUser", query="SELECT u FROM USERS u WHERE u.userName = :username"),
    @NamedQuery(name="getXEmail", query="SELECT u FROM USERS u WHERE u.email = :email")
})
public class Users implements Serializable {
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="USERNAME", length=200, nullable=false)
    private String userName;
    
    @Column(name="PASSWORD", length=255, nullable=false)
    private String password;
    
    @Column(name="EMAIL", length=255, nullable=false)
    private String email;

    public Users() {
    }

    public Users(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
