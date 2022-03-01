package com.example.awssdkforjavaexample.integrationtest.schedule;

import com.example.awssdkforjavaexample.integrationtest.IntegrationTestBase;
import com.example.awssdkforjavaexample.schedule.SqsPoller2x;
import com.example.awssdkforjavaexample.sdk2x.MessageClient2x;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class SqsPoller2xTest extends IntegrationTestBase {

  @Autowired private SqsPoller2x sqs2xPoller;
  @Autowired private MessageClient2x messageClient2x;

  @Value("${sqs.2x.queue.url}")
  String sqs2xQueueUrl;

  @Test
  public void pollSuccessfully() {
    messageClient2x.sendMessage(sqs2xQueueUrl, "groupId2");

    sqs2xPoller.poll();

    Assertions.assertEquals(0, messageClient2x.receiveMessages(sqs2xQueueUrl).size());
  }

}
