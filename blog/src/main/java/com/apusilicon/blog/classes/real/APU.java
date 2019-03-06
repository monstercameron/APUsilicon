/*
Models APU
 */
package com.apusilicon.blog.classes.real;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author earlcameron
 */
@Entity
public class APU {
    @Id
    private int id;
    private String brand;
    private String family;
    private String sku;
    private String cores;
    private String threads;
    private String shaders;
    private String link;

    @Override
    public String toString() {
        return "APU{" + "id=" + id + ", brand=" + brand + ", family=" + family + ", sku=" + sku + ", cores=" + cores + ", threads=" + threads + ", Shaders=" + shaders + ", link=" + link + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getThreads() {
        return threads;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }

    public String getShaders() {
        return shaders;
    }

    public void setShaders(String Shaders) {
        this.shaders = Shaders;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
}
