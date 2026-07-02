package com.yogish.aimailrouting.mailreader.config.properties;


import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "gmail.session.app")
public record GmailAppProperties(
        @NotBlank
        String gmailId,

        @NotBlank
        String password,

        @NotBlank
        String host
        )
{


}
