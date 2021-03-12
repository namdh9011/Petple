package com.pet.sns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class Webconfig implements WebMvcConfigurer{
	
	private final String ImagePath;

	public Webconfig(@Value("${custom.path.upload-images}")String imagePath) {
		super();
		ImagePath = imagePath;
		System.out.println("ImagePath   : "+ImagePath);
	}
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("swagger-ui.html")
    	.addResourceLocations("classpath:META-INF/resources/");
	      registry.addResourceHandler( "/static/img/**")
	        .addResourceLocations("file:///" + ImagePath+"/")
	        .setCachePeriod(3600)
	        .resourceChain(true)
	        .addResolver(new PathResourceResolver());
    	    
    	  System.out.println(registry);
    }
    


}
