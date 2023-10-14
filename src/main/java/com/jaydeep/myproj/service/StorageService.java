package com.jaydeep.myproj.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class StorageService {
	
	private String bucketName = "testbucketapnorth";

	@Autowired
	private AmazonS3 s3Client;
	
	public void uploadFile(MultipartFile multipartFile) {
		File file = convertMultipartFileToFile(multipartFile);
		s3Client.putObject(new PutObjectRequest(bucketName, multipartFile.getOriginalFilename(), file));
		file.delete();
	}
	
	public byte[] downloadFile(String fileName) {
		S3ObjectInputStream  inputStream = s3Client.getObject(bucketName, fileName).getObjectContent();
		try {
			return  IOUtils.toByteArray(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private File convertMultipartFileToFile(MultipartFile multipartFile) {
		File file = new File(multipartFile.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(file)){
			fos.write(multipartFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
