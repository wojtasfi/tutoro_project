package com.tutoro.dao;

import com.tutoro.entities.LearnRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wojci on 4/15/2017.
 */

@Repository
public interface LearnRelationRepository extends CrudRepository<LearnRelation, Long> {

}
