/*
Models storage media
 */
package com.apusilicon.blog.classes.real;

/**
 *
 * @author earlcameron
 */
public class Storage{

    private int id;
    private Type type;
    private Format format;
    private Connector connector;
    
    public enum Type{
    SEVENMM, NINEANDHALFMM, MSATA, M22280, M22260, M22240
    }
    
    public enum Format{
        SSD, SSHD, HDD
    }
    
    public enum Connector{
        M2NVME, M2SATA, MSATA, SATA
    }
}

