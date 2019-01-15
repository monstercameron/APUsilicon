/*
This class models a DIMM of RAM
 */
package com.apusilicon.blog.classes.real;

/**
 *
 * @author earlcameron
 */
public class Ram {
    
    private int id;
    private double capacity;
    private Type type;
    
    public enum Type{DDR4, DDR3}
}
