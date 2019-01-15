/*
link object
 */
package com.apusilicon.blog.classes.imaginery;

/**
 *
 * @author earlcameron
 */
public class Links {
    private int id;
    private String title;
    private String url;
    private Type type;
    
    public enum Type{
        SHOP, REVIEW, FORUM
    }
}
