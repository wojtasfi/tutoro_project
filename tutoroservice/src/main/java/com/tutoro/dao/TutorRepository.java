package com.tutoro.dao;

import com.tutoro.entities.Tutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wojci on 4/15/2017.
 */

@Repository
public interface TutorRepository extends CrudRepository<Tutor, Long> {

    Tutor findByUsername(String username);
}
