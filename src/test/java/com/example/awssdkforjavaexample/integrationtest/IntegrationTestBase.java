package com.example.awssdkforjavaexample.integrationtest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest()
@ContextConfiguration(
    initializers = {LocalStackITInitializer.class})
@ActiveProfiles("it")
public class IntegrationTestBase {
  static {
    System.setProperty("aws.region", "ap-southeast-2");
  }
}
