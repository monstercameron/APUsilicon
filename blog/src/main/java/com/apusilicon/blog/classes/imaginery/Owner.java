package com.apusilicon.blog.classes.imaginery;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author monstercameron
 * 
 * This class handles the Read/Write permissions for
 * Administrators and Contributers
 */

@Entity
public class Owner {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotNull
    private String saltedPassword;
    
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private Role role;
    
    @OneToMany
    private List<Blog> blogs;

    public Owner() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setSaltedPassword(String SaltedPassword) {
        this.saltedPassword = SaltedPassword;
    }
    
    public String getSaltedPassword() {
        return this.saltedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", saltedPassword=" + saltedPassword + ", email=" + email + '}';
    }
    
    public enum Role{
        ADMIN, CONTRIBUTER, DBUSER
    }
    
}
