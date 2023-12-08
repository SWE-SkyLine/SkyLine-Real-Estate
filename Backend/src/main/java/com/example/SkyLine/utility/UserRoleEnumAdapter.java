package com.example.SkyLine.utility;

import com.example.SkyLine.enums.UserRoleEnum;

public class UserRoleEnumAdapter {
    public String UserRoleEnumToString(UserRoleEnum userRoleEnum) {
        String userRole;
        if (userRoleEnum == UserRoleEnum.SUPERADMIN)
            userRole = "SUPERADMIN";
        else if(userRoleEnum == UserRoleEnum.ADMIN)
            userRole = "ADMIN";
        else if(userRoleEnum == UserRoleEnum.COMPANY)
            userRole = "COMPANY";
        else if(userRoleEnum == UserRoleEnum.CLIENT)
            userRole = "CLIENT";
        else
            userRole = "AGENT";
        return userRole;
    }
}
