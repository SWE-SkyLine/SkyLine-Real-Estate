package com.example.SkyLine.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {
    private byte[] profile_photo;
    private String firstName;
    private String lastName;
    private String account_type;
    private String mobile;
    private String email;
}
