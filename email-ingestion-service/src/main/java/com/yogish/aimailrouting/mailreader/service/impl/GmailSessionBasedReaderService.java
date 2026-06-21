package com.yogish.aimailrouting.mailreader.service.impl;

import com.yogish.aimailrouting.mailreader.service.IGmailReaderService;
import jakarta.mail.*;
import jakarta.mail.search.FlagTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GmailSessionBasedReaderService implements IGmailReaderService {

    private static Logger logger = LoggerFactory.getLogger(GmailSessionBasedReaderService.class);


    @Autowired
    @Qualifier("gmailSession")
    private Session session;

    @Value("${gmail.app.username}")
    private String username;

    @Value("${gmail.app.password}")
    private String appPassword;



    @Override
    public void readUnRead() throws IOException {
        try {
            logger.info("reading");
            Store store = session.getStore("imaps");

            store.connect(
                    "imap.gmail.com",
                    username,
                    appPassword);

            Folder inbox =
                    store.getFolder("INBOX");

            inbox.open(Folder.READ_WRITE);

            Message[] unread =
                    inbox.search(
                            new FlagTerm(
                                    new Flags(
                                            Flags.Flag.SEEN),
                                    false));

            for (Message message : unread) {

                logger.info(
                        "From={} Subject={}",
                        message.getFrom()[0],
                        message.getSubject());

                // Mark as read
                message.setFlag(
                        Flags.Flag.SEEN,
                        true);
            }

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            logger.error("Error reading mail", e);
        }
    }
}
