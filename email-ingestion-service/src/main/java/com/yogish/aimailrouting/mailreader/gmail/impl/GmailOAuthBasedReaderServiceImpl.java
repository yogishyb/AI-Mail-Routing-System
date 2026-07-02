//package com.yogish.aimailrouting.mailreader.gmail.impl;
//
//
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.model.ListMessagesResponse;
//import com.yogish.aimailrouting.mailreader.gmail.MessageReaderService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//
//public class GmailOAuthBasedReaderServiceImpl implements MessageReaderService {
//    private static Logger logger = LoggerFactory.getLogger(GmailOAuthBasedReaderServiceImpl.class);
//
//    @Autowired(required = false)
//    private  Gmail gmail;
//
//
//
//    public void readUnRead() throws IOException {
//        ListMessagesResponse listMessagesResponse = gmail.users().
//                messages()
//                .list("me")
//                .execute();
//        logger.info("{}",listMessagesResponse.size());
//    }
//}
//
