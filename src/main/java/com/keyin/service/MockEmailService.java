package com.keyin.service;

import org.springframework.stereotype.Service;

@Service
public class MockEmailService {
    public void sendEmail(String to, String subject, String content) {
        // Simply log the email details for mocking purposes
        System.out.println("Mock email sent:");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
    }
}