/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Image {

    @Id
    @GeneratedValue
    int id;

    @NotNull
    String fileName;

    public Image() {
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", fileName=" + fileName + '}';
    }
}
