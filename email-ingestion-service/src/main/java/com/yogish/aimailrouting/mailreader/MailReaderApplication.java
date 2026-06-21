package com.yogish.aimailrouting.mailreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailReaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailReaderApplication.class, args);

    }
}