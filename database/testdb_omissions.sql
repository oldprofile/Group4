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
-- Table structure for table `omissions`
--

DROP TABLE IF EXISTS `omissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `omissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_omission` bit(1) NOT NULL,
  `training` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n3lyrlriwufed6qf7n87yupas` (`training`),
  KEY `FK_cfp63addvdploawh5vakgasnh` (`user`),
  CONSTRAINT `FK_cfp63addvdploawh5vakgasnh` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_n3lyrlriwufed6qf7n87yupas` FOREIGN KEY (`training`) REFERENCES `trainings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `omissions`
--

LOCK TABLES `omissions` WRITE;
/*!40000 ALTER TABLE `omissions` DISABLE KEYS */;
INSERT INTO `omissions` VALUES (1,'\0',8,1),(2,'\0',8,2),(3,'\0',8,3),(4,'',8,4),(5,'\0',8,5),(6,'\0',8,6),(7,'\0',8,7),(8,'\0',8,8),(9,'\0',11,33),(10,'\0',11,1),(11,'\0',11,2),(12,'\0',11,3),(13,'\0',11,4),(14,'\0',11,5),(15,'',11,6),(16,'',11,7),(17,'\0',12,1),(18,'\0',12,2),(19,'\0',12,3),(20,'\0',12,4),(21,'\0',12,9),(22,'\0',12,10),(23,'\0',13,19),(24,'',13,20),(25,'',13,21),(26,'',13,22),(27,'\0',13,23),(28,'\0',14,5),(29,'\0',14,6),(30,'\0',14,7),(31,'\0',14,8),(32,'\0',14,9),(33,'',14,10),(34,'\0',14,11),(35,'',14,12),(36,'\0',15,5),(37,'\0',15,6),(38,'',15,7),(39,'\0',15,8),(40,'\0',19,16),(41,'\0',19,17),(42,'\0',19,18),(43,'\0',19,19),(44,'\0',19,20),(45,'\0',19,21),(46,'\0',19,22),(47,'\0',22,5),(48,'',22,6),(49,'\0',22,7),(50,'\0',22,8),(51,'\0',22,9),(52,'',22,10),(53,'\0',22,11),(54,'\0',22,12),(55,'\0',22,13),(56,'',22,14);
/*!40000 ALTER TABLE `omissions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-03 11:03:28
