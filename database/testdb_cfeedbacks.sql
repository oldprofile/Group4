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
-- Table structure for table `cfeedbacks`
--

DROP TABLE IF EXISTS `cfeedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfeedbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `asking_questions` bit(1) NOT NULL,
  `creativity` bit(1) NOT NULL,
  `date` datetime NOT NULL,
  `erudition` bit(1) NOT NULL,
  `explain_hardness` bit(1) NOT NULL,
  `explain_how_to_use_new` bit(1) NOT NULL,
  `highlight_main` bit(1) NOT NULL,
  `how_enounce_material` bit(1) NOT NULL,
  `interesting` bit(1) NOT NULL,
  `kindness` bit(1) NOT NULL,
  `other` varchar(255) DEFAULT NULL,
  `patience` bit(1) NOT NULL,
  `style_of_teaching` bit(1) NOT NULL,
  `coach` bigint(20) DEFAULT NULL,
  `feedbacker` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `news` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g7v6cdl04ysmkg4uibdty3vxv` (`coach`),
  KEY `FK_4u1299cniaskugnbk8nbdfwww` (`feedbacker`),
  KEY `FK_4yf7ciyx020ey8s8dbert0j12` (`news`),
  CONSTRAINT `FK_4u1299cniaskugnbk8nbdfwww` FOREIGN KEY (`feedbacker`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_4yf7ciyx020ey8s8dbert0j12` FOREIGN KEY (`news`) REFERENCES `news` (`id`),
  CONSTRAINT `FK_g7v6cdl04ysmkg4uibdty3vxv` FOREIGN KEY (`coach`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cfeedbacks`
--

LOCK TABLES `cfeedbacks` WRITE;
/*!40000 ALTER TABLE `cfeedbacks` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfeedbacks` ENABLE KEYS */;
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
