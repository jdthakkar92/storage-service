package com.jaydeep.myproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jaydeep.myproj.service.StorageService;

@RestController
public class StorageController {

	@Autowired
	private StorageService storageService;
	
	
	@PostMapping(value="/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam(value="file") MultipartFile multipartFile) {
		storageService.uploadFile(multipartFile);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
		byte[] data = storageService.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok()
				.contentLength(data.length)
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\""+fileName+"\"")
				.body(resource);
	}
}
