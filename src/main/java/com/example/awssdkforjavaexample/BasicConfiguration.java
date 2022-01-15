package com.example.awssdkforjavaexample;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfiguration {

  @Bean
  public AmazonSQS amazonSQS(@Value("${aws.region}") String region) {
    return AmazonSQSClientBuilder.standard().withRegion(region).build();
  }
}
