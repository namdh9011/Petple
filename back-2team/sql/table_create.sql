-- member tabel 생성
-- String email, String password, String nickname, String phone, String interest
create table IF NOT EXISTS member(
    unum int primary key auto_increment, 
    name varchar(100),
    email varchar(30) not null, 
    password varchar(100) not null, 
    nickname varchar(20) not null,
    phone varchar(20),
    interest varchar(50),
    role varchar(100),
    userprofile VARCHAR(50) NULL  DEFAULT  'http://52.78.132.70:8082/static/img/default.png',
	authorization VARCHAR(10) NULL DEFAULT 'N',
    PRIMARY KEY (`unum`)
);

-- 게시물 tabel 생성
-- int unum, String title, String img, String contents, int likes, int hits, int comments, String date, String privacy
-- unum,title,img,contents,likes,hits,comments,date,privacy
create table post(
	pnum int primary key auto_increment,
    unum int not null,
    title varchar(30),
    img varchar(50)  DEFAULT  'http://52.78.132.70:8082/static/img/default.png',
    contents varchar(1000),
	tags varchar(100),
    likes int default 0, 	-- 좋아요수
    hits int default 0, 	-- 조회수
    comments int default 0,	-- 댓글수? 댓글 테이블의 숫자를 세야하는 건가?
    date datetime,
    privacy varchar(30) default 'all', -- 공개범위 
	constraint fk_unum foreign key(unum) references member(unum)
);

-- 펫 tabel 생성
create table pet(
	`petnum` INT(11) NOT NULL AUTO_INCREMENT,
  `unum` INT(11) NOT NULL,
  `name` VARCHAR(30) NULL DEFAULT NULL,
  `animal` VARCHAR(30) NULL DEFAULT NULL,
  `gender` VARCHAR(30) NULL DEFAULT NULL,
  `breed` VARCHAR(30) NULL DEFAULT NULL,
  `age` INT(11) NULL DEFAULT NULL,
  `firstday` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `img` VARCHAR(50) NULL DEFAULT 'http://52.78.132.70:8082/static/img/default.png',
  `intro` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`petnum`),
  CONSTRAINT `fk_pet_unum`
    FOREIGN KEY (`unum`)
    REFERENCES `member` (`unum`)
);


-- 해시태그 tabel
create table tag(
	tnum int  primary key auto_increment,
	word varchar(50)
);

-- 게시물_해시태그_테이블

create table post_tag(
	no int primary key auto_increment,
	postnum int,
    tagnum int,
    constraint fk_post_tag_pnum foreign key(postnum) references post(pnum),
    constraint fk_post_tag_tnum foreign key(tagnum) references tag(tnum)
);

-- 팔로워 팔로잉 table
create table follow(
    folnum int primary key auto_increment, 
    unum int not null,
    follower varchar(1000) not null, 
    following varchar(1000) not null,
    constraint fk_follor_member foreign key(unum) references member(unum)
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `notice` (
  `num` INT(11) NOT NULL AUTO_INCREMENT,
  `unum_from` INT(11) NULL DEFAULT NULL,
  `unum_to` INT(11) NULL DEFAULT NULL,
  `action` VARCHAR(100) NULL DEFAULT NULL,
  `pnum` INT(11) NULL DEFAULT '0',
  `create_at` DATETIME NULL DEFAULT NULL,
  `read_at` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`num`),
  CONSTRAINT `fk_pnum_alarm`
    FOREIGN KEY (`pnum`)
    REFERENCES `post` (`pnum`),
  CONSTRAINT `fk_unum_from_alarm`
    FOREIGN KEY (`unum_from`)
    REFERENCES `member` (`unum`),
  CONSTRAINT `fk_unum_to_alarm`
    FOREIGN KEY (`unum_to`)
    REFERENCES `member` (`unum`)
);

-- 좋아요 테이블
create table likepost(
	lpnum int primary key auto_increment,
    unum  varchar(100) null,
    pnum varchar(100) not null, 
    likepost varchar(10)
);

-- oauth 를 쓰기 위한 user 테이블
create table user(
    id int primary key auto_increment,
    name varchar(100) not null, 
    email varchar(100) not null, 
    picture varchar(1000) not null,
    role varchar(100),
    created_date varchar(100),
    modified_date varchar(100)
);

-- oauth 를 쓰기 위한 SPRING_SESSION 테이블
CREATE TABLE SPRING_SESSION (
	PRIMARY_ID VARCHAR(100),
    SESSION_ID VARCHAR(100),
    CREATION_TIME VARCHAR(100) NOT NULL,
    LAST_ACCESS_TIME VARCHAR(100) NOT NULL,
    MAX_INACTIVE_INTERVAL VARCHAR(100) NOT NULL,
    EXPIRY_TIME VARCHAR(100) NOT NULL,
    PRINCIPAL_NAME VARCHAR(100)
) ENGINE=InnoDB;

CREATE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (LAST_ACCESS_TIME);

-- oauth 를 쓰기 위한 SPRING_SESSION_ATTRIBUTES 테이블
CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID VARCHAR(100),
    SESSION_ID VARCHAR(100),
    ATTRIBUTE_NAME VARCHAR(500),
    ATTRIBUTE_BYTES BLOB
) ENGINE=InnoDB;

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1 ON SPRING_SESSION_ATTRIBUTES (SESSION_ID);