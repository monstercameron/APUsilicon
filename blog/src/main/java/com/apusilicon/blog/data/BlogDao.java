package com.apusilicon.blog.data;
import java.util.ArrayList;

import com.apusilicon.blog.classes.imaginery.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author monstercameron
 */
@Repository
@Transactional
public interface BlogDao extends CrudRepository<Blog, Integer> {
    public ArrayList<Blog> findFirstByCategory(String category);
    public ArrayList<Blog> findFirstByHash(String hash);
    public ArrayList<Blog> findByTagsContaining(String tag);
}
