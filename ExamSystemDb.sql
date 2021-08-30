/*
SQLyog Ultimate v12.09 (32 bit)
MySQL - 5.5.41 : Database - exam_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`exam_system` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `exam_system`;

/*Table structure for table `answers` */

DROP TABLE IF EXISTS `answers`;

CREATE TABLE `answers` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `correct_answer` varchar(255) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `exam_id` (`exam_id`),
  KEY `question` (`question`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`),
  CONSTRAINT `answers_ibfk_2` FOREIGN KEY (`question`) REFERENCES `questions` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `answers` */

/*Table structure for table `courses` */

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `course_name` varchar(75) NOT NULL,
  `total_marks` int(11) NOT NULL,
  `time` varchar(45) NOT NULL,
  PRIMARY KEY (`course_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `courses` */

insert  into `courses`(`course_name`,`total_marks`,`time`) values ('CIT104-Introduction to Computer',50,'1');

/*Table structure for table `exams` */

DROP TABLE IF EXISTS `exams`;

CREATE TABLE `exams` (
  `exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `std_id` varchar(45) NOT NULL,
  `course_name` varchar(45) NOT NULL,
  `total_marks` varchar(45) NOT NULL,
  `obt_marks` varchar(45) DEFAULT NULL,
  `date` varchar(45) NOT NULL,
  `start_time` varchar(45) NOT NULL,
  `end_time` varchar(45) DEFAULT NULL,
  `exam_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `course_name` (`course_name`),
  CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`course_name`) REFERENCES `courses` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `exams` */

insert  into `exams`(`exam_id`,`std_id`,`course_name`,`total_marks`,`obt_marks`,`date`,`start_time`,`end_time`,`exam_time`,`status`) values (2,'3','CIT104-Introduction to Computer','50','0','22-05-2021','07:43:24.859','07:43:32','1','F');

/*Table structure for table `questions` */

DROP TABLE IF EXISTS `questions`;

CREATE TABLE `questions` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) NOT NULL,
  `question` varchar(255) NOT NULL,
  `opt1` varchar(225) NOT NULL,
  `opt2` varchar(225) NOT NULL,
  `opt3` varchar(225) NOT NULL,
  `opt4` varchar(225) NOT NULL,
  `correct` varchar(225) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `course_name` (`course_name`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`course_name`) REFERENCES `courses` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `questions` */

insert  into `questions`(`question_id`,`course_name`,`question`,`opt1`,`opt2`,`opt3`,`opt4`,`correct`) values (1,'CIT104-Introduction to Computer','What is a Computer ?','An Elecytronic Device','A Computing machine','An Electronic Super Machine','An Electronic Device','An Electronic Device');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `name` varchar(25) NOT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test` */

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_role_id` char(50) NOT NULL,
  `user_role` varchar(70) DEFAULT NULL,
  `user_role_desc` text,
  `entry_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_role_id`,`user_role`,`user_role_desc`,`entry_date`) values ('UR01','Admin','Administrator','2021-03-03 10:32:59'),('UR02','Instructor','Lecturer/Instructor','2021-03-03 10:32:59'),('UR03','Student','Student\r\n','2021-03-03 10:34:20');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` char(50) DEFAULT NULL,
  `contact_no` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `login_status` int(11) DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_type` (`user_type`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_type`) REFERENCES `user_roles` (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`first_name`,`last_name`,`user_name`,`email`,`password`,`user_type`,`contact_no`,`city`,`address`,`login_status`,`entry_date`) values (1,'Precious','Ekwere','Preshae','tompreciousekwere@gmail.com','password','UR01','08102578666','Lagos','No 3, Fassy Adesina street',1,'2021-03-03 10:36:09'),(2,'Timothy','John','jontimothy','jontimothy@gmail.com','password','UR02','09125006919','Lagos','No 8, Block B, Satellite Town.',1,'2021-03-03 10:39:34'),(3,'Kane','Geoffery','kgeoffery','kgeoffery@gmail.com','password','UR03','08135467898','Lagos','No 9, Wewe Street, Orile Iganmu',2,'2021-03-03 10:40:52');

/*Table structure for table `users_bk` */

DROP TABLE IF EXISTS `users_bk`;

CREATE TABLE `users_bk` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  `contact_no` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `login_status` int(11) DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';

/*Data for the table `users_bk` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
