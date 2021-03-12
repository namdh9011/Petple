package com.pet.sns.model.dto;

public class Pet {
	private String petnum;
	private String unum;
	private String name;
	private String animal;
	private String gender;
	private String breed;
	private String age;
	private String firstday;
	private String img;
	private String intro;

	public Pet() {
	}

	public Pet(String petnum, String img) {
		super();
		this.petnum = petnum;
		this.img = img;
	}

	public Pet(String petnum, String unum, String name, String animal, String gender, String breed, String age,
			String firstday, String img, String intro) {
		this.petnum = petnum;
		this.unum = unum;
		this.name = name;
		this.animal = animal;
		this.gender = gender;
		this.breed = breed;
		this.age = age;
		this.firstday = firstday;
		this.img = img;
		this.intro = intro;
	}

	public String getPetnum() {
		return petnum;
	}

	public void setPetnum(String petnum) {
		this.petnum = petnum;
	}

	public String getUnum() {
		return unum;
	}

	public void setUnum(String unum) {
		this.unum = unum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstday() {
		return firstday;
	}

	public void setFirstday(String firstday) {
		this.firstday = firstday;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
