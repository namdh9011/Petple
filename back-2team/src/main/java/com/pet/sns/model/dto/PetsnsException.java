package com.pet.sns.model.dto;

public class PetsnsException extends RuntimeException {

	public PetsnsException() {
		super("오류 발생");
	}

	public PetsnsException(String msg) {
		super(msg);
	}

}
