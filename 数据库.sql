/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.0.18-nt : Database - exame
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`exame` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `exame`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `kcm` varchar(255) character set utf8 collate utf8_bin default NULL COMMENT '课程名',
  `xf` int(3) default NULL COMMENT '学分',
  `jj` varchar(255) character set utf8 collate utf8_bin default NULL COMMENT '介绍',
  `xz` varchar(255) default NULL COMMENT '性质'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`kcm`,`xf`,`jj`,`xz`) values 
('大数据',4,'大数据是一门新技术','选修'),
('java',6,'java是一门编程语言','必修'),
('数据库',3,'数据库是一门技术','必修');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` varchar(255) default '',
  `name` varchar(255) default NULL,
  `kemu` varchar(255) default NULL,
  `score` int(5) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`id`,`name`,`kemu`,`score`) values 
('1','王菲','大数据',30),
('2','王五','java',44),
('3','孙发','数据库',0),
('2','王五','java',44);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uId` varchar(255) NOT NULL,
  `uName` varchar(255) NOT NULL,
  `uPwd` varchar(255) NOT NULL,
  `uType` varchar(255) NOT NULL,
  `uAge` varchar(255) default NULL,
  `uSex` varchar(255) default NULL,
  `uTel` varchar(255) default NULL,
  `img` varchar(255) default NULL,
  PRIMARY KEY  (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uId`,`uName`,`uPwd`,`uType`,`uAge`,`uSex`,`uTel`,`img`) values 
('1','孙发','1','学生','15','男','234234','src/img/cat4.jpg'),
('123','王菲','2','学生','23','女','123123123','src/img/cat4.jpg'),
('2','王五','2','教师','32','女','151515151','src/img/cat1.jpg'),
('20201','admin','admin','教师','20','男',NULL,'src/img/cat4.jpg'),
('3','李是','3','学生','30','女','23423423','src/img/cat4.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
