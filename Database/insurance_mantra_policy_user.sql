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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_user`
--

LOCK TABLES `policy_user` WRITE;
/*!40000 ALTER TABLE `policy_user` DISABLE KEYS */;
INSERT INTO `policy_user` VALUES (2,'jtsharma','jyoti','j@gmail.commmmmmm','customer','1991-08-23','51 C Smith St.','Roxburyjh','MA','US',4532699,'2016-12-07','Jyoti Sharma'),(3,'admin','admin','j@j.com','admin','1998-12-25','51 C Smith St.','Roxbury','MA','USA',4532699,'2016-12-14','Jyoti Sharma'),(4,'ewr','wer','rew','customer',NULL,'sdf','sdf','df','df',NULL,'2016-12-14','qwe'),(5,'test','test','234324','customer',NULL,'sdfsf','sdfs','sdfsdf','fds',NULL,'2016-12-14','subhas'),(7,'sks','sks','sks','customer',NULL,'sks','sks','sks','sks.Us',123654,'2016-12-14','sks'),(8,'insurance','BSFS/oQxp+ke0ACg1RI1EQ==','insurance@insurance','customer',NULL,'xyz company','xyz city','state','Us',NULL,'2016-12-15','insurance');
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

-- Dump completed on 2016-12-15  2:00:35
