package com.apusilicon.blog.data;
import com.apusilicon.blog.classes.imaginery.Blog;
import java.util.List;
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
    public Blog findFirstByCategory(String category);
}
