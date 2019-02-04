package com.apusilicon.blog.classes.imaginery;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private String name;
    
    @NotNull
    private String hash;
    
    @NotNull
    private String email;

    @NotNull
    private Role role;
    
    @OneToMany
    @NotNull
    private List<Blog> blogs;

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getHash() {
        return hash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", name=" + name + ", hash=" + hash + ", email=" + email + '}';
    }
    
    private enum Role{
        ADMIN, CONTRIBUTER, DBUSER
    }
    
}
