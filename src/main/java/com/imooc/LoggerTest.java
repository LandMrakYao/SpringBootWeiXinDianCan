package com.imooc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class LoggerTest {
    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
        log.info("这是info级别");
        log.error("这是error级别");
    }
}
