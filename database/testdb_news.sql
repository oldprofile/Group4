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
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `training` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `type` int(11) NOT NULL,
  `coach_feedback` bigint(20) DEFAULT NULL,
  `training_feedback` bigint(20) DEFAULT NULL,
  `user_feedback` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fha3d2l0ab2cfjn8aaxjkdfen` (`training`),
  KEY `FK_iooav87b4408r9ums33l9pp0j` (`user`),
  KEY `FK_nf6i204qvi5rst4gqu0bjkhlc` (`coach_feedback`),
  KEY `FK_10do26mbyehl5qcuv1xfrpu9g` (`training_feedback`),
  KEY `FK_plegj8ocoqhunh0aqicrw8y68` (`user_feedback`),
  CONSTRAINT `FK_10do26mbyehl5qcuv1xfrpu9g` FOREIGN KEY (`training_feedback`) REFERENCES `tfeedbacks` (`id`),
  CONSTRAINT `FK_fha3d2l0ab2cfjn8aaxjkdfen` FOREIGN KEY (`training`) REFERENCES `trainings` (`id`),
  CONSTRAINT `FK_iooav87b4408r9ums33l9pp0j` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_nf6i204qvi5rst4gqu0bjkhlc` FOREIGN KEY (`coach_feedback`) REFERENCES `cfeedbacks` (`id`),
  CONSTRAINT `FK_plegj8ocoqhunh0aqicrw8y68` FOREIGN KEY (`user_feedback`) REFERENCES `ufeedbacks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
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
