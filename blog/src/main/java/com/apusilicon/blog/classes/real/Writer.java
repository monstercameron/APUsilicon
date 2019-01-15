/*
This Class models the author
 */
package com.apusilicon.blog.classes.real;
import com.apusilicon.blog.classes.imaginery.Post;
import com.apusilicon.blog.enums.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author earlcameron
 */
public class Writer {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String recovery;
    private Role role;
    
    List<Post> posts = new ArrayList<>();
    
}
