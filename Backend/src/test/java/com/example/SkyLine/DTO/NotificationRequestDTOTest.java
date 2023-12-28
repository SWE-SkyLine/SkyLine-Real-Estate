package com.example.SkyLine.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class NotificationRequestDTOTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NotificationRequestDTO#setCandidateId(int)}
     *   <li>{@link NotificationRequestDTO#setDecide(boolean)}
     *   <li>{@link NotificationRequestDTO#setMessage(String)}
     *   <li>{@link NotificationRequestDTO#setNotificationId(int)}
     *   <li>{@link NotificationRequestDTO#setRequesterId(int)}
     *   <li>{@link NotificationRequestDTO#setResponderId(int)}
     *   <li>{@link NotificationRequestDTO#getCandidateId()}
     *   <li>{@link NotificationRequestDTO#getDate_answered()}
     *   <li>{@link NotificationRequestDTO#getDate_requested()}
     *   <li>{@link NotificationRequestDTO#getMessage()}
     *   <li>{@link NotificationRequestDTO#getNotificationId()}
     *   <li>{@link NotificationRequestDTO#getRequesterId()}
     *   <li>{@link NotificationRequestDTO#getResponderId()}
     *   <li>{@link NotificationRequestDTO#isDecide()}
     * </ul>
     */
    @Test
    void testSetCandidateId() {
        LocalDateTime date_requested = LocalDate.of(1970, 1, 1).atStartOfDay();
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(1, 1, 1, 1,
                "Not all who wander are lost", date_requested, LocalDate.of(1970, 1, 1).atStartOfDay(), true);
        notificationRequestDTO.setCandidateId(1);
        notificationRequestDTO.setDecide(true);
        notificationRequestDTO.setMessage("Not all who wander are lost");
        notificationRequestDTO.setNotificationId(1);
        notificationRequestDTO.setRequesterId(1);
        notificationRequestDTO.setResponderId(1);
        int actualCandidateId = notificationRequestDTO.getCandidateId();
        String actualDate_answered = notificationRequestDTO.getDate_answered();
        String actualDate_requested = notificationRequestDTO.getDate_requested();
        String actualMessage = notificationRequestDTO.getMessage();
        int actualNotificationId = notificationRequestDTO.getNotificationId();
        int actualRequesterId = notificationRequestDTO.getRequesterId();
        int actualResponderId = notificationRequestDTO.getResponderId();
        assertEquals("1970-01-01 00:00", actualDate_answered);
        assertEquals("1970-01-01 00:00", actualDate_requested);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(1, actualCandidateId);
        assertEquals(1, actualNotificationId);
        assertEquals(1, actualRequesterId);
        assertEquals(1, actualResponderId);
        assertTrue(notificationRequestDTO.isDecide());
    }

    /**
     * Method under test:
     * {@link NotificationRequestDTO#setDate_requested(LocalDateTime)}
     */
    @Test
    void testSetDate_requested() {
        LocalDateTime date_requested = LocalDate.of(1970, 1, 1).atStartOfDay();
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(1, 1, 1, 1,
                "Not all who wander are lost", date_requested, LocalDate.of(1970, 1, 1).atStartOfDay(), true);
        notificationRequestDTO.setDate_requested(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertEquals("1970-01-01 00:00", notificationRequestDTO.getDate_requested());
    }

    /**
     * Method under test:
     * {@link NotificationRequestDTO#setDate_answered(LocalDateTime)}
     */
    @Test
    void testSetDate_answered() {
        LocalDateTime date_requested = LocalDate.of(1970, 1, 1).atStartOfDay();
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(1, 1, 1, 1,
                "Not all who wander are lost", date_requested, LocalDate.of(1970, 1, 1).atStartOfDay(), true);
        notificationRequestDTO.setDate_answered(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertEquals("1970-01-01 00:00", notificationRequestDTO.getDate_answered());
    }

    /**
     * Method under test:
     * {@link NotificationRequestDTO#setDate_answered(LocalDateTime)}
     */
    @Test
    void testSetDate_answered2() {
        LocalDateTime date_requested = LocalDate.of(1970, 1, 1).atStartOfDay();
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(1, 1, 1, 1,
                "Not all who wander are lost", date_requested, LocalDate.of(1970, 1, 1).atStartOfDay(), true);
        notificationRequestDTO.setDate_answered(null);
        assertNull(notificationRequestDTO.getDate_answered());
    }

    /**
     * Method under test:
     * {@link NotificationRequestDTO#NotificationRequestDTO(int, int, int, int, String, LocalDateTime, LocalDateTime, boolean)}
     */
    @Test
    void testConstructor() {
        LocalDateTime date_requested = LocalDate.of(1970, 1, 1).atStartOfDay();
        NotificationRequestDTO actualNotificationRequestDTO = new NotificationRequestDTO(1, 1, 1, 1,
                "Not all who wander are lost", date_requested, LocalDate.of(1970, 1, 1).atStartOfDay(), true);

        assertEquals("1970-01-01 00:00", actualNotificationRequestDTO.getDate_answered());
        assertEquals("1970-01-01 00:00", actualNotificationRequestDTO.getDate_requested());
        assertEquals("Not all who wander are lost", actualNotificationRequestDTO.getMessage());
        assertEquals(1, actualNotificationRequestDTO.getCandidateId());
        assertEquals(1, actualNotificationRequestDTO.getNotificationId());
        assertEquals(1, actualNotificationRequestDTO.getRequesterId());
        assertEquals(1, actualNotificationRequestDTO.getResponderId());
        assertTrue(actualNotificationRequestDTO.isDecide());
    }

    /**
     * Method under test:
     * {@link NotificationRequestDTO#NotificationRequestDTO(int, int, int, int, String, LocalDateTime, LocalDateTime, boolean)}
     */
    @Test
    void testConstructor2() {
        NotificationRequestDTO actualNotificationRequestDTO = new NotificationRequestDTO(1, 1, 1, 1, "foo",
                LocalDate.of(1970, 1, 1).atStartOfDay(), null, true);

        assertEquals("1970-01-01 00:00", actualNotificationRequestDTO.getDate_requested());
        assertEquals("foo", actualNotificationRequestDTO.getMessage());
        assertNull(actualNotificationRequestDTO.getDate_answered());
        assertEquals(1, actualNotificationRequestDTO.getCandidateId());
        assertEquals(1, actualNotificationRequestDTO.getNotificationId());
        assertEquals(1, actualNotificationRequestDTO.getRequesterId());
        assertEquals(1, actualNotificationRequestDTO.getResponderId());
        assertTrue(actualNotificationRequestDTO.isDecide());
    }
}
