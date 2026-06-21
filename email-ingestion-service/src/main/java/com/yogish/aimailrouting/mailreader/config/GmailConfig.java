//package com.yogish.aimailrouting.mailreader.config;
//
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.GmailScopes;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//import java.util.List;
//
//@Configuration
//public class GmailConfig {
//
//    private static final List<String> SCOPES =
//            List.of(GmailScopes.GMAIL_READONLY);
//
//    @Value("${google.oauth.client-id}")
//    private String clientId;
//
//
//    @Value("${google.oauth.client-secret}")
//    private String clientSecret;
//
//    @Bean
//    public Gmail gmail() throws Exception{
//
//        GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
//        details.setClientId(clientId);
//        details.setClientSecret(clientSecret);
//
//        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
//        clientSecrets.setInstalled(details);
//
//        GoogleAuthorizationCodeFlow authorizationCodeFlow =
//                new GoogleAuthorizationCodeFlow.Builder
//                        (
//                        GoogleNetHttpTransport.newTrustedTransport(),
//                        GsonFactory.getDefaultInstance(),
//                        clientSecrets,
//                        List.of(GmailScopes.GMAIL_READONLY))
//
//                        .setAccessType("offline")
//                        .setDataStoreFactory(new FileDataStoreFactory(
//                                new File("tokens")
//                        ))
//                        .build();
//
//        Credential credential =
//                new AuthorizationCodeInstalledApp(
//                        authorizationCodeFlow,
//                        new LocalServerReceiver.Builder().setPort(8888)
//                                .build()
//                ).authorize("user");
//
//        return new Gmail.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                GsonFactory.getDefaultInstance(),
//                credential
//        )
//                .setApplicationName("ai-mail-routing")
//                .build();
//
//
//
//
//    }
//
//
//}
//
//
