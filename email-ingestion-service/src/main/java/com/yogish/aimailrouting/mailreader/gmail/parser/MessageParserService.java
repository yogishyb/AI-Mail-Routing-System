package com.yogish.aimailrouting.mailreader.gmail.parser;

import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MessageParserService {

    public EmailMessageEvent parse(MimeMessage m) throws Exception;
}
