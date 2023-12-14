package com.example.SkyLine.UtilityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.utility.UserRoleEnumAdapter;

public class UserRoleEnumAdapterTest {
    @Test
    void testSuperAdmin(){
        UserRoleEnumAdapter userRole = new UserRoleEnumAdapter();
        String ans = userRole.UserRoleEnumToString(UserRoleEnum.SUPERADMIN);
        assertEquals("SUPERADMIN", ans);
    }

    @Test
    void testAdmin(){
        UserRoleEnumAdapter userRole = new UserRoleEnumAdapter();
        String ans = userRole.UserRoleEnumToString(UserRoleEnum.ADMIN);
        assertEquals("ADMIN", ans);
    }

    @Test
    void testCompany(){
        UserRoleEnumAdapter userRole = new UserRoleEnumAdapter();
        String ans = userRole.UserRoleEnumToString(UserRoleEnum.COMPANY);
        assertEquals("COMPANY", ans);
    }

    @Test
    void testClient(){
        UserRoleEnumAdapter userRole = new UserRoleEnumAdapter();
        String ans = userRole.UserRoleEnumToString(UserRoleEnum.CLIENT);
        assertEquals("CLIENT", ans);
    }

    @Test
    void testAgent(){
        UserRoleEnumAdapter userRole = new UserRoleEnumAdapter();
        String ans = userRole.UserRoleEnumToString(UserRoleEnum.AGENT);
        assertEquals("AGENT", ans);
    }
}
