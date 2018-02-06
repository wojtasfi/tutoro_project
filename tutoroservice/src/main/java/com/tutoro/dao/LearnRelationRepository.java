package com.tutoro.dao;

import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wojci on 4/15/2017.
 */

@Repository
public interface LearnRelationRepository extends CrudRepository<LearnRelation, Long> {

    LearnRelation findByTeacherAndStudentAndSkill(Tutor teacher, Tutor student, Skill skill);
}
