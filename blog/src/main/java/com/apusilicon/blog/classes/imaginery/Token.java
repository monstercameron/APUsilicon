package com.apusilicon.blog.classes.imaginery;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
/*
*
*   Passes object to view to authorize certain actions
*
*/
@Entity
public class Token implements Serializable{
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String jwt;

    @OneToOne
    private Owner owner;

    public Token(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return jwt;
    }

    public void setHash(String hash) {
        this.jwt = hash;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    

}