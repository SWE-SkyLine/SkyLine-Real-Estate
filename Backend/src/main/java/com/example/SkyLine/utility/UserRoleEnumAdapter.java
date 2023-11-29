package com.example.SkyLine.utility;

import com.example.SkyLine.enums.UserRoleEnum;

public class UserRoleEnumAdapter {
    public String UserRoleEnumToString(UserRoleEnum userRoleEnum) {
        String userRole;
        if (userRoleEnum == UserRoleEnum.SUPERADMIN)
            userRole = "SUPERADMIN";
        else if(userRoleEnum == UserRoleEnum.ADMIN)
            userRole = "ADMIN";
        else
            userRole = "USER";
        return userRole;
    }
}
