package com.example.SkyLine;

import com.example.SkyLine.controller.NotificationController;
import com.example.SkyLine.entity.Notification;
import com.example.SkyLine.service.NotificationService;
import com.example.SkyLine.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAdminPendingRequests() {
        // Mock the behavior of notificationService.getAdminPendingRequests
        when(notificationService.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        // Call the method that you want to test
        ArrayList<Notification> result = notificationController.getAdminPendingRequests(1);

        // Verify the result
        assertEquals(0, result.size());

        // Verify that the notificationService.getAdminPendingRequests method was called with the expected parameter
        verify(notificationService, times(1)).getAdminPendingRequests(1);
    }

    @Test
    void getAdminAnsweredRequests() {
        // Mock the behavior of notificationService.getAdminAnsweredRequests
        when(notificationService.getAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());

        // Call the method that you want to test
        ArrayList<Notification> result = notificationController.getAdminAnsweredRequests(1);

        // Verify the result
        assertEquals(0, result.size());

        // Verify that the notificationService.getAdminAnsweredRequests method was called with the expected parameter
        verify(notificationService, times(1)).getAdminAnsweredRequests(1);
    }

    @Test
    void getSuperAdminRequests() {
        // Mock the behavior of notificationService.getSuperAdminRequests
        when(notificationService.getSuperAdminRequests(anyInt())).thenReturn(new ArrayList<>());

        // Call the method that you want to test
        ArrayList<Notification> result = notificationController.getSuperAdminRequests(1);

        // Verify the result
        assertEquals(0, result.size());

        // Verify that the notificationService.getSuperAdminRequests method was called with the expected parameter
        verify(notificationService, times(1)).getSuperAdminRequests(1);
    }

    @Test
    void approveRequest() {
        // Create a mock notification for the test
        Notification mockNotification = new Notification();
        mockNotification.setCandidate_id(1);

        // Call the method that you want to test
        notificationController.approveRequest(mockNotification);

        // Verify that the notificationService.approveRequest method was called with the expected parameter
        verify(notificationService, times(1)).approveRequest(mockNotification);

        // Verify that the userService.PromoteUser method was called with the expected parameter
        verify(userService, times(1)).PromoteUser(1);
    }

    @Test
    void rejectRequest() {
        // Create a mock notification for the test
        Notification mockNotification = new Notification();

        // Call the method that you want to test
        notificationController.rejectRequest(mockNotification);

        // Verify that the notificationService.rejectRequest method was called with the expected parameter
        verify(notificationService, times(1)).rejectRequest(mockNotification);
    }

    @Test
    void notify_test() {
        // Mock the behavior of notificationService.notify
        when(notificationService.notify(anyInt(), anyInt())).thenReturn(true);

        // Call the method that you want to test
        boolean result = notificationController.notify(1, 2);

        // Verify the result
        assertEquals(true, result);

        // Verify that the notificationService.notify method was called with the expected parameters
        verify(notificationService, times(1)).notify(1, 2);
    }

    @Test
    void markSeen() {
        // Create a mock notification for the test
        Notification mockNotification = new Notification();

        // Call the method that you want to test
        notificationController.markSeen(mockNotification);

        // Verify that the notificationService.markSeen method was called with the expected parameter
        verify(notificationService, times(1)).markSeen(mockNotification);
    }
}
