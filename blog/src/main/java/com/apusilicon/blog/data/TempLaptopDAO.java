package com.apusilicon.blog.data;

import com.apusilicon.blog.classes.real.TempLaptop;
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
public interface TempLaptopDAO extends CrudRepository<TempLaptop, Integer> {
    public Page<TempLaptop> findByEnabled(String enabled, Pageable pageable);
}
