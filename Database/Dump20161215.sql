CREATE DATABASE  IF NOT EXISTS `insurance_mantra` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `insurance_mantra`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: insurance_mantra
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.16-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `policy_brand`
--

DROP TABLE IF EXISTS `policy_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_brand` (
  `brand_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(50) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `brand_email` varchar(100) DEFAULT NULL,
  `password` varchar(2000) NOT NULL,
  `brand_address` varchar(200) DEFAULT NULL,
  `brand_city` varchar(50) DEFAULT NULL,
  `brand_state` varchar(20) DEFAULT NULL,
  `brand_country` varchar(20) DEFAULT NULL,
  `brand_phone` int(10) DEFAULT NULL,
  `brand_status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_brand`
--

LOCK TABLES `policy_brand` WRITE;
/*!40000 ALTER TABLE `policy_brand` DISABLE KEYS */;
INSERT INTO `policy_brand` VALUES (1,'Bajaj Allianz','2016-12-09','bajaj@gmail.com','bajaj','51 C smith st.','boston','MA','USA',123654,'Active'),(2,'ICICI','2016-12-09','icici@gmail.com','icici','51 C smith st.','Bostonnnnn','MA','USA',123654,'Active'),(3,'testbrand','2016-12-14','ytytuyu','test','ytgygty','ytytr','dgfbdgd','hgfhgfv',5663,'Pending'),(4,'Bharti AXA','2016-12-14','bharti@gmail.com','bharti','xyz st.','boston','MA','USA',123654,'Pending'),(5,'Kotak','2016-12-14','kotak@kotak.com','kotak','kotak','bangaluru','karnataka','India',12654,'Active'),(8,'SBI','2016-12-15','sbi@sbi.gmail.com','usmfTtwevb8=','sbi','sbi','sbi','sbi',73349019,'Active');
/*!40000 ALTER TABLE `policy_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_category`
--

DROP TABLE IF EXISTS `policy_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `category_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_category`
--

LOCK TABLES `policy_category` WRITE;
/*!40000 ALTER TABLE `policy_category` DISABLE KEYS */;
INSERT INTO `policy_category` VALUES (1,'Life Insurance',NULL,'Life Insurance Plan'),(5,'Health Insurance',NULL,'Health insurance desc fetaures');
/*!40000 ALTER TABLE `policy_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_order_history`
--

DROP TABLE IF EXISTS `policy_order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_order_history` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `plan_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `brand_id` int(10) unsigned NOT NULL,
  `requester_name` varchar(50) NOT NULL,
  `requester_email` varchar(100) DEFAULT NULL,
  `requester_phone` int(10) NOT NULL,
  `requester_dob` date DEFAULT NULL,
  `requester_income` varchar(20) DEFAULT NULL,
  `order_status` varchar(10) DEFAULT NULL,
  `order_creation_date` date DEFAULT NULL,
  `order_completion_date` date DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_policy_plan_plan_id` (`plan_id`),
  KEY `FK_policy_user_user_id` (`user_id`),
  KEY `FK_policy_brand_onhistory_brand_id` (`brand_id`),
  CONSTRAINT `FK_policy_brand_onhistory_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `policy_brand` (`brand_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_policy_plan_plan_id` FOREIGN KEY (`plan_id`) REFERENCES `policy_plan` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_policy_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `policy_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_order_history`
--

LOCK TABLES `policy_order_history` WRITE;
/*!40000 ALTER TABLE `policy_order_history` DISABLE KEYS */;
INSERT INTO `policy_order_history` VALUES (3,2,2,1,'Jyoti sharma','J@s.com',123654,'1991-08-23','10 lakhs','Completed','2016-12-10','2016-12-13'),(4,2,2,2,'Sks','sks@gmail.com',123654,'1988-12-14','100000','Completed','2016-12-14','2016-12-15');
/*!40000 ALTER TABLE `policy_order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_plan`
--

DROP TABLE IF EXISTS `policy_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_plan` (
  `plan_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sub_category_id` int(10) unsigned NOT NULL,
  `brand_id` int(10) unsigned NOT NULL,
  `plan_name` varchar(50) NOT NULL,
  `min_entry_age` char(2) NOT NULL,
  `max_entry_age` char(2) NOT NULL,
  `plan_maturity` char(2) DEFAULT NULL,
  `premium_amount` varchar(20) DEFAULT NULL,
  `sum_assured` varchar(20) DEFAULT NULL,
  `plan_desc` varchar(1000) DEFAULT NULL,
  `plan_features` varchar(1000) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `FK_policy_brand_onpolicyplan_brand_id` (`brand_id`),
  KEY `FK_policy_sub_category_sub_category_id` (`sub_category_id`),
  CONSTRAINT `FK_policy_brand_onpolicyplan_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `policy_brand` (`brand_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_policy_sub_category_sub_category_id` FOREIGN KEY (`sub_category_id`) REFERENCES `policy_sub_category` (`sub_category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_plan`
--

LOCK TABLES `policy_plan` WRITE;
/*!40000 ALTER TABLE `policy_plan` DISABLE KEYS */;
INSERT INTO `policy_plan` VALUES (2,2,2,'Max Bupa Health Insurance Plans','18','60','70','15k','2.5 lacs','2 adults+2 children (i.e. 4 members of the family)','Complete transparency No cheating','2016-12-13'),(3,2,1,'Bajaj Allianz Plan','18','60','70','15k','2.5 lacs','Bajaj Allianz iSecure Term Insurance Plan desc','xyzzzz','2016-12-13');
/*!40000 ALTER TABLE `policy_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_sub_category`
--

DROP TABLE IF EXISTS `policy_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_sub_category` (
  `sub_category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `sub_category_name` varchar(50) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `sub_category_desc` varchar(1000) DEFAULT NULL,
  `sub_category_features` varchar(1000) DEFAULT NULL,
  `sub_category_benefits` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`sub_category_id`),
  KEY `FK_policy_category_category_id` (`category_id`),
  CONSTRAINT `FK_policy_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `policy_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_sub_category`
--

LOCK TABLES `policy_sub_category` WRITE;
/*!40000 ALTER TABLE `policy_sub_category` DISABLE KEYS */;
INSERT INTO `policy_sub_category` VALUES (2,5,'Health plan','2016-12-12','health insurance plans insure you against unexpected medical emergencies and keep your family’s finances stable during such a testing time','features.....health insurance plans insure you against unexpected medical emergencies and keep your family’s finances stable during such a testing time','benefits....health insurance plans insure you against unexpected medical emergencies and keep your family’s finances stable during such a testing time');
/*!40000 ALTER TABLE `policy_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_user`
--

DROP TABLE IF EXISTS `policy_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(2000) NOT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_role` varchar(15) DEFAULT NULL,
  `user_dob` date DEFAULT NULL,
  `user_address` varchar(200) DEFAULT NULL,
  `user_city` varchar(50) DEFAULT NULL,
  `user_state` varchar(20) DEFAULT NULL,
  `user_country` varchar(20) DEFAULT NULL,
  `user_phone` int(10) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_user`
--

LOCK TABLES `policy_user` WRITE;
/*!40000 ALTER TABLE `policy_user` DISABLE KEYS */;
INSERT INTO `policy_user` VALUES (2,'jtsharma','jyoti','j@gmail.commmmmmm','customer','1991-08-23','51 C Smith St.','Roxburyjh','MA','US',4532699,'2016-12-07','Jyoti Sharma'),(3,'admin','admin','j@j.com','admin','1998-12-25','51 C Smith St.','Roxbury','MA','USA',4532699,'2016-12-14','Jyoti Sharma'),(4,'ewr','wer','rew','customer',NULL,'sdf','sdf','df','df',NULL,'2016-12-14','qwe'),(5,'test','test','234324','customer',NULL,'sdfsf','sdfs','sdfsdf','fds',NULL,'2016-12-14','subhas'),(7,'sks','sks','sks','customer',NULL,'sks','sks','sks','sks.Us',123654,'2016-12-14','sks'),(8,'insurance','BSFS/oQxp+ke0ACg1RI1EQ==','insurance@insurance','customer',NULL,'xyz company','xyz city','state','Us',NULL,'2016-12-15','insurance'),(9,'mainadmin','bkuykQs/HLM3kYyjM1HQ3w==',NULL,'admin',NULL,'hhjgjgjh','ytuytyu','uytuyt','uyuy',NULL,'2016-12-15','mainadmin');
/*!40000 ALTER TABLE `policy_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-15  7:42:35
