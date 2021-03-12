package com.pet.sns.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pet.sns.model.dao.CommentDaoMgr;
import com.pet.sns.model.dao.MemberDao;
import com.pet.sns.model.dao.PetDao;
import com.pet.sns.model.dao.PostDaoMgr;
import com.pet.sns.model.dto.FileDownloadException;
import com.pet.sns.model.dto.FileUploadException;
import com.pet.sns.model.dto.Member;
import com.pet.sns.model.dto.Pet;
import com.pet.sns.model.dto.Post;
import com.pet.sns.property.FileUploadProperties;

@Service
public class FileUploadDownloadService {

	private final Path fileLocation;
	@Autowired
	private MemberDao userdao;
	@Autowired
	private PetDao petdao;
	@Autowired
	private PostDaoMgr postdao;
	@Autowired
	private CommentDaoMgr commentdao;

	@Autowired
	public FileUploadDownloadService(FileUploadProperties prop) {
		this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
		System.out.println("fileLocation   : " + fileLocation);
		try {
			Files.createDirectories(this.fileLocation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
		}
	}

	private Calendar time = Calendar.getInstance();

	// file:///C:/Program%20Files/Project/s02p13a202/back-2team/uploads/2userpofile.jpg
	public String storeuserFile(MultipartFile file, String unum) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(" user  fileName   : " + fileName);
		try {
			// 파일명에 부적합 문자가 있는지 확인한다.
			if (fileName.contains(".."))
				throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);

//			String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(time.getTime()));
			double today = Math.floor(Math.random()*10000000);			 
			 
			fileName = unum + "userprofile" + (int)today +".png"; // 유저별로 구분할 방법 필요
			Path targetLocation = this.fileLocation.resolve(fileName);
			System.out.println("fileName   : " + fileName);
			System.out.println("targetLocation   : " + targetLocation);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("file.getInputStream(    " + file.getInputStream());
			Member user = userdao.selectOne(unum);
			System.out.println(user);
			user.setUserprofile("http://52.78.132.70:8080/petple/static/img/" + fileName);
			// http://52.78.132.70:8082/static/img/default.png
			userdao.updateuserprofile(user);
			System.out.println("update 이후 filename" + user.getUserprofile());
			commentdao.changeprofile(Integer.parseInt(unum));
				
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
			// throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시
			// 시도하십시오.", e);
		}
		return fileName;
	}

	public String storepetFile(MultipartFile file, String pnum) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(" pet  fileName   : " + fileName);
		try {
			// 파일명에 부적합 문자가 있는지 확인한다.
			if (fileName.contains(".."))
				throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
		//	String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(time.getTime()));
			double today = Math.floor(Math.random()*10000000);

			fileName = pnum + "petprofile" + (int)today+".png"; // 유저별로 구분할 방법 필요
			Path targetLocation = this.fileLocation.resolve(fileName);
			System.out.println("fileName   : " + fileName);
			System.out.println("targetLocation   : " + targetLocation);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("file.getInputStream(    " + file.getInputStream());
			Pet pet = petdao.selectOne(pnum);
			pet.setImg("http://52.78.132.70:8080/petple/static/img/" + fileName);
			petdao.updateimg(pet);

			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			// throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시
			// 시도하십시오.", e);
		}
		return fileName;
	}

	public String storepostFile(MultipartFile file, int pnum) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			System.out.println("post file upload");
			// 파일명에 부적합 문자가 있는지 확인한다.
			if (fileName.contains(".."))
				throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
//			String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(time.getTime()));
			 double today = Math.floor(Math.random()*10000000);
			 
			fileName = pnum + "postprofile" + (int)today+".png";
			Path targetLocation = this.fileLocation.resolve(fileName);

			System.out.println("fileName   : " + fileName);
			System.out.println("targetLocation   : " + targetLocation);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			Post post = postdao.selectone(pnum);
			System.out.println(post);
			post.setImg("http://52.78.132.70:8080/petple/static/img/" + fileName);
			postdao.update(post);
			System.out.println(post);

			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			// throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시
			// 시도하십시오.", e);
		}
		return fileName;
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists()) {
				return resource;
			} else {
				throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
		}
	}

}
