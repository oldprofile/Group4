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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number_phone` varchar(255) DEFAULT NULL,
  `password` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'mrartem6695@gmail.com','1','Юдовин Артём','',1),(2,'daria@gmail.com','2','Игнатович Млада','',2),(3,'so.daria.96@gmail.com','3','Бусел Дарья','+375296402157',3),(4,'daria.96@gmail.com','4','Досов Антон','',4),(5,'sodaria@gmail.com','5','Шупленков Клим',NULL,5),(6,'daria97@gmail.com','6','Анейчик Анна',NULL,6),(7,'daria.94@mail.ru','7','Ромашко Виктор',NULL,7),(8,'alice.12@mail.ru','8','Хворостовский Алексей',NULL,8),(9,'alice.1218@mail.ru','9','Белый Антон',NULL,9),(10,'artem6695@gmail.com','10','Трубач Геннадий',NULL,10),(11,'mrartem6695@mail.ru','11','Ярошевич Яна',NULL,11),(12,'mrartem@mail.ru','12','Щавровски Святослав',NULL,12),(13,'mrartem95@mail.ru','13','Никифоренко Антон',NULL,13),(14,'artem66@mail.ru','14','Волчков константин',NULL,14),(15,'artem16@mail.ru','15','Запольский Алексей',NULL,15),(16,'mrartem1995@mail.ru','16','Ровдо Дарья',NULL,16),(17,'artem1995@mail.ru','17','Иванюкович Надежда',NULL,17),(18,'artemr1995@mail.ru','18','Кукуев Михаил',NULL,18),(19,'artem1996@mail.ru','19','Палазник Иван',NULL,19),(20,'dariab@mail.ru','20','Черепкин Николай',NULL,20),(21,'darya@mail.ru','21','Пойда Иван',NULL,21),(22,'busd@mail.ru','22','Хлыстун Виктория',NULL,22),(23,'ivan65@mail.ru','23','Иван Жамойдин',NULL,23),(24,'alex8@mail.ru','24','Александр Пшеничник',NULL,24),(25,'kostya16@mail.ru','25','Константин Марлюков',NULL,25),(26,'pavel76@mail.ru','26','Павел Шаповалов',NULL,26),(27,'sergey09@mail.ru','27','Сергей Наливко',NULL,27),(28,'vzcheslav65@mail.ru','28','Вячеслав Брежнев',NULL,28),(29,'dima1994@mail.ru','29','Дмитрий Скращук',NULL,29),(30,'oleg6@mail.ru','30','Олег Бабашко','+375291396905',30),(31,'ilya1991@mail.ru','31','Илья Бузюк',NULL,31),(32,'alexey13@mail.ru','32','Алексей Кирильчик',NULL,32),(33,'zhanna7@mail.ru','33','Василенко Жанна',NULL,33),(34,'ivanov@mail.ru','34','Иванова Наталья',NULL,34),(35,'smirnova@mail.ru','35','Смирнова Инна',NULL,35),(36,'petrov@mail.ru','36','Петров Алексей',NULL,36),(37,'sergeev13@mail.ru','37','Сергеев Павел',NULL,37);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
