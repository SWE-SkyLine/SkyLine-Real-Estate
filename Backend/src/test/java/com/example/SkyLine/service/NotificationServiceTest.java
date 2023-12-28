package com.example.SkyLine.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.SkyLine.DTO.NotificationRequestDTO;
import com.example.SkyLine.DTO.UserPromoteDTO;
import com.example.SkyLine.entity.Client;
import com.example.SkyLine.entity.Company;
import com.example.SkyLine.entity.Notification;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.ClientRepository;
import com.example.SkyLine.repository.CompanyRepository;
import com.example.SkyLine.repository.NotificationRepository;
import com.example.SkyLine.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {
    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link NotificationService#notify(int, int)}
     */
    @Test
    void testNotify() {
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(1L);
        boolean actualNotifyResult = notificationService.notify(1, 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        assertFalse(actualNotifyResult);
    }

    /**
     * Method under test: {@link NotificationService#notify(int, int)}
     */
    @Test
    void testNotify2() {
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);
        when(userRepository.getSuperAdminsID(Mockito.<String>any())).thenReturn(new ArrayList<>());
        boolean actualNotifyResult = notificationService.notify(1, 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).getSuperAdminsID(Mockito.<String>any());
        assertTrue(actualNotifyResult);
    }

    /**
     * Method under test: {@link NotificationService#notify(int, int)}
     */
    @Test
    void testNotify3() {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        when(notificationRepository.save(Mockito.<Notification>any())).thenReturn(notification);
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        when(userRepository.getSuperAdminsID(Mockito.<String>any())).thenReturn(integerList);
        boolean actualNotifyResult = notificationService.notify(1, 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).getSuperAdminsID(Mockito.<String>any());
        verify(notificationRepository).save(Mockito.<Notification>any());
        assertTrue(actualNotifyResult);
    }

    /**
     * Method under test: {@link NotificationService#request(int, int)}
     */
    @Test
    void testRequest() throws UnsupportedEncodingException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        boolean actualRequestResult = notificationService.request(1, 1);
        verify(userRepository).findUserById(anyInt());
        assertFalse(actualRequestResult);
    }

    /**
     * Method under test: {@link NotificationService#request(int, int)}
     */
    @Test
    void testRequest2() throws UnsupportedEncodingException {
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(1L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        boolean actualRequestResult = notificationService.request(1, 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertFalse(actualRequestResult);
    }

    /**
     * Method under test: {@link NotificationService#request(int, int)}
     */
    @Test
    void testRequest3() throws UnsupportedEncodingException {
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.getSuperAdminsID(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        boolean actualRequestResult = notificationService.request(1, 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        verify(userRepository).getSuperAdminsID(Mockito.<String>any());
        assertTrue(actualRequestResult);
    }

    /**
     * Method under test: {@link NotificationService#request(int, int)}
     */
    @Test
    void testRequest4() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        when(notificationRepository.save(Mockito.<Notification>any())).thenReturn(notification);
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        when(userRepository.getSuperAdminsID(Mockito.<String>any())).thenReturn(integerList);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        boolean actualRequestResult = notificationService.request(1, 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        verify(userRepository).getSuperAdminsID(Mockito.<String>any());
        verify(notificationRepository).save(Mockito.<Notification>any());
        assertTrue(actualRequestResult);
    }

    /**
     * Method under test: {@link NotificationService#approveRequest(int)}
     */
    @Test
    void testApproveRequest() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        doNothing().when(notificationRepository).deleteNotificationById(anyInt(), anyInt(), anyInt());
        doNothing().when(notificationRepository)
                .updateNotificationById(anyInt(), Mockito.<Boolean>any(), Mockito.<Date>any());
        when(notificationRepository.findNotificationById(anyInt())).thenReturn(notification);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        doNothing().when(userRepository).promoteToAdmin(anyInt(), Mockito.<UserRoleEnum>any());
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        notificationService.approveRequest(1);
        verify(notificationRepository).deleteNotificationById(anyInt(), anyInt(), anyInt());
        verify(notificationRepository).findNotificationById(anyInt());
        verify(notificationRepository).updateNotificationById(anyInt(), Mockito.<Boolean>any(), Mockito.<Date>any());
        verify(userRepository).findUserById(anyInt());
        verify(userRepository).promoteToAdmin(anyInt(), Mockito.<UserRoleEnum>any());
    }

    /**
     * Method under test: {@link NotificationService#approveRequest(int)}
     */
    @Test
    void testApproveRequest2() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        when(notificationRepository.findNotificationById(anyInt())).thenReturn(notification);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        notificationService.approveRequest(1);
        verify(notificationRepository).findNotificationById(anyInt());
        verify(userRepository).findUserById(anyInt());
    }

    /**
     * Method under test: {@link NotificationService#approveRequest(int)}
     */
    @Test
    void testApproveRequest3() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        doNothing().when(notificationRepository)
                .updateNotificationById(anyInt(), Mockito.<Boolean>any(), Mockito.<Date>any());
        when(notificationRepository.findNotificationById(anyInt())).thenReturn(notification);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.CLIENT);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);

        Company company = new Company();
        company.setClients(new ArrayList<>());
        company.setEmail("jane.doe@example.org");
        company.setEnable(true);
        company.setFirstName("Jane");
        company.setId(1);
        company.setIs_enable(true);
        company.setLastName("Doe");
        company.setPassword("iloveyou");
        company.setPhoneNumber("6625550144");
        company.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        company.setUserRole(UserRoleEnum.SUPERADMIN);
        company.setVerificationCode("Verification Code");
        company.setVerificationCodeForgetPassword(1);

        Company company2 = new Company();
        company2.setClients(new ArrayList<>());
        company2.setEmail("jane.doe@example.org");
        company2.setEnable(true);
        company2.setFirstName("Jane");
        company2.setId(1);
        company2.setIs_enable(true);
        company2.setLastName("Doe");
        company2.setPassword("iloveyou");
        company2.setPhoneNumber("6625550144");
        company2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        company2.setUserRole(UserRoleEnum.SUPERADMIN);
        company2.setVerificationCode("Verification Code");
        company2.setVerificationCodeForgetPassword(1);
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company2);
        when(companyRepository.findCompanyById(anyInt())).thenReturn(company);

        Company company3 = new Company();
        company3.setClients(new ArrayList<>());
        company3.setEmail("jane.doe@example.org");
        company3.setEnable(true);
        company3.setFirstName("Jane");
        company3.setId(1);
        company3.setIs_enable(true);
        company3.setLastName("Doe");
        company3.setPassword("iloveyou");
        company3.setPhoneNumber("6625550144");
        company3.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        company3.setUserRole(UserRoleEnum.SUPERADMIN);
        company3.setVerificationCode("Verification Code");
        company3.setVerificationCodeForgetPassword(1);

        Client client = new Client();
        client.setBids(new ArrayList<>());
        client.setCompany(company3);
        client.setEmail("jane.doe@example.org");
        client.setEnable(true);
        client.setFirstName("Jane");
        client.setId(1);
        client.setIs_enable(true);
        client.setLastName("Doe");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setPosts(new ArrayList<>());
        client.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client.setUserRole(UserRoleEnum.SUPERADMIN);
        client.setVerificationCode("Verification Code");
        client.setVerificationCodeForgetPassword(1);
        when(clientRepository.findClientById(anyInt())).thenReturn(client);
        notificationService.approveRequest(1);
        verify(clientRepository).findClientById(anyInt());
        verify(companyRepository).findCompanyById(anyInt());
        verify(notificationRepository).findNotificationById(anyInt());
        verify(notificationRepository).updateNotificationById(anyInt(), Mockito.<Boolean>any(), Mockito.<Date>any());
        verify(userRepository).findUserById(anyInt());
        verify(companyRepository).save(Mockito.<Company>any());
    }

    /**
     * Method under test: {@link NotificationService#rejectRequest(int)}
     */
    @Test
    void testRejectRequest() {
        doNothing().when(notificationRepository)
                .updateNotificationById(anyInt(), Mockito.<Boolean>any(), Mockito.<Date>any());
        notificationService.rejectRequest(1);
        verify(notificationRepository).updateNotificationById(anyInt(), Mockito.<Boolean>any(), Mockito.<Date>any());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications() throws UnsupportedEncodingException {
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertTrue(actualUserNotifications.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications2() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications3() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications4() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(notificationList);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications5() throws UnsupportedEncodingException {
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertTrue(actualUserNotifications.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications6() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications7() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications8() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(notificationList);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications9() throws UnsupportedEncodingException {
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.CLIENT);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertTrue(actualUserNotifications.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications10() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.CLIENT);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications11() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.CLIENT);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications12() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.CLIENT);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getUserNotifications(int)}
     */
    @Test
    void testGetUserNotifications13() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(notificationList);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.CLIENT);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualUserNotifications = notificationService.getUserNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualUserNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getAdminNotifications(int)}
     */
    @Test
    void testGetAdminNotifications() {
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        ArrayList<NotificationRequestDTO> actualAdminNotifications = notificationService.getAdminNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        assertTrue(actualAdminNotifications.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#getAdminNotifications(int)}
     */
    @Test
    void testGetAdminNotifications2() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualAdminNotifications = notificationService.getAdminNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualAdminNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getAdminNotifications(int)}
     */
    @Test
    void testGetAdminNotifications3() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualAdminNotifications = notificationService.getAdminNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualAdminNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getAdminNotifications(int)}
     */
    @Test
    void testGetAdminNotifications4() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        Notification notification2 = new Notification();
        notification2.setAnswered(true);
        notification2.setApproved(true);
        notification2.setCandidate_id(1);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(1);
        notification2.setRequester_id(1);
        notification2.setResponder_id(1);
        notification2.setSeen(true);

        ArrayList<Notification> notificationList2 = new ArrayList<>();
        notificationList2.add(notification2);
        when(notificationRepository.getAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getAdminPendingRequests(anyInt())).thenReturn(notificationList2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualAdminNotifications = notificationService.getAdminNotifications(1);
        verify(notificationRepository).getAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualAdminNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getCompanyNotifications(int)}
     */
    @Test
    void testGetCompanyNotifications() {
        when(notificationRepository.getCompanyAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getCompanyPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        ArrayList<NotificationRequestDTO> actualCompanyNotifications = notificationService.getCompanyNotifications(1);
        verify(notificationRepository).getCompanyAnsweredRequests(anyInt());
        verify(notificationRepository).getCompanyPendingRequests(anyInt());
        assertTrue(actualCompanyNotifications.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#getCompanyNotifications(int)}
     */
    @Test
    void testGetCompanyNotifications2() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getCompanyAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getCompanyPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualCompanyNotifications = notificationService.getCompanyNotifications(1);
        verify(notificationRepository).getCompanyAnsweredRequests(anyInt());
        verify(notificationRepository).getCompanyPendingRequests(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualCompanyNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getCompanyNotifications(int)}
     */
    @Test
    void testGetCompanyNotifications3() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getCompanyAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getCompanyPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualCompanyNotifications = notificationService.getCompanyNotifications(1);
        verify(notificationRepository).getCompanyAnsweredRequests(anyInt());
        verify(notificationRepository).getCompanyPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualCompanyNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getCompanyNotifications(int)}
     */
    @Test
    void testGetCompanyNotifications4() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        Notification notification2 = new Notification();
        notification2.setAnswered(true);
        notification2.setApproved(true);
        notification2.setCandidate_id(1);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(1);
        notification2.setRequester_id(1);
        notification2.setResponder_id(1);
        notification2.setSeen(true);

        ArrayList<Notification> notificationList2 = new ArrayList<>();
        notificationList2.add(notification2);
        when(notificationRepository.getCompanyAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getCompanyPendingRequests(anyInt())).thenReturn(notificationList2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualCompanyNotifications = notificationService.getCompanyNotifications(1);
        verify(notificationRepository).getCompanyAnsweredRequests(anyInt());
        verify(notificationRepository).getCompanyPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualCompanyNotifications.size());
    }

    /**
     * Method under test:
     * {@link NotificationService#getSuperAdminNotifications(int)}
     */
    @Test
    void testGetSuperAdminNotifications() {
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        ArrayList<NotificationRequestDTO> actualSuperAdminNotifications = notificationService.getSuperAdminNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        assertTrue(actualSuperAdminNotifications.isEmpty());
    }

    /**
     * Method under test:
     * {@link NotificationService#getSuperAdminNotifications(int)}
     */
    @Test
    void testGetSuperAdminNotifications2() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualSuperAdminNotifications = notificationService.getSuperAdminNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(1, actualSuperAdminNotifications.size());
    }

    /**
     * Method under test:
     * {@link NotificationService#getSuperAdminNotifications(int)}
     */
    @Test
    void testGetSuperAdminNotifications3() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualSuperAdminNotifications = notificationService.getSuperAdminNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualSuperAdminNotifications.size());
    }

    /**
     * Method under test:
     * {@link NotificationService#getSuperAdminNotifications(int)}
     */
    @Test
    void testGetSuperAdminNotifications4() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        Notification notification2 = new Notification();
        notification2.setAnswered(true);
        notification2.setApproved(true);
        notification2.setCandidate_id(1);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(1);
        notification2.setRequester_id(1);
        notification2.setResponder_id(1);
        notification2.setSeen(true);

        ArrayList<Notification> notificationList2 = new ArrayList<>();
        notificationList2.add(notification2);
        when(notificationRepository.getSuperAdminAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getSuperAdminPendingRequests(anyInt())).thenReturn(notificationList2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualSuperAdminNotifications = notificationService.getSuperAdminNotifications(1);
        verify(notificationRepository).getSuperAdminAnsweredRequests(anyInt());
        verify(notificationRepository).getSuperAdminPendingRequests(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualSuperAdminNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getClientNotifications(int)}
     */
    @Test
    void testGetClientNotifications() {
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());
        ArrayList<NotificationRequestDTO> actualClientNotifications = notificationService.getClientNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        assertTrue(actualClientNotifications.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#getClientNotifications(int)}
     */
    @Test
    void testGetClientNotifications2() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualClientNotifications = notificationService.getClientNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualClientNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getClientNotifications(int)}
     */
    @Test
    void testGetClientNotifications3() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        Notification notification2 = new Notification();
        notification2.setAnswered(false);
        notification2.setApproved(false);
        notification2.setCandidate_id(2);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(2);
        notification2.setRequester_id(2);
        notification2.setResponder_id(2);
        notification2.setSeen(false);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification2);
        notificationList.add(notification);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualClientNotifications = notificationService.getClientNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualClientNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getClientNotifications(int)}
     */
    @Test
    void testGetClientNotifications4() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        Notification notification2 = new Notification();
        notification2.setAnswered(true);
        notification2.setApproved(true);
        notification2.setCandidate_id(1);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(1);
        notification2.setRequester_id(1);
        notification2.setResponder_id(1);
        notification2.setSeen(true);

        ArrayList<Notification> notificationList2 = new ArrayList<>();
        notificationList2.add(notification2);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(notificationList2);
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualClientNotifications = notificationService.getClientNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualClientNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#getClientNotifications(int)}
     */
    @Test
    void testGetClientNotifications5() throws UnsupportedEncodingException {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);

        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        Notification notification2 = new Notification();
        notification2.setAnswered(true);
        notification2.setApproved(true);
        notification2.setCandidate_id(1);
        notification2
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification2.setId(1);
        notification2.setRequester_id(1);
        notification2.setResponder_id(1);
        notification2.setSeen(true);

        ArrayList<Notification> notificationList2 = new ArrayList<>();
        notificationList2.add(notification2);
        when(notificationRepository.getClientAnsweredRequests(anyInt())).thenReturn(notificationList);
        when(notificationRepository.getClientPendingRequests(anyInt())).thenReturn(new ArrayList<>());
        when(notificationRepository.getClientPromotionNotification(anyInt())).thenReturn(notificationList2);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        ArrayList<NotificationRequestDTO> actualClientNotifications = notificationService.getClientNotifications(1);
        verify(notificationRepository).getClientAnsweredRequests(anyInt());
        verify(notificationRepository).getClientPendingRequests(anyInt());
        verify(notificationRepository).getClientPromotionNotification(anyInt());
        verify(userRepository, atLeast(1)).findUserById(anyInt());
        assertEquals(2, actualClientNotifications.size());
    }

    /**
     * Method under test: {@link NotificationService#markSeen(Notification)}
     */
    @Test
    void testMarkSeen() {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        notificationService.markSeen(notification);
        assertTrue(notification.getSeen());
    }

    /**
     * Method under test: {@link NotificationService#markSeen(Notification)}
     */
    @Test
    void testMarkSeen2() {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(true);
        notification.setCandidate_id(1);
        notification.setDate_answered(mock(java.sql.Date.class));
        notification.setDate_requested(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        notificationService.markSeen(notification);
        assertTrue(notification.getSeen());
    }

    /**
     * Method under test: {@link NotificationService#searchClientUsers(String, int)}
     */
    @Test
    void testSearchClientUsers() throws UnsupportedEncodingException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        when(userRepository.findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any()))
                .thenReturn(new ArrayList<>());
        List<UserPromoteDTO> actualSearchClientUsersResult = notificationService.searchClientUsers("Query", 1);
        verify(userRepository).findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any());
        verify(userRepository).findUserById(anyInt());
        assertTrue(actualSearchClientUsersResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#searchClientUsers(String, int)}
     */
    @Test
    void testSearchClientUsers2() throws UnsupportedEncodingException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        when(userRepository.findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any()))
                .thenReturn(new ArrayList<>());
        List<UserPromoteDTO> actualSearchClientUsersResult = notificationService.searchClientUsers("Query", 1);
        verify(userRepository).findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any());
        verify(userRepository).findUserById(anyInt());
        assertTrue(actualSearchClientUsersResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#searchClientUsers(String, int)}
     */
    @Test
    void testSearchClientUsers3() throws UnsupportedEncodingException {
        when(notificationRepository.countCompanyPreviousRequests(anyInt(), anyInt())).thenReturn(3L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setIs_enable(true);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("6625550144");
        user2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user2.setUserRole(UserRoleEnum.SUPERADMIN);
        user2.setVerificationCode("Verification Code");
        user2.setVerificationCodeForgetPassword(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        when(userRepository.findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any()))
                .thenReturn(userList);
        List<UserPromoteDTO> actualSearchClientUsersResult = notificationService.searchClientUsers("Query", 1);
        verify(notificationRepository).countCompanyPreviousRequests(anyInt(), anyInt());
        verify(userRepository).findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualSearchClientUsersResult.size());
        UserPromoteDTO getResult = actualSearchClientUsersResult.get(0);
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals(1, getResult.getId().intValue());
        assertTrue(getResult.isRequested());
        byte[] expectedProfilePhoto = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedProfilePhoto, getResult.getProfilePhoto());
    }

    /**
     * Method under test: {@link NotificationService#searchClientUsers(String, int)}
     */
    @Test
    void testSearchClientUsers4() throws UnsupportedEncodingException {
        when(notificationRepository.countCompanyPreviousRequests(anyInt(), anyInt())).thenReturn(0L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setIs_enable(true);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("6625550144");
        user2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user2.setUserRole(UserRoleEnum.SUPERADMIN);
        user2.setVerificationCode("Verification Code");
        user2.setVerificationCodeForgetPassword(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        when(userRepository.findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any()))
                .thenReturn(userList);
        List<UserPromoteDTO> actualSearchClientUsersResult = notificationService.searchClientUsers("Query", 1);
        verify(notificationRepository).countCompanyPreviousRequests(anyInt(), anyInt());
        verify(userRepository).findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualSearchClientUsersResult.size());
        UserPromoteDTO getResult = actualSearchClientUsersResult.get(0);
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals(1, getResult.getId().intValue());
        assertFalse(getResult.isRequested());
        byte[] expectedProfilePhoto = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedProfilePhoto, getResult.getProfilePhoto());
    }

    /**
     * Method under test: {@link NotificationService#searchClientUsers(String, int)}
     */
    @Test
    void testSearchClientUsers5() throws UnsupportedEncodingException {
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(1L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setIs_enable(true);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("6625550144");
        user2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user2.setUserRole(UserRoleEnum.SUPERADMIN);
        user2.setVerificationCode("Verification Code");
        user2.setVerificationCodeForgetPassword(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        when(userRepository.findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any()))
                .thenReturn(userList);
        List<UserPromoteDTO> actualSearchClientUsersResult = notificationService.searchClientUsers("Query", 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualSearchClientUsersResult.size());
        UserPromoteDTO getResult = actualSearchClientUsersResult.get(0);
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals(1, getResult.getId().intValue());
        assertTrue(getResult.isRequested());
        byte[] expectedProfilePhoto = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedProfilePhoto, getResult.getProfilePhoto());
    }

    /**
     * Method under test: {@link NotificationService#searchClientUsers(String, int)}
     */
    @Test
    void testSearchClientUsers6() throws UnsupportedEncodingException {
        when(notificationRepository.countCandidatePreviousRequests(anyInt())).thenReturn(0L);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.ADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setIs_enable(true);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("6625550144");
        user2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user2.setUserRole(UserRoleEnum.SUPERADMIN);
        user2.setVerificationCode("Verification Code");
        user2.setVerificationCodeForgetPassword(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(userRepository.findUserById(anyInt())).thenReturn(user);
        when(userRepository.findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any()))
                .thenReturn(userList);
        List<UserPromoteDTO> actualSearchClientUsersResult = notificationService.searchClientUsers("Query", 1);
        verify(notificationRepository).countCandidatePreviousRequests(anyInt());
        verify(userRepository).findClientsByFirstNameOrLastName(Mockito.<String>any(), Mockito.<UserRoleEnum>any());
        verify(userRepository).findUserById(anyInt());
        assertEquals(1, actualSearchClientUsersResult.size());
        UserPromoteDTO getResult = actualSearchClientUsersResult.get(0);
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals(1, getResult.getId().intValue());
        assertFalse(getResult.isRequested());
        byte[] expectedProfilePhoto = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedProfilePhoto, getResult.getProfilePhoto());
    }
}
