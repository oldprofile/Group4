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
-- Table structure for table `tfeedbacks`
--

DROP TABLE IF EXISTS `tfeedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfeedbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clear` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `effective` int(11) NOT NULL,
  `interesting` varchar(255) DEFAULT NULL,
  `new_material` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `recommendation` varchar(255) DEFAULT NULL,
  `feedbacker` bigint(20) DEFAULT NULL,
  `training` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gm0p229v5hjgcfli2qb20w7bh` (`feedbacker`),
  KEY `FK_g1t3lvlw8dag8qdg6syphfoc5` (`training`),
  CONSTRAINT `FK_g1t3lvlw8dag8qdg6syphfoc5` FOREIGN KEY (`training`) REFERENCES `trainings` (`id`),
  CONSTRAINT `FK_gm0p229v5hjgcfli2qb20w7bh` FOREIGN KEY (`feedbacker`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfeedbacks`
--

LOCK TABLES `tfeedbacks` WRITE;
/*!40000 ALTER TABLE `tfeedbacks` DISABLE KEYS */;
INSERT INTO `tfeedbacks` VALUES (1,'yes','2012-09-20 15:00:00',1,'yes','excelent','sdfgh','sdfghjk',1,1),(2,'yes','2015-10-20 14:00:00',3,'no','good','asdfg','sdfgh',2,2),(3,'no','2030-08-20 15:00:00',5,'yes','bad','dfdrgt','qwert',3,3);
/*!40000 ALTER TABLE `tfeedbacks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-15 19:27:45
