package com.yogish.aimailrouting.mailreader.scheduler;


import com.yogish.aimailrouting.mailreader.service.IGmailReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailReaderScheduler {

    @Autowired
    @Qualifier("gmailSessionBasedReaderService")
    private IGmailReaderService iGmailReaderService;


    @Scheduled(fixedDelay = 3000)
    private void pollMessages() throws IOException {
        iGmailReaderService.readUnRead();
    }

}
