package com.tutoro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wojci on 4/15/2017.
 */
@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String skype;
    private String story;

    @Lob
    @JsonIgnore
    private byte[] profilePic;

    @JsonIgnore
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private Set<Skill> skills = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private Set<LearnRelation> teacherRelations = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<LearnRelation> studentRelations = new HashSet<>();


    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                ", story='" + story + '\'' +
                ", picture='" + profilePic + '\'' +
                '}';
    }

    public Tutor() {

    }

    public String getStory() {
        return story;
    }

    public Set<LearnRelation> getTeacherRelations() {
        return teacherRelations;
    }

    public void setTeacherRelations(Set<LearnRelation> teacherRelations) {
        this.teacherRelations = teacherRelations;
    }

    public Set<LearnRelation> getStudentRelations() {
        return studentRelations;
    }

    public void setStudentRelations(Set<LearnRelation> studentRelations) {
        this.studentRelations = studentRelations;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

}
