package com.yogish.aimailrouting.mailreader.gmail.impl;

import com.yogish.aimailrouting.mailreader.config.properties.GmailAppProperties;
import com.yogish.aimailrouting.mailreader.gmail.MessageReaderService;
import com.yogish.aimailrouting.mailreader.gmail.parser.MessageParserService;
import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.FlagTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//todo  too many responsibilities,
// create separate store manager
// create calls back for message read only after successful processing
// below logic is not scaleble, it can read 1000k messsages at once n system will get OOM

@Service
public class GmailMessageReaderServiceImpl implements MessageReaderService {

    private static Logger logger = LoggerFactory.getLogger(GmailMessageReaderServiceImpl.class);


    private final Session session;

    private final GmailAppProperties gmailAppProperties;

    private final MessageParserService messageParserService;

    public GmailMessageReaderServiceImpl(Session session, GmailAppProperties gmailAppProperties, MessageParserService messageParserService) {
        this.session = session;
        this.gmailAppProperties = gmailAppProperties;
        this.messageParserService = messageParserService;
    }


    @Override
    public List<EmailMessageEvent> getUnreadMessages() throws IOException {
        try {
            logger.info("reading");
            Store store = session.getStore("imaps");
//imap.gmail.com
            store.connect(
                    gmailAppProperties.host(),
                    gmailAppProperties.gmailId(),
                    gmailAppProperties.password());

            Folder inbox =
                    store.getFolder("INBOX");

            inbox.open(Folder.READ_WRITE);
            logger.info("opened inbox");
            Message[] unread =
                    inbox.search(
                            new FlagTerm(
                                    new Flags(
                                            Flags.Flag.SEEN),
                                    false));


            List<EmailMessageEvent> emailMessageEvents = new ArrayList<>();
            for (Message message : unread) {

                // Mark as read
                message.setFlag(
                        Flags.Flag.SEEN,
                        true);

                MimeMessage mimeMessage = (MimeMessage) message;
                EmailMessageEvent parsedMessage = messageParserService.parse(mimeMessage);
                emailMessageEvents.add(parsedMessage);
            }

            inbox.close(true);
            store.close();

            return emailMessageEvents;

        } catch (Exception e) {
            logger.error("Error reading mail", e);
        }


        return new ArrayList<>();
    }


}
