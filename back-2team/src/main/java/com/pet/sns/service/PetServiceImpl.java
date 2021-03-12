package com.pet.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.sns.model.dao.PetDao;
import com.pet.sns.model.dto.Pet;

@Service("petservice")
public class PetServiceImpl implements PetService {

	@Autowired
	private PetDao petdao;

	@Override
	public List<Pet> testAll() {
		return petdao.testAll();
	}

	@Override
	public List<Pet> selectAll(String unum) {
		return petdao.selectAll(unum);
	}

	@Override
	public Pet selectOne(String petnum) {
		return petdao.selectOne(petnum);
	}

	@Override
	public void insert(Pet p) {
		petdao.insert(p);
	}

	@Override
	public void update(Pet p) {
		petdao.update(p);
	}

	@Override
	public void delete(String petnum) {
		petdao.delete(petnum);
	}

	@Override
	public void updateimg(Pet p) {
		petdao.updateimg(p);
	}

	@Override
	public List<Pet> selectPetnum(Pet p) {
		return petdao.selectPetnum(p);
	}

}
