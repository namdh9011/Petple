package com.pet.sns.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pet.sns.model.dto.Comment;
import com.pet.sns.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Api("Comment Rest API")
public class CommentController {
	@Autowired
	private CommentService service;
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
	@GetMapping("/comment/selectall/{postnum}")
	@ApiOperation("해당 게시물 번호에 맞는 모든 댓글 가져오기 ")
	public ResponseEntity<Map<String, Object>> selectall(@PathVariable int postnum) {
		System.out.println("comment Search All...........");
		List<Comment> find = service.selectall(postnum);
		System.out.println("find............"+find);
		return handleSuccess(find);
	}
	@PostMapping("comment/insert")
	@ApiOperation("댓글 등록")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Comment comment ) {
		System.out.println("comment insert..........." + comment);
		service.insert(comment);
		return handleSuccess("댓글 등록 완료");
	}
	/*
	 * @PutMapping("comment/update")
	 *
	 * @ApiOperation("댓글 수정") public ResponseEntity<Map<String, Object>>
	 * update(@RequestBody Comment comment ) {
	 * System.out.println("comment update..........."); // 해시태그도
	 * service.update(comment); return handleSuccess("댓글  수정 완료"); }
	 */
	@DeleteMapping("comment/delete/{no}")
	@ApiOperation("comment 삭제")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable int no) {
		System.out.println("comment delete...........");
		service.delete(no);
		return handleSuccess("comment 삭제 완료");
	}
}