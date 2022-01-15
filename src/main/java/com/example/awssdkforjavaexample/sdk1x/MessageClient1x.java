package com.example.awssdkforjavaexample.sdk1x;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageClient1x {

  private final AmazonSQS amazonSQS;

  public List<Message> receiveMessages(String queueUrl) {
    final ReceiveMessageRequest request = new ReceiveMessageRequest().withQueueUrl(queueUrl)
        .withWaitTimeSeconds(20).withMaxNumberOfMessages(10);

    return amazonSQS.receiveMessage(request).getMessages();
  }

  public SendMessageResult sendMessage(String queueUrl, String groupId) {
    SendMessageRequest messageRequest = new SendMessageRequest(queueUrl, "{}");
    messageRequest.setMessageGroupId(groupId);
    return amazonSQS.sendMessage(messageRequest);
  }

  public void deleteMessage(String queueUrl, Message message) {
    DeleteMessageRequest messageRequest = new DeleteMessageRequest(queueUrl,
        message.getReceiptHandle());
    amazonSQS.deleteMessage(messageRequest);
    log.info("Delete messageId: {} successfully", message.getMessageId());
  }
}
