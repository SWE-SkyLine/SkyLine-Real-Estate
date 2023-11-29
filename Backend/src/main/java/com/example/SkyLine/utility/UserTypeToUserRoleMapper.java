package com.example.SkyLine.utility;

import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.enums.UserTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class UserTypeToUserRoleMapper {
    public UserRoleEnum map(UserTypeEnum userTypeEnum){
        UserRoleEnum role;
        if(userTypeEnum == UserTypeEnum.SUPERADMIN)
            role = UserRoleEnum.SUPERADMIN;
        else if(userTypeEnum == UserTypeEnum.ADMIN)
            role = UserRoleEnum.ADMIN;
        else
            role = UserRoleEnum.USER;

        return role;
    }
}
