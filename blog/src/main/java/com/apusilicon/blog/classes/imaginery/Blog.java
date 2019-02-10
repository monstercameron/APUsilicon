package com.apusilicon.blog.classes.imaginery;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author monstercameron
 * 
 * Contains the blog post and metadata
 */

@Entity
public class Blog implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
        
    @NotNull
    private String titleImage, title, date, tags, category, preview, hash;;
    
    @NotNull
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String body;

    @ManyToOne
    private Owner owner;

    public Blog() {
        this.hash = UUID.randomUUID().toString();
    }

    public String getHash(){
        return this.hash;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Blog{" + ",hash=" + hash + ",titleImage=" + titleImage + ", title=" + title + ", date=" + date + ", tags=" + tags + ", category=" + category + ", preview=" + preview + ", body=" + body + ", owner=" + owner + '}';
    }
    
}
