package com.example.SkyLine.UtilityTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import com.example.SkyLine.entity.Admin;
import com.example.SkyLine.entity.Client;
import com.example.SkyLine.entity.Company;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.utility.UserFactory;

public class UserFactoryTest {
    @Test
    void testUserFactoryAdmin(){
        UserFactory userFactory = new UserFactory();
        User user = userFactory.userFactory("ADMIN");
        assertInstanceOf(Admin.class, user);
    }

    @Test
    void testUserFactorySuperAdmin(){
        UserFactory userFactory = new UserFactory();
        User user = userFactory.userFactory("SUPERADMIN");
        assertInstanceOf(Admin.class, user);
    }

    @Test
    void testUserFactoryClient(){
        UserFactory userFactory = new UserFactory();
        User user = userFactory.userFactory("CLIENT");
        assertInstanceOf(Client.class, user);
    }

    @Test
    void testUserFactoryAgent(){
        UserFactory userFactory = new UserFactory();
        User user = userFactory.userFactory("AGENT");
        assertInstanceOf(Client.class, user);
    }

    @Test
    void testUserFactoryCompany(){
        UserFactory userFactory = new UserFactory();
        User user = userFactory.userFactory("COMPANY");
        assertInstanceOf(Company.class, user);
    }
}
