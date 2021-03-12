package com.pet.sns.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pet.sns.model.dto.FileUploadResponse;
import com.pet.sns.service.FileUploadDownloadService;
import com.pet.sns.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileUploadDownloadService service;
	@Autowired
	private MemberService userservice;

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handle(Exception e) {
		return handleFail(e.getMessage(), HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> handleFail(Object data, HttpStatus state) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "fail");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, state);
	}

	@PostMapping("/userprofileUpload")
	@ApiOperation("유저프로필 파일 업로드하기")
	public ResponseEntity<Map<String, Object>> userprofileUpload(@RequestParam("file") MultipartFile file,
			String unum) {
		// user num 받아와서 unum+userpofile+fileName 으로 저장하는게 좋을거같음
		String fileName = service.storeuserFile(file, unum);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		System.out.println(fileName);
		System.out.println(fileDownloadUri);
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		Date date = new Date();

		System.out.println(date);
		return handleSuccess(new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));

	}

	@PostMapping("/petprofileUpload")
	@ApiOperation("펫 프로필 사진 파일 업로드하기")
	public ResponseEntity<Map<String, Object>> uploadpetFile(@RequestParam("file") MultipartFile file, String pnum) {
		// user num 받아와서 unum+userpofile+fileName 으로 저장하는게 좋을거같음
		String fileName = service.storepetFile(file, pnum);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		System.out.println(fileName);
		System.out.println(fileDownloadUri);
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		Date date = new Date();

		System.out.println(date);

		return handleSuccess(new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
	}

	@PostMapping("/postfileUpload")
	@ApiOperation(" 사진 파일 업로드하기")
	public FileUploadResponse postfileUpload(@RequestParam("file") MultipartFile file, int pnum) {
		// user num 받아와서 unum+userpofile+fileName 으로 저장하는게 좋을거같음
		System.out.println("postfileUpload....................");
		String fileName = service.storepostFile(file, pnum);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		System.out.println(fileName);
		System.out.println(fileDownloadUri);
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		Date date = new Date();

		System.out.println(date);

		return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	/// ResponseEntity<Map<String, Object>>
	public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, int pnum) {
		return Arrays.asList(files).stream().map(file -> postfileUpload(file, pnum)).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = service.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

//	@DeleteMapping("/deleteFile")
//	@ApiOperation("서버에 있는 파일 지우기")
//	public ResponseEntity<Map<String, Object>> deleteFile(@PathVariable int unum) {
//
//		String path = "C:\"Program Files\"Project\"s02p13a202\"back-2team\"uploads";
//		// DB null로 set 해야겠다 , 이미지가 null 일때 어떻게 할지? 기본값을 정해놔야겠다
//
//		return handleSuccess("서버에 있는 파일 삭제 완료");
//
//	}
	/*
	 * public @ResponseBody String boardFileDelete() {
	 * 
	 * String path = "C:\Users\Public\Pictures\Sample Pictures\abc.jpg"​ // 삭제할 파일의
	 * 경로 File file = new File(path); if(file.exists() == true){ file.delete(); }
	 * return null; }
	 */

}
