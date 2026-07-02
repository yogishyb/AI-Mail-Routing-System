package com.yogish.aimailrouting.mailreader.scheduler;


import com.yogish.aimailrouting.mailreader.gmail.MessageReaderService;
import com.yogish.aimailrouting.mailreader.service.MessageProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class EmailReaderScheduler {
    private final Logger logger = LoggerFactory.getLogger(EmailReaderScheduler.class);

    private final MessageProcessorService messageProcessorService;

    public EmailReaderScheduler(MessageProcessorService messageProcessorService) {
        this.messageProcessorService = messageProcessorService;
    }


    @Scheduled(fixedDelayString = "${mail.polling-delay:4000}")
    private void pollMessages() throws IOException {
        logger.info(" =================== Started to poll messages at {} ============================",
                Instant.now().atZone(
                        ZoneId.of("Asia/Kolkata")
                )
        );
        messageProcessorService.processMessages();
    }

}
