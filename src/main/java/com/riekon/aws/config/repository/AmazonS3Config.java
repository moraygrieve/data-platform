package com.riekon.aws.config.repository;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.riekon.aws.config.aws.AmazonBaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config  extends AmazonBaseConfig {

    @Value("${amazon.s3.bucket}")
    private String amazonS3Bucket;

    @Bean
    public AmazonS3Client amazonS3Client() {
        return new AmazonS3Client(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
    }

    public String getAmazonS3Bucket() { return amazonS3Bucket; }
}
