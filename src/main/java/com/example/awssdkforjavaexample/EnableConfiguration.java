package com.example.awssdkforjavaexample;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Profile({"local", "dev", "prod"})
public class EnableConfiguration {}
