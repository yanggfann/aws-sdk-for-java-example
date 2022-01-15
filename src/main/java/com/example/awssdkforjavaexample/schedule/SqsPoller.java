package com.example.awssdkforjavaexample.schedule;

import com.amazonaws.services.sqs.model.Message;
import com.example.awssdkforjavaexample.sdk1x.MessageClient1x;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Builder
@AllArgsConstructor
public class SqsPoller {
  private final String queueUrl;
  private final MessageClient1x messageClient1x;

  @Scheduled(fixedDelay = 1000)
  public void poll() {
    try {
      final List<Message> messages = messageClient1x.receiveMessages(queueUrl);
      log.info(
          "Received {} messages from leads queue: {}",
          messages.size(), messages);

      messages.forEach(message -> messageClient1x.deleteMessage(queueUrl, message));
    } catch (final RuntimeException e) {
      log.error("Processing failed", e);
    }
  }
}
