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

# article 테이블에 memberId 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER `body`;

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
`title` = '제목1',
`body` = '내용1', 
memberId = 1;

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
`title` = '제목2',
`body` = '내용2', 
memberId = 2;

# reply table 생성
CREATE TABLE `reply` (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
`body` VARCHAR(300) NOT NULL,
articleId INT(10) UNSIGNED NOT NULL,
memberId INT(10) UNSIGNED NOT NULL
);

INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글1',
articleId = 1,
memberId = 1;

INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글2',
articleId = 1,
memberId = 2;

INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글1',
articleId = 2,
memberId = 1;

INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글2',
articleId = 2,
memberId = 2;

#board table 생성
CREATE TABLE `board` (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
`name` CHAR(20) NOT NULL,
`code` CHAR(20) NOT NULL
);

INSERT INTO `board`
SET regDate = NOW(),
`name` = '공지',
`code` = 'notice';

INSERT INTO `board`
SET regDate = NOW(),
`name` = '자유',
`code` = 'free';

# article 테이블에 boardId 추가
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER id;

UPDATE article
SET boardId = 1
WHERE id = 1;

UPDATE article
SET boardId = 2
WHERE id = 2;
