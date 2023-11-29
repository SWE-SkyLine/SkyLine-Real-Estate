package com.example.SkyLine;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.model.Notification;
import com.example.SkyLine.repository.NotificationRepository;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.service.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class NotificationServiceTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public int createMockRequester(){
        User requester = new User();
        requester.setFirstName("admin");
        requester.setLastName("test");
        requester.setEmail("admin@gmail.com");
        requester.setPassword("password");
        requester.setPhoneNumber("123456789");
        requester.setVer_code(123);
        requester.setUserRole(UserRoleEnum.ADMIN);
        return userRepository.save(requester).getId();
    }
    public int createMockCandidate(){
        User requester = new User();
        requester.setFirstName("user");
        requester.setLastName("test");
        requester.setEmail("user@gmail.com");
        requester.setPassword("password");
        requester.setPhoneNumber("123456789");
        requester.setVer_code(122);
        requester.setUserRole(UserRoleEnum.USER);
        return userRepository.save(requester).getId();
    }
    @Test
    public void testNotify() {

        int requesterID = 7;
        int candidateID = 6;
        assertTrue(notificationService.notify(requesterID, candidateID));
        assertTrue(notificationService.getSuperAdminRequests(1).size()>0);
        Notification notification = notificationService.getSuperAdminRequests(1).get(0);
        assertTrue(notification.getRequester_id()==requesterID && notification.getCandidate_id()==candidateID);
        assertFalse(notificationService.notify(requesterID, candidateID));


    }
    @Test
    public void testGetAdminPendingRequests() {
        int requesterID = 7;

        assertTrue(notificationService.getAdminPendingRequests(requesterID).size()>0);
    }

    @Test
    public void testRejectRequest() {
        // Add test data
        int requesterID = 7;
        int candidateID = 6;
        int responderID =1;
        Notification request = new Notification(requesterID,candidateID,responderID);
        notificationRepository.save(request);
        notificationService.rejectRequest(request);
        assertSame(userRepository.getById(candidateID).getUserRole(), UserRoleEnum.USER);

    }
    @Test
    public void testApproveRequest() {
        // Add test data
        int requesterID = 7;
        int candidateID = 6;
        int responderID =1;
        Notification request = new Notification(requesterID,candidateID,responderID);
        notificationRepository.save(request);
        notificationService.approveRequest(request);
        assertSame(userRepository.getById(candidateID).getUserRole(), UserRoleEnum.ADMIN);

    }


    @Test
    public void testGetAdminAnsweredRequests() {
        int requesterID = 7;
        int candidateID = 6;
        int responderID =1;
        Notification request = new Notification(requesterID,candidateID,responderID);
        notificationRepository.save(request);
        notificationService.rejectRequest(request);
        assertTrue(notificationService.notify(requesterID, candidateID));
        assertTrue(notificationService.getAdminPendingRequests(requesterID).size()>0);
    }

    @Test
    public void testGetSuperAdminRequests() {
        int requesterID = 7;
        int candidateID = 6;
        int responderID =1;
        assertTrue(notificationService.notify(requesterID, candidateID));
        Notification request = new Notification(requesterID,candidateID,responderID);
        notificationRepository.save(request);
        assertTrue(notificationService.getSuperAdminRequests(responderID).size()>0);
    }
}