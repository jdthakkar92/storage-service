package com.jaydeep.myproj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {

	private String accessKey = "AKIARU6UI5QZJJM56B4J";
	private String secretKey = "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";
	private String region = "ap-northeast-1";
	
	
	@Bean
	public AmazonS3 s3Client() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
		return AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(region).build();
	}
}
