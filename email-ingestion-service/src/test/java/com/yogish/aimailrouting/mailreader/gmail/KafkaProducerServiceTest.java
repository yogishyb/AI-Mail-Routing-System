package com.yogish.aimailrouting.mailreader.gmail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.stream.IntStream;

@SpringBootTest
class KafkaProducerServiceTest {
    ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(KafkaProducerServiceTest.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void test1() throws JsonProcessingException {

        String s = "a";
        EventA eventA = new EventA("a", "b");

        ObjectMapper objectMapper = new ObjectMapper();
        String strEvent = objectMapper.writeValueAsString(eventA);

        logger.info("a : {}",strEvent);
        long st = System.currentTimeMillis();
        IntStream.range(0, 10).forEach(i -> {
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }

                    if (i % 2 == 0) {
                        logger.info("{}", i);
                    }
                    kafkaTemplate.send("test-topic", eventA);
                }

        );

        logger.info("Total ms: {}", System.currentTimeMillis() - st);


        logger.info(strEvent);

    }

    private record EventA(String a, String b) {

    }
}