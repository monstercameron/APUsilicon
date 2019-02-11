package com.apusilicon.blog.data;
import java.util.ArrayList;

import com.apusilicon.blog.classes.imaginery.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author monstercameron
 */
@Repository
@Transactional
public interface BlogDao extends PagingAndSortingRepository<Blog, Integer>{
    //public ArrayList<Blog> findFirstByCategory(String category);
    public ArrayList<Blog> findFirstByCategory(String category);
    public ArrayList<Blog> findFirstByHash(String hash);
    public Page<Blog> findByTagsContaining(String filter, Pageable pageable);
    public Page<Blog> findByCategoryContaining(String filter, Pageable pageable);
}
