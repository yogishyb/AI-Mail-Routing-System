package com.yogish.aimailrouting.mailreader.gmail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogish.aimailrouting.mailreader.gmail.dto.TestEmail;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.IntStream;


//@SpringBootTest
public class GmailIMAPProducer {
    private Logger logger = LoggerFactory.getLogger(GmailIMAPProducer.class);

    @Value("${gmail.session.mail-produce.gmailId}")
    private String fromEmail;

    @Value("${gmail.session.mail-produce.password}")
    private String fromEmailAppPassword;

    @Value("${gmail.session.mail-produce.gmailId}")
    private String toEmail;

    public void sendMail(
            String fromEmail,
            String appPassword,
            String toEmail,
            String subject,
            String body) throws MessagingException {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                fromEmail,
                                appPassword
                        );
                    }
                });

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(fromEmail));

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail));

        message.setSubject(subject);

        message.setText(body);

        Transport.send(message);
    }


    @Test
    void shouldSendUniqueTestMail() throws Exception {
        GmailIMAPProducer producer = new GmailIMAPProducer();

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props);

        Transport transport = session.getTransport("smtp");

        transport.connect(
                "smtp.gmail.com",
                fromEmail,
                fromEmailAppPassword
        );

        for (int i = 0; i < 100; i++) {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject("Test-" + UUID.randomUUID());

            message.setText("Body");

            transport.sendMessage(
                    message,
                    message.getAllRecipients()
            );
        }

        transport.close();

        IntStream.range(0, 100).forEach(i->{
            String id = UUID.randomUUID().toString();

            String subject = "Mail Reader Test - " + id;

            String body = """
            Test Email
            
            Id: %s
            Timestamp: %s
            
            Generated automatically by integration test.
            """.formatted(
                    id,
                    Instant.now()
            );


            try {
                producer.sendMail("yogishiphone@gmail.com",
                        "bjdnyppdrdtqstda",
                        "yogishiphone@gmail.com",
                        subject,
                        body
                );

                logger.info("count {}",i);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldSendIndustryEmails() throws Exception {

        InputStream stream =
                getClass().getResourceAsStream("/sample-emails.json");

        List<TestEmail> emails =
                objectMapper.readValue(
                        stream,
                        new TypeReference<List<TestEmail>>() {}
                );

        GmailIMAPProducer producer = new GmailIMAPProducer();

        for (TestEmail email : emails) {

            String correlationId =
                    UUID.randomUUID().toString();

            String subject =
                    "[" + email.industry() + "] "
                            + email.subject()
                            + " | "
                            + correlationId;

            String body =
                    email.body()
                            + "\n\nIndustry: " + email.industry()
                            + "\nDepartment: " + email.department()
                            + "\nPriority: " + email.priority()
                            + "\nExpectedQueue: " + email.expectedQueue()
                            + "\nLanguage: " + email.language()
                            + "\nGeneratedAt: " + Instant.now()
                            + "\nCorrelationId: " + correlationId;

            try {
                producer.sendMail(
                        "yourgmail@gmail.com",
                        "your-app-password",
                        "yourgmail@gmail.com",
                        subject,
                        body
                );

                Thread.sleep(500); // avoid Gmail throttling
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

}