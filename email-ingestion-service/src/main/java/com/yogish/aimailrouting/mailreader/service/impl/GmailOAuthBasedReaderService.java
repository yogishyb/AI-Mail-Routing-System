package com.yogish.aimailrouting.mailreader.service.impl;


import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.yogish.aimailrouting.mailreader.service.IGmailReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class GmailOAuthBasedReaderService implements IGmailReaderService {
    private static Logger logger = LoggerFactory.getLogger(GmailOAuthBasedReaderService.class);

    @Autowired(required = false)
    private  Gmail gmail;



    public void readUnRead() throws IOException {
        ListMessagesResponse listMessagesResponse = gmail.users().
                messages()
                .list("me")
                .execute();
        logger.info("{}",listMessagesResponse.size());
    }
}

