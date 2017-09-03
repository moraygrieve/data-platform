package com.riekon.data.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonBaseConfig {

    @Value("${amazon.aws.accesskey}")
    protected String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    protected String amazonAWSSecretKey;

    public String getAmazonAWSAccessKey() { return amazonAWSAccessKey; }
    public String getAmazonAWSSecretKey() { return amazonAWSSecretKey; }
}
