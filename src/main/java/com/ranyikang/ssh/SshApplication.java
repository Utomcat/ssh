package com.ranyikang.ssh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ranyi
 */
@EnableScheduling
@SpringBootApplication
public class SshApplication {

    public static void main(String[] args) {
        SpringApplication.run(SshApplication.class, args);
    }

}
