# aws-sdk-for-java-example

[AWS SDK for Java](https://docs.aws.amazon.com/sdk-for-java/index.html)

## [AWS SDK for Java, version 1](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/welcome.html)

The [AWS SDK for Java](https://aws.amazon.com/sdk-for-java/) provides a Java API for AWS services. Using the SDK, you can easily build Java applications that work with Amazon S3, Amazon EC2, DynamoDB, and more. We regularly add support for new services to the AWS SDK for Java. For a list of the supported services and their API versions that are included with each release of the SDK, view the [release notes](https://github.com/aws/aws-sdk-java#release-notes) for the version that you’re working with.

### AmazonSQS

Default endpoint: https://sqs.ap-southeast-2.amazonaws.com

[Working with Amazon SQS Message Queues](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-sqs-message-queues.html)

## [AWS SDK for Java, version 2](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)

The AWS SDK for Java provides a Java API for AWS services. Using the SDK, you can easily build Java applications that work with Amazon S3, Amazon EC2, DynamoDB, and more.

**The AWS SDK for Java 2.x is a major rewrite of the version 1.x code base.** It’s built on top of Java 8+ and adds several frequently requested features. These include **support for non-blocking I/O** and the ability to plug in a different HTTP implementation at run time. For more information see the [AWS blog](https://aws.amazon.com/blogs/developer/aws-sdk-for-java-2-0-developer-preview/).

We regularly add support for new services to the AWS SDK for Java. For a list of changes and features in a particular version, view the [change log](https://github.com/aws/aws-sdk-java-v2/blob/master/CHANGELOG.md).

### [AWS SDK for Java 2.0 – Developer Preview](https://aws.amazon.com/blogs/developer/aws-sdk-for-java-2-0-developer-preview/)

> Although we’re excited about the AWS SDK for Java 2.0 Developer Preview, we also want to reassure customers that we’re not dropping support for the 1.x line of the SDK any time soon. We know there are lots of customers who depend on 1.x versions of the SDK, and we will continue to support them.

#### Non-blocking I/O

`AWS SDK for Java 2.0` uses an HTTP client built on top of [Netty](https://netty.io/) to make the non-blocking HTTP call.

- Netty(I'll introduce it in the future)

  Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients.
  Netty is a **NIO** client server framework which enables quick and easy development of network applications such as protocol servers and clients.

Look at [IO Models](./doc/IO%20Models.md) for more information.

### SqsClient

Default endpoint: https://sqs.ap-southeast-2.amazonaws.com

[Working with Amazon Simple Queue Service message queues](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-sqs-message-queues.html)

## Why the AWS SQS default endpoint is https://sqs.ap-southeast-2.amazonaws.com/ ?

You can find the reason according to the following steps in the origin code: 

```
SqsClient.builder()
DefaultSqsClientBuilder
super.syncClientConfiguration()
finalizeChildConfiguration
.option(SdkClientOption.ENDPOINT, resolveEndpoint(configuration))
endpointFromConfig
getServiceEndpoint
```
