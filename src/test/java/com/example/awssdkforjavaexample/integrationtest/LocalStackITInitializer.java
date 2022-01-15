package com.example.awssdkforjavaexample.integrationtest;


import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import java.net.URI;
import java.util.Map;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public class LocalStackITInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  private static final String DOCKER_IMAGE = "localstack/localstack:0.12.18";
  private static final LocalStackContainer localStackContainer =
      new LocalStackContainer(DockerImageName.parse(DOCKER_IMAGE)).withServices(SQS, SNS);

  static {
    localStackContainer.start();
  }

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    URI sqsEndpoint = localStackContainer.getEndpointOverride(SQS);
    AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
        .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(SQS))
        .withCredentials(localStackContainer.getDefaultCredentialsProvider())
        .build();

    System.out.println("sqs endpoint" + sqsEndpoint.toString());

    System.setProperty("sqs.1x.queue.url", createSqs1xQueue(amazonSQS));
    System.setProperty("aws.accessKeyId", localStackContainer.getAccessKey());
    System.setProperty("aws.secretAccessKey", localStackContainer.getSecretKey());
  }

  private String createSqs1xQueue(AmazonSQS amazonSQS) {
    CreateQueueRequest createQueueRequest = new CreateQueueRequest();

    CreateQueueResult response =
        amazonSQS.createQueue(
            createQueueRequest.withQueueName("sqs-1x-queue.fifo")
                .withAttributes(Map.of("FifoQueue", "true")));

    return response.getQueueUrl();
  }
}
