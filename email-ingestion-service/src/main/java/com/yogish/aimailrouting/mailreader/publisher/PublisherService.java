package com.yogish.aimailrouting.mailreader.publisher;

import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;

public interface PublisherService {

    public void publish(String topic, EmailMessageEvent emailMessageEvent);
}
