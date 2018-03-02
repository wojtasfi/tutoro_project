package com.tutoro.dao;

import com.tutoro.entities.LearnRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by wojci on 4/15/2017.
 */

@Repository
public interface LearnRelationRepository extends CrudRepository<LearnRelation, Long> {

    LearnRelation findByTeacherIdAndStudentIdAndSkillId(Long teacherId, Long studentId, Long skillId);

    Set<LearnRelation> findByStudentId(Long studentId);

    Set<LearnRelation> findByTeacherId(Long teacherId);

    Set<LearnRelation> findBySkillId(Long skillId);

}
