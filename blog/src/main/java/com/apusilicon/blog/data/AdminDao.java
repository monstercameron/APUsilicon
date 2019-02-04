package com.apusilicon.blog.data;

import com.apusilicon.blog.classes.imaginery.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author monstercameron
 */
@Repository
@Transactional
public interface AdminDao  extends CrudRepository<Owner, Integer> {
    
}
