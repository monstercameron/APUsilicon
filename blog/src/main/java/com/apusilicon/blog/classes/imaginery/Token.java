package com.apusilicon.blog.classes.imaginery;

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
public class Token{
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String hash;

    @OneToOne
    private Owner owner;

    public Token(){
    }

    

}