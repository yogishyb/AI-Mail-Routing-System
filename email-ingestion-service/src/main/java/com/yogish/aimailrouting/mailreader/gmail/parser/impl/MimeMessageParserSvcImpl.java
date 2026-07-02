package com.yogish.aimailrouting.mailreader.gmail.parser.impl;

import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;
import com.yogish.aimailrouting.mailreader.gmail.parser.MessageParserService;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// todo null handling
@Component
public class MimeMessageParserSvcImpl implements MessageParserService {

    private final Logger logger= LoggerFactory.getLogger(MimeMessageParserSvcImpl.class);
    @Override
    public EmailMessageEvent parse(MimeMessage message) throws Exception {
        logger.info("parsing message from : {}", message.getFrom()[0].toString());

        return EmailMessageEvent.builder()
                .messageId(message.getMessageID())
                .from(message.getFrom()[0].toString())
                .to(
                        Arrays.stream(message.getRecipients(Message.RecipientType.TO))
                                .map(Address::toString)
                                .toList()

                )
                .body(extractBody(message)
                       )
                .subject(message.getSubject())
                .receivedAt(message.getReceivedDate().toInstant())
                .build();
    }

    private String extractBody(Part part) throws Exception {

        if (part.isMimeType("text/plain")) {
            return (String) part.getContent();
        }

        if (part.isMimeType("text/html")) {
            return (String) part.getContent();
        }

        if (part.isMimeType("multipart/*")) {

            Multipart mp = (Multipart) part.getContent();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                result.append(extractBody(bp));
            }

            return result.toString();
        }

        return "";
    }
}
