/*
SQLyog Community Edition- MySQL GUI v8.2 
MySQL - 5.1.48-community : Database - sanstha
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sanstha` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sanstha`;

/*Table structure for table `events` */

DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `event_id` int(10) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(20) NOT NULL,
  `society_id` int(10) NOT NULL,
  `event_coordinator` varchar(50) NOT NULL,
  `event_location` varchar(100) NOT NULL,
  `event_start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `event_end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `event_created_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `events` */

insert  into `events`(`event_id`,`event_name`,`society_id`,`event_coordinator`,`event_location`,`event_start_date`,`event_end_date`,`event_created_date`) values (1,'Diwali Mela',1,'Ravi Yadav','Ramlila Maidan','2015-09-23 11:11:00','2015-09-25 11:11:00','2015-09-23 11:11:42');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(10) NOT NULL AUTO_INCREMENT,
  `society_id` int(10) NOT NULL,
  `depositor_name` varchar(150) NOT NULL,
  `bank_code` varchar(10) NOT NULL,
  `ifsc_code` varchar(50) NOT NULL,
  `type` int(1) NOT NULL,
  `payment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`society_id`,`depositor_name`,`bank_code`,`ifsc_code`,`type`,`payment_date`,`amount`) values (1,1,'Nikhil Gupta','1','ICIC01212',1,'2015-09-02 12:40:56',1000),(2,2,'Ravi Yadav','2','PNB023',1,'2015-09-02 12:42:51',3333);

/*Table structure for table `society` */

DROP TABLE IF EXISTS `society`;

CREATE TABLE `society` (
  `society_id` int(10) NOT NULL AUTO_INCREMENT,
  `society_name` varchar(150) NOT NULL,
  `society_web_address` varchar(150) NOT NULL,
  `user_id` int(10) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`society_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `society` */

insert  into `society`(`society_id`,`society_name`,`society_web_address`,`user_id`,`created_date`) values (1,'My Welfare Society','MyWelfareSociety',2,'2015-08-20 00:35:15'),(2,'Sanskar Welfare Society','SanskarWelfareSociety',3,'2015-08-20 00:38:22'),(3,'Saumya Samaj','SaumyaSamaj',4,'2015-08-20 00:41:18'),(4,'Naya Jeevan Sanstha','NayaJeevanSanstha',5,'2015-08-20 01:02:12'),(5,'Nav Jeevan Sanstha','NavJeevanSanstha',6,'2015-08-20 13:07:59'),(6,'Meri Society','MeriSociety',7,'2015-09-02 12:44:56');

/*Table structure for table `society_request` */

DROP TABLE IF EXISTS `society_request`;

CREATE TABLE `society_request` (
  `request_id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `email_id` varchar(100) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `society_name` varchar(150) NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `society_request` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `password` varchar(50) NOT NULL,
  `role_id` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`email_id`,`status`,`password`,`role_id`,`created_date`) values (1,'nikhil.gupta939@gmail.com',1,'12345678',1,'2015-08-19 23:34:27'),(2,'nikhil@gmail.com',1,'12345678',2,'2015-08-20 00:34:34'),(3,'pankaj@gmail.com',1,'12345678',3,'2015-09-23 11:15:15'),(4,'bhaskar@yahoo.com',1,'12345678',2,'2015-08-20 00:41:18'),(5,'ekta@gmail.com',1,'12345678',2,'2015-08-20 01:02:11'),(6,'ektaG@gmail.com',1,'12345678',2,'2015-08-20 13:07:59'),(7,'ravi@gmail.com',1,'12345678',2,'2015-09-02 12:44:56');

/*Table structure for table `user_details` */

DROP TABLE IF EXISTS `user_details`;

CREATE TABLE `user_details` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `society_id` int(10) NOT NULL DEFAULT '0',
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `fathers_first_name` varchar(20) DEFAULT NULL,
  `fathers_last_name` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `local_building_number` varchar(20) DEFAULT NULL,
  `local_location` varchar(20) DEFAULT NULL,
  `local_city` varchar(20) DEFAULT NULL,
  `local_state` varchar(20) DEFAULT NULL,
  `local_pincode` varchar(6) DEFAULT NULL,
  `local_phone_number` varchar(10) DEFAULT NULL,
  `office_building_number` varchar(20) DEFAULT NULL,
  `office_location` varchar(20) DEFAULT NULL,
  `office_city` varchar(20) DEFAULT NULL,
  `office_state` varchar(20) DEFAULT NULL,
  `office_pincode` varchar(6) DEFAULT NULL,
  `office_phone_number` varchar(10) DEFAULT NULL,
  `permanent_building_number` varchar(20) DEFAULT NULL,
  `permanent_location` varchar(20) DEFAULT NULL,
  `permanent_city` varchar(20) DEFAULT NULL,
  `permanent_state` varchar(20) DEFAULT NULL,
  `permanent_pincode` varchar(6) DEFAULT NULL,
  `permanent_phone_number` varchar(10) DEFAULT NULL,
  `about` varchar(500) DEFAULT NULL,
  `hobbies` varchar(200) DEFAULT NULL,
  `profile_picture_name` varchar(50) NOT NULL DEFAULT 'default.png',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user_details` */

insert  into `user_details`(`user_id`,`society_id`,`first_name`,`last_name`,`fathers_first_name`,`fathers_last_name`,`date_of_birth`,`local_building_number`,`local_location`,`local_city`,`local_state`,`local_pincode`,`local_phone_number`,`office_building_number`,`office_location`,`office_city`,`office_state`,`office_pincode`,`office_phone_number`,`permanent_building_number`,`permanent_location`,`permanent_city`,`permanent_state`,`permanent_pincode`,`permanent_phone_number`,`about`,`hobbies`,`profile_picture_name`,`updated_date`) values (1,0,'Nikhil','Gupta',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'default.png','2015-08-19 23:34:54'),(2,1,'Nikhil Kumar','Gupta','','',NULL,'','','','','','','','','','','','','','','','','','','I love to play Guitar.',NULL,'default.png','2015-08-20 00:35:18'),(3,2,'Pankaj','Kushwaha',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9988776655',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'default.png','2015-08-20 00:38:22'),(4,3,'Bhaskar','Gupta',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1122334455',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'default.png','2015-08-20 00:41:18'),(5,4,'Ekta','Gupta',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9711242804',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'default.png','2015-08-20 01:02:12'),(6,5,'Ekta','Gupta',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1122334455',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'default.png','2015-08-20 13:07:59'),(7,6,'Manoj','Rajan','','',NULL,'','','','','','9988776655','','','','','','','','','','','','',NULL,NULL,'default.png','2015-09-02 12:44:56');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
