package com.apusilicon.blog.classes.imaginery;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author monstercameron
 */
@Entity
public class DbEntryForm {
    
    @Id
    @GeneratedValue
    int Id;
    
    public DbEntryForm(){
    }
   
    HashMap<String, Object> getMap(){
        return null;
    }
    
}
