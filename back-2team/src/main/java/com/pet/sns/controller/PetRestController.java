package com.pet.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.sns.model.dto.Pet;
import com.pet.sns.service.PetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@Api("Pet Rest API")
public class PetRestController {
	@Autowired
	PetService pservice;
	
	@RequestMapping(value = "/petall/test", method = RequestMethod.GET)
	@ApiOperation("frontend가 모든 DB정보를 볼 수 있는 test")
	public List<Pet> testAll() {
		return pservice.testAll();
	}
	
	@RequestMapping(value = "/petall/{unum}", method = RequestMethod.GET)
	@ApiOperation("회원의 모든 소유 펫 검색")
	public List<Pet> selectAll(@PathVariable String unum) {
		return pservice.selectAll(unum);
	}
	
	@RequestMapping(value = "/petone/{petnum}", method = RequestMethod.GET)
	@ApiOperation("펫 한마리 정보")
	public Pet selectOne(@PathVariable String petnum) {
		return pservice.selectOne(petnum);
	}
	
	@RequestMapping(value = "/pet", method = RequestMethod.POST)
	@ApiOperation("펫 정보 추가")
	public Map<String, String> insert(@RequestBody Pet p) {
		HashMap<String, String> map = new HashMap<String, String>();
		pservice.insert(p);
		List<Pet> petlist = pservice.selectPetnum(p);
		String petnum = petlist.get(petlist.size()-1).getPetnum();
		map.put("result", "추가성공");
		map.put("petnum", petnum);
		return map;
	}
	
	@RequestMapping(value = "/petupdate", method = RequestMethod.PUT)
	@ApiOperation("펫 정보 수정")
	public Map<String, String> updatePassword(@RequestBody Pet p) {
		HashMap<String, String> map = new HashMap<String, String>();
		pservice.update(p);
		map.put("result", "수정성공");
		return map;
	}
	
	@RequestMapping(value = "/petdelete/{petnum}", method = RequestMethod.DELETE)
	@ApiOperation("펫 정보 삭제")
	public Map<String, String> delete(@PathVariable String petnum) {
		pservice.delete(petnum);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "삭제성공");
		return map;
	}
}