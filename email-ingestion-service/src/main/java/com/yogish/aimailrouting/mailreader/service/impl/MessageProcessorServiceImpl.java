package com.yogish.aimailrouting.mailreader.service.impl;

import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;
import com.yogish.aimailrouting.mailreader.publisher.PublisherService;
import com.yogish.aimailrouting.mailreader.service.MessageProcessorService;
import com.yogish.aimailrouting.mailreader.gmail.MessageReaderService;
import jakarta.mail.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;


//todo make more generic, currently it tied to jakartha mail
//todo exception handling
//todo deduplication
// systems crash handling
// outbox pattern


@Service
public class MessageProcessorServiceImpl implements MessageProcessorService {
    private final Logger logger = LoggerFactory.getLogger(MessageProcessorServiceImpl.class);

    private final MessageReaderService messageReaderService;
    private final PublisherService publisherService;

    @Value("${message.publish.topic:parsed-mail-events}")
    private String topic;


    public MessageProcessorServiceImpl(MessageReaderService messageReaderService, PublisherService publisherService) {
        this.messageReaderService = messageReaderService;

        this.publisherService = publisherService;
    }


    @Override
    public void processMessages() throws IOException {
        logger.info("processing messages");

        List<EmailMessageEvent> messageEvents = messageReaderService.getUnreadMessages();

        logger.info("received [{}] at [{}]",
                messageEvents.size(),
                Instant.now().atZone(ZoneId.of("Asia/Kolkata"))
        );

        messageEvents.forEach(
                event ->
                {
                    logger.info("message sending to kafka from: {}, subject : {}", event.from(), event.subject());
                    publisherService.publish(topic, event);
                }
        );
        logger.info("processed [{}] at [{}]",
                messageEvents.size(),
                Instant.now().atZone(ZoneId.of("Asia/Kolkata"))
        );

    }
}
