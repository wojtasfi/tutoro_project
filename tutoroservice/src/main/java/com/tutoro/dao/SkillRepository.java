package com.tutoro.dao;

import com.tutoro.entities.Skill;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wojci on 4/26/2017.
 */
public interface SkillRepository extends CrudRepository<Skill, Long> {
    Skill getSkillById(Long id);
}
