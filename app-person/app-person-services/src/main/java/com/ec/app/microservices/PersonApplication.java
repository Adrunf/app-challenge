package com.ec.app.microservices;

import com.ec.app.microservices.config.BaseAppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Application starter
 *
 * @author arobayo
 */
@EnableDiscoveryClient
@Import({BaseAppConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.ec.app"})
@ComponentScan(basePackages = {"com.ec.app"})
public class PersonApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class);
    }

}
