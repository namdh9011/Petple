package com.pet.sns.model.dao;


import java.util.List;

import com.pet.sns.model.dto.Pet;

public interface PetDao {
	public List<Pet> testAll();
	public List<Pet> selectAll(String unum);
	public Pet selectOne(String petnum);
	public List<Pet> selectPetnum(Pet p);
	public void insert(Pet p);
	public void update(Pet p);
	public void updateimg(Pet p);
	public void delete(String petnum);
}
