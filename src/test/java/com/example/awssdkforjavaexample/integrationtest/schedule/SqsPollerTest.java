package com.example.awssdkforjavaexample.integrationtest.schedule;

import com.example.awssdkforjavaexample.integrationtest.IntegrationTestBase;
import com.example.awssdkforjavaexample.schedule.SqsPoller;
import com.example.awssdkforjavaexample.sdk1x.MessageClient1x;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class SqsPollerTest extends IntegrationTestBase {
  @Autowired private SqsPoller sqs1xPoller;
  @Autowired private MessageClient1x messageClient1x;

  @Value("${sqs.1x.queue.url}")
  String sqs1xQueueUrl;

  @Test
  public void pollSuccessfully() {
    messageClient1x.sendMessage(sqs1xQueueUrl, "groupId1");

    sqs1xPoller.poll();

    Assertions.assertEquals(0, messageClient1x.receiveMessages(sqs1xQueueUrl).size());
  }

}
