CREATE DATABASE  IF NOT EXISTS `testdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `trainings`
--

DROP TABLE IF EXISTS `trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `date_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_internal` bit(1) NOT NULL,
  `language` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent` bigint(20) NOT NULL,
  `picture_link` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `state` int(11) NOT NULL,
  `category` int(11) DEFAULT NULL,
  `coach` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9e5kuuswyybclwxv8mj9w4vki` (`category`),
  KEY `FK_akyiltw6qfm35ry7jecytagm7` (`coach`),
  CONSTRAINT `FK_akyiltw6qfm35ry7jecytagm7` FOREIGN KEY (`coach`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_9e5kuuswyybclwxv8mj9w4vki` FOREIGN KEY (`category`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
INSERT INTO `trainings` VALUES (1,12,'2001-01-01 01:01:00','qwer','',1,'Front end',0,NULL,'243',1,1,1),(2,14,'2002-02-02 02:02:00','asdf','',1,'Java',0,NULL,'243',2,2,2),(3,16,'2003-03-03 03:03:00','zxcv','',1,'angular',0,NULL,'243',3,1,1);
/*!40000 ALTER TABLE `trainings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-15 19:27:44
