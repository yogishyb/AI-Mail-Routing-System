package com.yogish.aimailrouting.mailreader.model;



import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record EmailMessageEvent(
        String messageId,
        String subject,
        String from,
        List<String> to,
        Instant receivedAt,
        String body) {


}
