package com.apusilicon.blog.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.apusilicon.blog.classes.imaginery.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author monstercameron
 */
@Repository
@Transactional
public interface ImageDao  extends CrudRepository<Image, Integer> {
    public Page<Image> findByFileName(String fileName, Pageable pageable);
    public Page<Image> findAll(Pageable pageable);
}
