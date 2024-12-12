package com.keyin.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final MockEmailService mockEmailService;

    public NotificationService(MockEmailService mockEmailService) {
        this.mockEmailService = mockEmailService;
    }

    public void sendEmailNotification(String to, String subject, String content) {
        // Using mock email service instead of SendGrid
        mockEmailService.sendEmail(to, subject, content);
    }

    public void sendSMSNotification(String to, String message) {
        // Mock SMS notification instead of Twilio
        System.out.println("Mock SMS sent:");
        System.out.println("To: " + to);
        System.out.println("Message: " + message);
    }
}