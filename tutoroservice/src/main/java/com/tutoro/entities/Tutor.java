package com.tutoro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by wojci on 4/15/2017.
 */
@Entity
@NoArgsConstructor
@Data
@ToString
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

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    @Builder
    public Tutor(String name, String lastName, String username, String password, String email, String skype, String story, byte[] profilePic) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.skype = skype;
        this.story = story;
        this.profilePic = profilePic;
    }

}
