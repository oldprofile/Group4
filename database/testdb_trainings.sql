-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	5.6.21

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
  `additional` varchar(255) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `audience` varchar(255) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_internal` bit(1) NOT NULL,
  `language` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent` bigint(20) NOT NULL,
  `picture_link` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `rating` double NOT NULL,
  `state` int(11) NOT NULL,
  `category` int(11) DEFAULT NULL,
  `coach` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9e5kuuswyybclwxv8mj9w4vki` (`category`),
  KEY `FK_akyiltw6qfm35ry7jecytagm7` (`coach`),
  CONSTRAINT `FK_9e5kuuswyybclwxv8mj9w4vki` FOREIGN KEY (`category`) REFERENCES `categories` (`id`),
  CONSTRAINT `FK_akyiltw6qfm35ry7jecytagm7` FOREIGN KEY (`coach`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
INSERT INTO `trainings` VALUES (1,NULL,10,NULL,'2015-07-21 14:00:00',NULL,'\0',2,'English',0,NULL,'213',9,3,3,34),(2,NULL,5,NULL,'2015-08-11 13:30:00',NULL,'',1,'JPA vs Hibernate',0,NULL,'243',6,1,2,36),(3,NULL,7,'for beginers','2015-07-15 12:45:00',NULL,'\0',1,'Java for begginers',0,NULL,'243',8,5,2,32),(4,'',12,NULL,'2015-06-09 14:00:00',NULL,'',1,'Angular',0,NULL,'243',6,6,1,31),(5,'',20,NULL,'2015-03-10 11:00:00',NULL,'\0',1,'Front End: JS frameworks',0,NULL,'243',8,5,1,30),(6,NULL,14,NULL,'2015-07-06 12:00:00',NULL,'',1,'Spring',0,NULL,'243',9,5,2,37),(7,'',6,'for beginers','2015-08-09 14:00:00',NULL,'\0',1,'DB',0,NULL,'243',7,4,2,29),(8,NULL,10,NULL,'2015-07-21 14:00:00',NULL,'\0',2,'English',1,NULL,'213',9,5,3,34),(9,NULL,10,NULL,'2015-07-24 14:00:00',NULL,'\0',2,'English',1,NULL,'213',9,6,3,34),(10,NULL,6,'for beginers','2015-07-09 14:00:00',NULL,'\0',1,'DB',7,NULL,'243',7,5,2,28),(11,NULL,12,NULL,'2015-06-09 14:00:00',NULL,'',1,'Angular',4,NULL,'243',6,5,1,27),(12,NULL,10,NULL,'2015-07-28 14:00:00',NULL,'\0',2,'English',1,NULL,'213',9,5,3,34),(13,NULL,6,'for beginers','2015-06-09 12:00:00',NULL,'',1,'DB',7,NULL,'243',7,6,2,26),(14,NULL,14,NULL,'2015-07-06 12:00:00',NULL,'',1,'Spring',6,NULL,'243',7,5,2,25),(15,NULL,4,NULL,'2015-08-01 13:00:00',NULL,'',1,'Vanilla JS',0,NULL,'243',8,2,1,24),(16,NULL,12,NULL,'2015-06-16 14:00:00',NULL,'',1,'Angular',4,NULL,'243',6,6,1,23),(17,NULL,10,NULL,'2015-08-01 14:00:00',NULL,'\0',2,'English',1,NULL,'213',9,2,3,34),(18,NULL,5,NULL,'2015-08-11 13:30:00',NULL,'',1,'JPA vs Hibernate',2,NULL,'243',6,1,2,36),(19,NULL,7,'for beginers','2015-07-15 12:45:00',NULL,'\0',1,'Java for begginers',3,NULL,'243',8,5,2,32),(20,NULL,12,NULL,'2015-06-23 14:00:00',NULL,'',1,'Angular',4,NULL,'243',6,6,1,27),(21,NULL,20,NULL,'2015-03-10 11:00:00',NULL,'\0',1,'Front End: JS frameworks',5,NULL,'243',8,5,1,30),(22,NULL,14,NULL,'2015-07-09 12:00:00',NULL,'',1,'Spring',6,NULL,'243',7,5,2,35),(23,NULL,6,'for beginers','2015-08-09 14:00:00',NULL,'\0',1,'DB',7,NULL,'243',7,4,2,29),(24,NULL,4,NULL,'2015-08-01 13:00:00',NULL,'',1,'Vanilla JS',15,NULL,'243',8,2,1,24);
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

-- Dump completed on 2015-08-03 11:03:27
