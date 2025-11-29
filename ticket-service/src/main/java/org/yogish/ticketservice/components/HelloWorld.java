package org.yogish.ticketservice.components;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    @PostConstruct
    public void test(){
        logger.info("Yogish Started");
    }
}
