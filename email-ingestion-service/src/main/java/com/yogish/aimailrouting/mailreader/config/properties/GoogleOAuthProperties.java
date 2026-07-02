package com.yogish.aimailrouting.mailreader.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.oauth")
public class GoogleOAuthProperties {

}
