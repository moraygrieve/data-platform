package com.riekon.aws.repository;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.riekon.aws.config.repository.AmazonS3Config;
import com.riekon.aws.model.ObjectFileEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;

@Repository
public class ObjectRepository {
    public static final Logger logger = LoggerFactory.getLogger(ObjectRepository.class);

    @Autowired
    private AmazonS3Config amazonS3Config;

    @Autowired
    private AmazonS3Client amazonS3Client;

    public String addObject(String key, ObjectFileEntry entry) {
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentLength(entry.getBytes().length);
        amazonS3Client.putObject(new PutObjectRequest(amazonS3Config.getAmazonS3Bucket(), key, new ByteArrayInputStream(entry.getBytes()), omd));
        return amazonS3Config.getAmazonS3Bucket() + "/" + key;
    }

    public void deleteObject(String key) {
        amazonS3Client.deleteObject(amazonS3Config.getAmazonS3Bucket(), key);
    }
}
