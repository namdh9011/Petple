package com.pet.sns.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.sns.model.dto.Pet;

@Repository("petdao")
public class PetDaoImpl implements PetDao{

	@Autowired
	SqlSession session;

	
	@Override
	public List<Pet> testAll() {
		return session.selectList("pet.testAll");
	}
	
	@Override
	public List<Pet> selectAll(String unum) {
		return session.selectList("pet.selectAll", unum);
		
	}

	@Override
	public Pet selectOne(String petnum) {
		return session.selectOne("pet.selectOne",petnum);
	}

	@Override
	public void insert(Pet p) {
		session.insert("pet.insert", p);
	}

	@Override
	public void update(Pet p) {
		session.update("pet.update", p);
	}

	@Override
	public void updateimg(Pet p) {
		session.update("pet.updateimg", p);
	}

	@Override
	public void delete(String petnum) {
		session.delete("pet.delete", petnum);
	}

	@Override
	public List<Pet> selectPetnum(Pet p) {
		return session.selectList("pet.selectPetnum", p);
	}


	
}
