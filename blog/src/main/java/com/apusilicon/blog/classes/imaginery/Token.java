package com.apusilicon.blog.classes.imaginery;

import java.io.Serializable;

/*
*
*   encrypted string that store access directives.
*
 */
public class Token implements Serializable {

    String blog, database;

    public Token() {
    }

    @Override
    public String toString() {
        return "Token{" + "blog=" + blog + ", database=" + database + '}';
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
