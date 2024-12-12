package com.keyin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    private MockEmailService mockEmailService;

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationService(mockEmailService);
    }

    @Test
    void testSendEmailNotification() {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String content = "Test Content";

        // Act
        notificationService.sendEmailNotification(to, subject, content);

        // Assert
        verify(mockEmailService, times(1)).sendEmail(to, subject, content);
    }
}