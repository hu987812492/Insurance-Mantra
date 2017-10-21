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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_brand`
--

LOCK TABLES `policy_brand` WRITE;
/*!40000 ALTER TABLE `policy_brand` DISABLE KEYS */;
INSERT INTO `policy_brand` VALUES (1,'Bajaj Allianz','2016-12-09','bajaj@gmail.com','bajaj','51 C smith st.','boston','MA','USA',123654,'Active'),(2,'ICICI','2016-12-09','icici@gmail.com','icici','51 C smith st.','Bostonnnnn','MA','USA',123654,'Active'),(3,'testbrand','2016-12-14','ytytuyu','test','ytgygty','ytytr','dgfbdgd','hgfhgfv',5663,'Pending'),(4,'Bharti AXA','2016-12-14','bharti@gmail.com','bharti','xyz st.','boston','MA','USA',123654,'Pending'),(5,'Kotak','2016-12-14','kotak@kotak.com','kotak','kotak','bangaluru','karnataka','India',12654,'Active');
/*!40000 ALTER TABLE `policy_brand` ENABLE KEYS */;
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
