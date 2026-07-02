package com.yogish.aimailrouting.mailreader.publisher.impl;

import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;
import com.yogish.aimailrouting.mailreader.publisher.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherSvcImpl implements PublisherService {

    private final Logger logger = LoggerFactory.getLogger(KafkaPublisherSvcImpl.class);


    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaPublisherSvcImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void publish(String topic, EmailMessageEvent emailMessageEvent) {
        logger.info("publishing message id: {} to topic: {}",
                emailMessageEvent.messageId(),
                topic);

        kafkaTemplate.send(topic, emailMessageEvent);
    }
}
