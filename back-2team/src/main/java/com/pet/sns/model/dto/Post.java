package com.pet.sns.model.dto;

/*create table post(
		pnum int primary key auto_increment,
	    unum int not null,
	    title varchar(30),
	    img varchar(50),
	    contents varchar(1000),
	    likes int default 0,
	    hits int default 0, 
	    comments int default 0,
	    date datetime,
		constraint fk_unum foreign key(unum) references user(unum)
	);
*/

/*
create table post(
		pnum int primary key auto_increment,
	    unum int not null,
	    title varchar(30),
	    img varchar(50),
	    contents varchar(1000),
		tags varchar(100),
	    likes int default 0, 	-- 좋아요수
	    hits int default 0, 	-- 조회수
	    comments int default 0,	-- 댓글수? 댓글 테이블의 숫자를 세야하는 건가?
	    date datetime,
	    privacy varchar(30) default 'all', -- 공개범위 (only me, only follower, all)
		constraint fk_unum foreign key(unum) references user(unum)
	);*/
public class Post {

	private int pnum;
	private int unum;
	private String title;
	private String img;
	private String contents;
	private String tags;
	private int likes;
	private int hits;
	private int comments;
	private String date;
	private String privacy;

	public Post() {
	}

	public Post(int pnum, int unum) {
		this.pnum = pnum;
		this.unum = unum;
	}

	public Post(int unum, String title, String img, int comments,  String tags, String date, String privacy) {
		this.unum = unum;
		this.title = title;
		this.img = img;
		this.comments = comments;
		this.tags = tags;
		this.date = date;
		this.privacy = privacy;
	}

	public Post(int pnum, int unum, String title, String img, int comments, String date, String privacy) {
		this.pnum = pnum;
		this.unum = unum;
		this.title = title;
		this.img = img;
		this.comments = comments;
		this.date = date;
		this.privacy = privacy;
	}

	public Post(int pnum, int unum, String title, String img, String contents, String tags, int likes, int hits,
			int comments, String date, String privacy) {
		super();
		this.pnum = pnum;
		this.unum = unum;
		this.title = title;
		this.img = img;
		this.contents = contents;
		this.tags = tags;
		this.likes = likes;
		this.hits = hits;
		this.comments = comments;
		this.date = date;
		this.privacy = privacy;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getUnum() {
		return unum;
	}

	public void setUnum(int unum) {
		this.unum = unum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [pnum=" + pnum + ", unum=" + unum + ", title=" + title + ", img=" + img + ", contents=" + contents
				+ ", tags=" + tags + ", likes=" + likes + ", hits=" + hits + ", comments=" + comments + ", date=" + date
				+ ", privacy=" + privacy + "]";
	}

	

}
