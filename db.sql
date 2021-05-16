# DB 생성
DROP DATABASE springbootblog;
CREATE DATABASE springbootblog;
USE springbootblog;

# article table 생성
CREATE TABLE `article` (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
`title` CHAR(30) NOT NULL,
`body` VARCHAR(300) NOT NULL
);

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
`title` = '제목1',
`body` = '내용1';

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
`title` = '제목2',
`body` = '내용2';

# member table 생성
CREATE TABLE `member` (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
`loginId` CHAR(20) NOT NULL,
`loginPw` VARCHAR(30) NOT NULL,
`name` CHAR(20) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`loginId` = 'admin',
`loginPw` = 'admin', 
`name` = 'admin';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`loginId` = 'user1',
`loginPw` = 'user1',
`name` = 'user1';