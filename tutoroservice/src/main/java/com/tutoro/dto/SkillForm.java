package com.tutoro.dto;

import com.tutoro.entities.Skill;

/**
 * Created by wojci on 4/25/2017.
 */

public class SkillForm {

    private Long skillId;
    private String name;
    private String tag;
    private String description;
    String tags;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTags() {
        return tags;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Skill toSkill() {
        Skill skill = new Skill();
        skill.setName(this.name);

        return skill;
    }

    @Override
    public String toString() {
        return "SkillForm{" +
                "skillId=" + skillId +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", tags=" + tags +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
