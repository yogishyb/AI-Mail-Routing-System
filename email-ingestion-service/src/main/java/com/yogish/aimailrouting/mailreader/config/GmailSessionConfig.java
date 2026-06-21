package com.yogish.aimailrouting.mailreader.config;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class GmailSessionConfig {

    @Value("${gmail.app.username}")
    private String username;

    @Value("${gmail.app.password}")
    private String appPassword;

    @Bean
    public Session gmailSession() {

        Properties props = new Properties();

        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");

        return Session.getInstance(props);
    }
}
