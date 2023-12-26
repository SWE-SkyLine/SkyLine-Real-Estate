//package com.example.SkyLine;
//
//import com.example.SkyLine.entity.Notification;
//import com.example.SkyLine.repository.NotificationRepository;
//import com.example.SkyLine.repository.UserRepository;
//import com.example.SkyLine.service.NotificationService;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class NotificationServiceTest {
//
//    @Mock
//    private NotificationRepository notificationRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private NotificationService notificationService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testNotify_NoPreviousRequests() {
//        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);
//        when(userRepository.getSuperAdminsID("SuperAdmin")).thenReturn(new ArrayList<>());
//
//        assertTrue(notificationService.notify(1, 2));
//
//        verify(notificationRepository, times(0)).save(any(Notification.class));
//    }
//
//    @Test
//    public void testNotify_PreviousRequestsExist() {
//        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(1L);
//
//        assertFalse(notificationService.notify(1, 2));
//        verify(notificationRepository, times(0)).save(any(Notification.class));
//    }
//
//    @Test
//    public void testNotify_WithSuperAdmins() {
//        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);
//        when(userRepository.getSuperAdminsID("SuperAdmin")).thenReturn(new ArrayList<>(Arrays.asList(3, 4)));
//
//        assertTrue(notificationService.notify(1, 2));
//
//        verify(notificationRepository, times(2)).save(any(Notification.class));
//    }
//
//
//    @Test
//    public void testApproveRequest() {
//        Notification request = new Notification(1, 2, 3);
//
//        // Fix the stubbing for notificationRepository.deleteNotificationById
//        doNothing().when(notificationRepository).deleteNotificationById(1, 2, 3);
//
//        notificationService.approveRequest(request);
//
//        verify(notificationRepository).deleteNotificationById(1, 2, 3);
//        verify(notificationRepository).updateNotificationById(eq(1), eq(2), eq(true), any()); // Use eq() for non-primitive arguments
//    }
//
//
//    @Test
//    public void testRejectRequest() {
//        Notification request = new Notification(1, 2, 3);
//        notificationService.rejectRequest(request);
//
//        verify(notificationRepository).deleteNotificationById(1, 2, 3);
//        verify(notificationRepository).updateNotificationById(eq(1), eq(2), eq(false), any()); // Use eq() for non-primitive arguments
//    }
//
//
//    @Test
//    public void testGetAdminPendingRequests() {
//        when(notificationRepository.getAdminPendingRequests(1)).thenReturn(new ArrayList<>());
//
//        List<Notification> result = notificationService.getAdminPendingRequests(1);
//
//        assertEquals(0, result.size());
//        verify(notificationRepository).getAdminPendingRequests(1);
//    }
//
//    @Test
//    public void testGetAdminAnsweredRequests() {
//        when(notificationRepository.getAdminAnsweredRequests(1)).thenReturn(new ArrayList<>());
//
//        List<Notification> result = notificationService.getAdminAnsweredRequests(1);
//
//        assertEquals(0, result.size());
//        verify(notificationRepository).getAdminAnsweredRequests(1);
//    }
//
//    @Test
//    public void testGetSuperAdminRequests() {
//        when(notificationRepository.getSuperAdminRequests(1)).thenReturn(new ArrayList<>());
//
//        List<Notification> result = notificationService.getSuperAdminRequests(1);
//
//        assertEquals(0, result.size());
//        verify(notificationRepository).getSuperAdminRequests(1);
//    }
//
//    @Test
//    public void testMarkSeen() {
//        Notification notification = new Notification(1, 2, 3);
//        notificationService.markSeen(notification);
//
//        assertTrue(notification.isSeen());
//    }
//}
