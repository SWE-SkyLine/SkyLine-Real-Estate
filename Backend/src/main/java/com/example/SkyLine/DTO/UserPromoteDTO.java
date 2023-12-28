package com.example.SkyLine.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPromoteDTO {

    public UserPromoteDTO(Integer id,String firstName, String lastName, byte[] profile_photo, boolean requested) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profile_photo;
        this.requested = requested;
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private byte[] profilePhoto;
    private boolean requested;

}
