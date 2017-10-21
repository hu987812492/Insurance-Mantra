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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_plan`
--

LOCK TABLES `policy_plan` WRITE;
/*!40000 ALTER TABLE `policy_plan` DISABLE KEYS */;
INSERT INTO `policy_plan` VALUES (1,1,1,'Bajaj Allianz iSecure Term Insurance Plan','18','60','70','15k','2.5 lacs','Bajaj Allianz iSecure Term Insurance Plan desc','xyzzzz','2016-12-09'),(2,2,2,'Max Bupa Health Insurance Plans','18','60','70','15k','2.5 lacs','2 adults+2 children (i.e. 4 members of the family)','Complete transparency No cheating','2016-12-13'),(3,2,1,'Bajaj Allianz Plan','18','60','70','15k','2.5 lacs','Bajaj Allianz iSecure Term Insurance Plan desc','xyzzzz','2016-12-13'),(4,1,1,'Healthy','20','65','80','10l','25l','yuetueiu iuwtriutey','uietiute i iuyieuyriu','2016-12-13'),(5,1,2,'test','23','56','20','10000','1000000','test for data','test for data','2016-12-13'),(6,2,2,'Icici health plan','15','60','75','100000','25lacs','uyeiru uytiubyciy','uytuy iuo9ncbm ckuehb','2016-12-13'),(7,2,2,'dsfs','23','23','23','23','sdfs','sdfs','ds','2016-12-15'),(8,3,5,'Kotak Travel Insurance','15','70','75','20 lacs','35 lacs','Loss of passport and international driving licenseDelay in trips','Emergency evacuationFlight cancellationsLoss of passport and international driving licenseDelay in trips','2016-12-15');
/*!40000 ALTER TABLE `policy_plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-15  2:00:34
