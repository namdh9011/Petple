package com.pet.sns.property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
 
@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
	
	@Value("${custom.path.upload-images}")
    private String uploadDir;
 
    public String getUploadDir() {
        return uploadDir;
    }
 
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

	@Override
	public String toString() {
		return "FileUploadProperties [uploadDir=" + uploadDir + "]";
	}
    
    
}
