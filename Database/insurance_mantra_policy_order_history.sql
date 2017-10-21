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
INSERT INTO `policy_order_history` VALUES (1,1,2,1,'Subhash kumar sharma','subhash@s.com',123654,'1989-01-25','10 lakhs','Completed','2016-12-10','2016-12-13'),(2,1,2,1,'test','test',89898999,'2016-12-03','384734','Completed',NULL,'2016-12-13'),(3,2,2,1,'Jyoti sharma','J@s.com',123654,'1991-08-23','10 lakhs','Completed','2016-12-10','2016-12-13'),(4,2,2,2,'Sks','sks@gmail.com',123654,'1988-12-14','100000','Completed','2016-12-14','2016-12-15'),(5,6,5,2,'testOrder','test@gmail',123654,'1988-12-25','10258','created','2016-12-14',NULL),(6,4,5,1,'subhash','s@s',459856,'2011-12-04','10lacs','created','2016-12-14',NULL),(7,1,4,1,'jt','jt@js',45932,'1991-08-23','10 lacs','created','2016-12-14',NULL),(8,1,4,1,'subhJyo','js@js',46632,'1995-12-23','20lacs','created','2016-12-14',NULL),(9,4,7,1,'neu','neu@neu',456321,'1888-12-25','260000','created','2016-12-14',NULL);
/*!40000 ALTER TABLE `policy_order_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-15  2:00:35
