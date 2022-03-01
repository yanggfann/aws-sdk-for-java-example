package com.example.awssdkforjavaexample.schedule;

import com.example.awssdkforjavaexample.sdk2x.MessageClient2x;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import software.amazon.awssdk.services.sqs.model.Message;

@Slf4j
@Builder
@AllArgsConstructor
public class SqsPoller2x {
  private final String queueUrl;
  private final MessageClient2x messageClient2x;

  @Scheduled(fixedDelay = 1000)
  public void poll() {
    try {
      final List<Message> messages = messageClient2x.receiveMessages(queueUrl);
      log.info(
          "Received {} messages from leads queue: {}",
          messages.size(), messages);

      messages.forEach(message -> messageClient2x.deleteMessage(queueUrl, message));
    } catch (final RuntimeException e) {
      log.error("Processing failed", e);
    }
  }
}
