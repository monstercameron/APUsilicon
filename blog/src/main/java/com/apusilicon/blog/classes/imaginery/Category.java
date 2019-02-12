/*
Categories for the blog post
 */
package com.apusilicon.blog.classes.imaginery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author earlcameron
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String category;

    public Category(){
    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString(){
        return "{id:"+this.id+",category:"+this.category+"}";
    }
}