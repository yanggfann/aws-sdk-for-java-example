package com.example.awssdkforjavaexample;

import com.example.awssdkforjavaexample.schedule.SqsPoller;
import com.example.awssdkforjavaexample.sdk1x.MessageClient1x;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConsumeConfiguration {

  @Bean
  public SqsPoller sqs1xPoller(@Value("${sqs.1x.queue.url}") String queueUrl, MessageClient1x messageClient1x) {
    System.out.println("ddd");
    return SqsPoller.builder().queueUrl(queueUrl).messageClient1x(messageClient1x).build();
  }

}
