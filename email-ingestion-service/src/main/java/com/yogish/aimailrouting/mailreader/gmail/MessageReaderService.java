package com.yogish.aimailrouting.mailreader.gmail;

import com.yogish.aimailrouting.mailreader.model.EmailMessageEvent;
import jakarta.mail.Message;

import java.io.IOException;
import java.util.List;

public interface MessageReaderService {

    public List<EmailMessageEvent> getUnreadMessages() throws IOException;
}
