package com.relationservice.dao.db;

import com.relationservice.entities.db.LearnRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wojciech on 28.06.17.
 */
@Repository
public interface LearnRelationRepository extends CrudRepository<LearnRelation, Long> {

}
