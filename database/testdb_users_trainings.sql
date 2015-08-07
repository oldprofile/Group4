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
-- Table structure for table `users_trainings`
--

DROP TABLE IF EXISTS `users_trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_trainings` (
  `listeners` bigint(20) NOT NULL,
  `trainings` bigint(20) NOT NULL,
  KEY `FK_458gxa6a80o2y1x7pbjnyngp2` (`trainings`),
  KEY `FK_gtri2u2nwxgxf11cy9n3feu6f` (`listeners`),
  CONSTRAINT `FK_458gxa6a80o2y1x7pbjnyngp2` FOREIGN KEY (`trainings`) REFERENCES `trainings` (`id`),
  CONSTRAINT `FK_gtri2u2nwxgxf11cy9n3feu6f` FOREIGN KEY (`listeners`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_trainings`
--

LOCK TABLES `users_trainings` WRITE;
/*!40000 ALTER TABLE `users_trainings` DISABLE KEYS */;
INSERT INTO `users_trainings` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(1,8),(2,8),(3,8),(4,8),(5,8),(6,8),(7,8),(8,8),(1,9),(2,9),(3,9),(4,9),(1,12),(2,12),(3,12),(4,12),(9,12),(10,12),(1,17),(2,17),(3,17),(4,17),(9,17),(10,17),(11,17),(12,17),(13,17),(14,2),(15,2),(14,18),(15,18),(16,3),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(16,19),(17,19),(18,19),(19,19),(20,19),(21,19),(22,19),(33,4),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(33,11),(1,11),(2,11),(3,11),(4,11),(5,11),(6,11),(7,11),(8,5),(9,5),(10,5),(11,5),(12,5),(13,5),(14,5),(15,5),(16,5),(17,5),(18,5),(19,5),(20,5),(21,5),(22,5),(1,5),(2,5),(3,5),(4,5),(1,21),(2,21),(3,21),(4,21),(8,21),(9,21),(10,21),(11,21),(12,21),(13,21),(14,21),(15,21),(16,21),(17,21),(18,21),(19,21),(20,21),(21,21),(22,21),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(11,6),(12,6),(5,14),(6,14),(7,14),(8,14),(9,14),(10,14),(11,14),(12,14),(5,22),(6,22),(7,22),(8,22),(9,22),(10,22),(11,22),(12,22),(13,22),(14,22),(33,7),(19,7),(20,7),(21,7),(22,7),(23,7),(33,23),(19,23),(20,23),(21,23),(22,23),(23,23),(19,13),(20,13),(21,13),(22,13),(23,13),(5,15),(6,15),(7,15),(8,15),(5,24),(6,24),(7,24),(8,24);
/*!40000 ALTER TABLE `users_trainings` ENABLE KEYS */;
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
