package com.yogish.aimailrouting.mailreader.gmail.dto;

public record TestEmail(
        String industry,
        String department,
        String priority,
        String expectedQueue,
        String language,
        String subject,
        String body
) {
}
