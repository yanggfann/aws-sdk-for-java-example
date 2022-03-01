package com.example.awssdkforjavaexample;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;
import software.amazon.awssdk.utils.StringUtils;

@Configuration
public class BasicConfiguration {

  @Value("${aws.endpointOverride:}")
  private String awsEndpointOverride;

  @Bean
  public AmazonSQS amazonSQS(@Value("${aws.region}") String region) {
    return AmazonSQSClientBuilder.standard().withRegion(region).build();
  }

  @Bean
  public SqsClient sqsClient(@Value("${aws.region}") String region) {
    SqsClientBuilder builder = SqsClient.builder();

    if (StringUtils.isNotBlank(awsEndpointOverride)) {
      builder.endpointOverride(URI.create(awsEndpointOverride));
    }
    return builder.region(Region.of(region)).build();
  }
}
