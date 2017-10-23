package com.guessnumber;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Start Spring Boot Application
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@ComponentScan(basePackages = "com.guessnumber")
public class Application 
{
    public static void main( String[] args )  {
    	SpringApplication.run(Application.class, args);
    }
    

}
