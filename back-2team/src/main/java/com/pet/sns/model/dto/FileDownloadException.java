package com.pet.sns.model.dto;

public class FileDownloadException extends RuntimeException {
	public FileDownloadException(String message) {
		super(message);
	}

	public FileDownloadException(String message, Throwable cause) {
		super(message, cause);
	}
}
