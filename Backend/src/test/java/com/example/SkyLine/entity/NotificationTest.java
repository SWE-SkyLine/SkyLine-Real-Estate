package com.example.SkyLine.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class NotificationTest {
    /**
     * Method under test: {@link Notification#Notification(int, int)}
     */
    @Test
    void testConstructor() {
        Notification actualNotification = new Notification(1, 1);

        assertNull(actualNotification.getApproved());
        assertNull(actualNotification.getDate_answered());
        assertEquals(1, actualNotification.getRequester_id());
        assertEquals(1, actualNotification.getResponder_id());
        assertFalse(actualNotification.getAnswered());
        assertFalse(actualNotification.getSeen());
    }

    /**
     * Method under test: {@link Notification#Notification(int, int, int)}
     */
    @Test
    void testConstructor2() {
        Notification actualNotification = new Notification(1, 1, 1);

        assertNull(actualNotification.getApproved());
        assertNull(actualNotification.getDate_answered());
        assertEquals(1, actualNotification.getCandidate_id());
        assertEquals(1, actualNotification.getRequester_id());
        assertEquals(1, actualNotification.getResponder_id());
        assertFalse(actualNotification.getAnswered());
        assertFalse(actualNotification.getSeen());
    }

    /**
     * Method under test: {@link Notification#isApproved()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsApproved() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Boolean.booleanValue()" because "this.approved" is null
        //       at com.example.SkyLine.entity.Notification.isApproved(Notification.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        (new Notification()).isApproved();
    }

    /**
     * Method under test: {@link Notification#isApproved()}
     */
    @Test
    void testIsApproved2() {
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
        assertTrue(notification.isApproved());
    }

    /**
     * Method under test: {@link Notification#isApproved()}
     */
    @Test
    void testIsApproved3() {
        Notification notification = new Notification();
        notification.setAnswered(true);
        notification.setApproved(false);
        notification.setCandidate_id(1);
        notification
                .setDate_answered(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification
                .setDate_requested(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        notification.setId(1);
        notification.setRequester_id(1);
        notification.setResponder_id(1);
        notification.setSeen(true);
        assertFalse(notification.isApproved());
    }

    /**
     * Method under test: {@link Notification#isApproved()}
     */
    @Test
    void testIsApproved4() {
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
        assertTrue(notification.isApproved());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Notification#setId(int)}
     *   <li>{@link Notification#getCandidate_id()}
     *   <li>{@link Notification#getRequester_id()}
     *   <li>{@link Notification#getResponder_id()}
     * </ul>
     */
    @Test
    void testSetId() {
        Notification notification = new Notification(1, 1);
        notification.setId(1);
        int actualCandidate_id = notification.getCandidate_id();
        int actualRequester_id = notification.getRequester_id();
        assertEquals(0, actualCandidate_id);
        assertEquals(1, actualRequester_id);
        assertEquals(1, notification.getResponder_id());
    }

    /**
     * Method under test: {@link Notification#isSeen()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsSeen() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Boolean.booleanValue()" because "this.seen" is null
        //       at com.example.SkyLine.entity.Notification.isSeen(Notification.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        (new Notification()).isSeen();
    }

    /**
     * Method under test: {@link Notification#isSeen()}
     */
    @Test
    void testIsSeen2() {
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
        assertTrue(notification.isSeen());
    }

    /**
     * Method under test: {@link Notification#isSeen()}
     */
    @Test
    void testIsSeen3() {
        assertFalse((new Notification(1, 1)).isSeen());
    }

    /**
     * Method under test: {@link Notification#isSeen()}
     */
    @Test
    void testIsSeen4() {
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
        assertTrue(notification.isSeen());
    }

    /**
     * Method under test: {@link Notification#setSeen(boolean)}
     */
    @Test
    void testSetSeen() {
        Notification notification = new Notification();
        notification.setSeen(true);
        assertTrue(notification.getSeen());
    }

    /**
     * Method under test: {@link Notification#setSeen(boolean)}
     */
    @Test
    void testSetSeen2() {
        Notification notification = new Notification();
        notification.setDate_requested(mock(java.sql.Date.class));
        notification.setSeen(true);
        assertTrue(notification.getSeen());
    }
}
