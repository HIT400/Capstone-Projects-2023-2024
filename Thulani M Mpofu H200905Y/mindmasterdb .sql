CREATE DATABASE  IF NOT EXISTS `tetherbiz` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tetherbiz`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: tetherbiz
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `idcompany` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `approval` varchar(45) DEFAULT '0',
  `user_role` varchar(45) DEFAULT 'client',
  `subscribed` varchar(45) DEFAULT '0',
  `rating` varchar(45) DEFAULT '0',
  PRIMARY KEY (`idcompany`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (27,'ADMIN ACCOUNT','Admin','Admin','luke@gmail.com','$2a$10$Xu5g/8FzvBNLKSm.W23GN.CRD3ioXkAV9zViQ3ISl19UMJMhTr4dq','Admin','1','admin','1','0'),(31,'ADMIN ACCOUNT','Admin','Admin','t@gmail.com','$2a$10$VXuBuKoH3Xaa3RWCEvvIUOq.UaoYNWFs6/J/CCARqBjYpgJHZE/GS','Admin','1','admin','1','0'),(36,'ADMIN ACCOUNT','Admin','Admin','tendesai@gmail.com','$2a$10$IBg/N/hnpeShaCc.iIaWuuZ6gi8p7VU7VePHdXtF01QtJsB1zx4ki','Admin','1','admin','1','0'),(46,'tembstar','mutare','12345','tembstar@gmail.com','$2a$10$0UIu5Xmya3ZEjARWHLtyBORWW2Ls9yeQPS5dsKDH3.YqoEOAIvuIW','mutare','1','client','1','0'),(47,'teacher','34 mbare','0774975876','teacher@gmail.com','$2a$10$GbjnOKWERnHkJ1cqXYWK6OFSF.2mEItRzp9boQXf.QDInlmjE9Sdq','Badminton','1','client','1','4'),(48,'client','client','1234','killa@gmail.com','$2a$10$wJGmF.h2kN.dqzEhecwgneTWK/aSzgIHvtvKh0hH1obi7dUoB2X6W','client','0','client','0','0');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `idlocation` int NOT NULL AUTO_INCREMENT,
  `company_id` varchar(45) NOT NULL,
  `location` varchar(1000) NOT NULL,
  PRIMARY KEY (`idlocation`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'1','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15195.809410587759!2d31.02559156274511!3d-17.793936299342818!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1931a5779bbc282b%3A0x1adf2004b440bf6c!2sAvondale%2C%20Harare!5e0!3m2!1sen!2szw!4v1713794447706!5m2!1sen!2szw'),(11,'34','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15195.809410587759!2d31.02559156274511!3d-17.793936299342818!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1931a5779bbc282b%3A0x1adf2004b440bf6c!2sAvondale%2C%20Harare!5e0!3m2!1sen!2szw!4v1713794447706!5m2!1sen!2szw'),(12,'26','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15195.809410587759!2d31.02559156274511!3d-17.793936299342818!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1931a5779bbc282b%3A0x1adf2004b440bf6c!2sAvondale%2C%20Harare!5e0!3m2!1sen!2szw!4v1713794447706!5m2!1sen!2szw'),(13,'41','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15191.733463345392!2d30.9991027!3d-17.84175834999998!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1931a517ff05d7ff%3A0xd4de27d1d70e3835!2sRidgeview!5e0!3m2!1sen!2szw!4v1713933300835!5m2!1sen!2szw'),(14,'46','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15191.733463345392!2d30.9991027!3d-17.84175834999998!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1931a517ff05d7ff%3A0xd4de27d1d70e3835!2sRidgeview!5e0!3m2!1sen!2szw!4v1713933300835!5m2!1sen!2szw'),(15,'47','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15191.733463345392!2d30.9991027!3d-17.84175834999998!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1931a517ff05d7ff%3A0xd4de27d1d70e3835!2sRidgeview!5e0!3m2!1sen!2szw!4v1713933300835!5m2!1s');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `company_id` varchar(45) NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `review_text` varchar(45) NOT NULL,
  `rating` varchar(45) NOT NULL,
  `date_reviewed` datetime NOT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (2,'26','lukesoft@gmail.com','text review','3','2024-04-08 23:06:50'),(3,'25','lukesoft@gmail.com','text review','3','2024-04-08 23:06:51'),(7,'26','tembstar@gmail.com','Love it','3','2024-04-08 23:18:59'),(8,'26','tembstar@gmail.com','Not bad','2','2024-04-08 23:43:48'),(11,'26','tembstar@gmail.com','Best','2','2024-04-09 00:08:30'),(12,'26','tembstar@gmail.com','Okay','2','2024-04-09 00:10:13'),(13,'34','tembstar@gmail.com','Good Service','4','2024-04-09 00:13:50'),(14,'34','tembstar@gmail.com','Bad','0','2024-04-09 00:16:17'),(15,'34','mandoza@gmail.com','Very Professional','5','2024-04-09 00:19:01'),(16,'26','mu@gmail.com','Excellent Service, would try it again','5','2024-04-09 00:27:10'),(17,'26','temba@gmail.com','okay','3','2024-04-22 22:08:12'),(18,'47','killa@gmail.com','Good Tutor','4','2024-05-02 00:08:20');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `company_id` varchar(45) NOT NULL,
  `service_name` varchar(45) NOT NULL,
  `service_price` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `photos` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (5,'21','Car Wash','12','',NULL),(6,'21','Water Distillation','450','',NULL),(7,'22','TEST','10','',NULL),(12,'18','test','5','test desc',NULL),(13,'18','Solar','50','Installation',NULL),(14,'25','Wedding Gown Hiring','50','Wedding Gown Hiring',NULL),(15,'25','test','0','te',NULL),(16,'26','LAPTOP FIX N REPAIRS','12','LAPTOP FIX N REPAIRS',NULL),(17,'26','Screen Guard Changing','5','Screen Guard Changing',NULL),(18,'34','20LITRE BUCKETS','10','20LITRE BUCKETS FOR SALE',NULL),(19,'39','test service','09','tes',NULL),(20,'46','test serve','3','serve',NULL),(21,'47','Maths Lessons','100','Maths Lessons',NULL);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `subscription_id` int NOT NULL AUTO_INCREMENT,
  `company_id` varchar(45) NOT NULL,
  `subscription_status` varchar(45) NOT NULL,
  `subscription_date` datetime NOT NULL,
  `subscription_expiry` datetime NOT NULL,
  PRIMARY KEY (`subscription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (6,'25','subscribed','2024-04-04 10:20:09','2028-01-14 10:20:09'),(7,'24','subscribed','2024-04-04 11:07:24','2024-05-04 11:07:24'),(8,'26','subscribed','2024-04-04 11:29:42','2024-11-30 11:29:42'),(9,'23','subscribed','2024-04-04 11:41:20','2024-05-04 11:41:20'),(10,'34','subscribed','2024-04-09 00:11:49','2024-11-05 00:11:49'),(11,'41','subscribed','2024-04-24 06:36:48','2024-05-24 06:36:48'),(12,'46','subscribed','2024-04-24 06:52:56','2024-05-24 06:52:56'),(13,'47','subscribed','2024-05-01 23:22:15','2024-05-31 23:22:15');
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_role` varchar(45) DEFAULT 'user',
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (34,'Tembani Luke','Harare','tl@gmail.com','0774975876','$2a$10$D7vwQNXwM.MrN/RRb4tOP.YKOvQEnoSy4q1D/1B9mGs6qoBRCvV7i','admin');
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

-- Dump completed on 2024-05-02  5:36:14
