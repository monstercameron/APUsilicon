package com.apusilicon.blog.data;

import com.apusilicon.blog.classes.imaginery.Blog;
import com.apusilicon.blog.classes.real.APU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author earlcameron
 */
@Repository
@Transactional
public interface APUDao extends CrudRepository<APU, Integer> {
    public Page<APU> findAll(Pageable pageable);
}
