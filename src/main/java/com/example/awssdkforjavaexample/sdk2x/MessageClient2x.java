package com.example.awssdkforjavaexample.sdk2x;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Slf4j
@Component
@AllArgsConstructor
public class MessageClient2x {

  private final SqsClient sqsClient;

  public List<Message> receiveMessages(String queueUrl) {
    final ReceiveMessageRequest request = ReceiveMessageRequest.builder().queueUrl(queueUrl)
        .waitTimeSeconds(20).maxNumberOfMessages(10).build();

    return sqsClient.receiveMessage(request).messages();
  }

  public SendMessageResponse sendMessage(String queueUrl, String groupId) {
    SendMessageRequest messageRequest = SendMessageRequest.builder().queueUrl(queueUrl)
        .messageGroupId(groupId).messageBody("{}").build();
    return sqsClient.sendMessage(messageRequest);
  }

  public void deleteMessage(String queueUrl, Message message) {
    DeleteMessageRequest messageRequest = DeleteMessageRequest.builder().queueUrl(queueUrl)
        .receiptHandle(message.receiptHandle()).build();
    sqsClient.deleteMessage(messageRequest);
    log.info("Delete messageId: {} successfully", message.messageId());
  }
}
