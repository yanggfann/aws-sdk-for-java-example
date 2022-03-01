package com.example.awssdkforjavaexample;

import com.example.awssdkforjavaexample.schedule.SqsPoller1x;
import com.example.awssdkforjavaexample.schedule.SqsPoller2x;
import com.example.awssdkforjavaexample.sdk1x.MessageClient1x;
import com.example.awssdkforjavaexample.sdk2x.MessageClient2x;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConsumeConfiguration {

  @Bean
  public SqsPoller1x sqs1xPoller(@Value("${sqs.1x.queue.url}") String queueUrl,
      MessageClient1x messageClient1x) {
    System.out.println("Build sqs1xPoller");
    return SqsPoller1x.builder().queueUrl(queueUrl).messageClient1x(messageClient1x).build();
  }

  @Bean
  public SqsPoller2x sqs2xPoller(@Value("${sqs.2x.queue.url}") String queueUrl,
      MessageClient2x messageClient2x) {
    System.out.println("Build sqs2xPoller");
    return SqsPoller2x.builder().queueUrl(queueUrl).messageClient2x(messageClient2x).build();
  }

}
