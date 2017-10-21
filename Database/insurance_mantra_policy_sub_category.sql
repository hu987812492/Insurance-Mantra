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
INSERT INTO `policy_sub_category` VALUES (1,1,'Term Insurance','2016-12-09','subcategory description','features','benefits'),(2,5,'Health plan','2016-12-12','health insurance plans insure you against unexpected medical emergencies and keep your family’s finances stable during such a testing time','features.....health insurance plans insure you against unexpected medical emergencies and keep your family’s finances stable during such a testing time','benefits....health insurance plans insure you against unexpected medical emergencies and keep your family’s finances stable during such a testing time'),(3,8,'Domestic Travel Insurance','2016-12-15','Domestic travel insurance is meant for customers who are travelling within the country. It provides coverage for medical emergencies, permanent disability and death, checked-in lost/stolen baggage, travel delay and personal liability.','InjuriesAccidents or DeathLoss of baggageTerrorist attack','Emergency evacuationFlight cancellationsLoss of passport and international driving licenseDelay in trips');
/*!40000 ALTER TABLE `policy_sub_category` ENABLE KEYS */;
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
