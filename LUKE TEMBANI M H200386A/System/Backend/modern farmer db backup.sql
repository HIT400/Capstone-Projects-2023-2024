CREATE DATABASE  IF NOT EXISTS `modernfarmer` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `modernfarmer`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: modernfarmer
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
-- Table structure for table `buying_companies`
--

DROP TABLE IF EXISTS `buying_companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buying_companies` (
  `idbuying_companies` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `buying_price` varchar(45) NOT NULL,
  PRIMARY KEY (`idbuying_companies`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buying_companies`
--

LOCK TABLES `buying_companies` WRITE;
/*!40000 ALTER TABLE `buying_companies` DISABLE KEYS */;
INSERT INTO `buying_companies` VALUES (1,'CODE','Blummington','7'),(2,'Metro Peech','Harare','5'),(3,'N Richards','Mutare','4.5'),(4,'Profeeds','Rusape','7.5'),(5,'Bhola','Chiredzi','4.5'),(6,'Masvingo Chicks','Masvingo','6');
/*!40000 ALTER TABLE `buying_companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chicken_feed_orders`
--

DROP TABLE IF EXISTS `chicken_feed_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chicken_feed_orders` (
  `idchicken_feed_orders` int NOT NULL AUTO_INCREMENT,
  `feed_order_id` varchar(45) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `feed_total_price` varchar(45) NOT NULL,
  `company_name` varchar(45) NOT NULL DEFAULT 'Default',
  `status` varchar(45) DEFAULT 'Pending',
  `date_ordered` varchar(45) NOT NULL,
  PRIMARY KEY (`idchicken_feed_orders`),
  KEY `feed_order_id` (`feed_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chicken_feed_orders`
--

LOCK TABLES `chicken_feed_orders` WRITE;
/*!40000 ALTER TABLE `chicken_feed_orders` DISABLE KEYS */;
INSERT INTO `chicken_feed_orders` VALUES (10,'Lukers_1701935535200','Lukers','150.6','Test','Pending','2023-10-28 16:20:29'),(11,'Lukers_1701935558412','Lukers','150.6','Test','Pending','2023-10-28 16:20:29'),(12,'Lukers_1701935946347','Lukers','150.6','Test','Pending','2023-10-28 16:20:29'),(13,'Lukers_1701936025082','Lukers','150.6','Test','Pending','2023-10-28 16:20:29'),(14,'tj_1701939424269','tj','203','Test','Pending','Thu Dec 07 10:57:04 GMT+02:00 2023'),(15,'tj_1701939528412','tj','203','Test','Pending','Thu Dec 07 10:58:48 GMT+02:00 2023'),(16,'tj_1701939785851','tj','7023','Test','Pending','Thu Dec 07 11:03:05 GMT+02:00 2023'),(17,'tj_1701940293724','tj','271','Test','Pending','Thu Dec 07 11:11:33 GMT+02:00 2023'),(18,'tj_1701940383308','tj','271','Farm & City','Pending','Thu Dec 07 11:13:03 GMT+02:00 2023'),(19,'Luke_1701940450573','Luke','1045','Farm & City','Pending','Thu Dec 07 11:14:10 GMT+02:00 2023'),(20,'Luke_1701940730289','Luke','170','Farm & City','Pending','2023-12-07 11:18:50'),(21,'luke_1703009441928','luke','29000','Lukesoft Administration','Pending','2023-12-19 8:10:41'),(22,'luke_1703009524863','luke','2602.5','Bhola','Approved','2023-12-19 8:12:03'),(23,'luke_1703010364782','luke','4281.5','Farm & City','Pending','2023-12-19 8:26:03'),(24,'tj_1703011304338','tj','1985','Bhola','Revoked','2023-12-19 8:41:43'),(25,'tj_1703011319315','tj','72','Bhola','Pending','2023-12-19 8:41:58'),(26,'luke_1703672316051','luke','1250','Lukesoft Administration','Pending','2023-12-27 12:18:34'),(27,'luke_1703672796529','luke','2377.5','Bhola','Pending','2023-12-27 12:26:35'),(28,'luke_1703672902246','luke','175','Bhola','Pending','2023-12-27 12:28:21'),(29,'tj_1705239223969','tj','1250','Lukesoft Administration','Pending','2024-01-14 3:33:42'),(30,'luke_1705309680723','luke','1045','Farm & City','Pending','2024-01-15 11:08:02'),(31,'munya_1707224376196','munya','22.5','Bhola','Approved','2024-02-06 2:59:35'),(32,'undefined_1707375507461','tj','4246886.4','Bhola','Pending','2024-02-08 8:58:26'),(33,'undefined_1708866353392','tembani','206.4','Bhola','Pending','2024-02-25 3:05:53'),(34,'undefined_1708866430663','tembani','206.4','Bhola','Approved','2024-02-25 3:07:10'),(35,'tembani_1708866478706','tembani','206.4','Bhola','Approved','2024-02-25 3:07:58'),(36,'skilla_1708873937275','skilla','3393.5','Farm & City','Revoked','2024-02-25 5:12:17'),(37,'skilla_1708874053231','skilla','22.5','Bhola','Approved','2024-02-25 5:14:13'),(38,'test_1708887358177','test','688','Bhola','Approved','2024-02-25 8:55:57'),(39,'hit_1708939041492','hit','2425','Irvines Chicks','Approved','2024-02-26 11:17:21'),(40,'luke_1708941224219','luke','1250','Skelta','Pending','2024-02-26 11:53:43'),(41,'tj_1708941256407','tj','1500','Skelta','Approved','2024-02-26 11:54:16'),(42,'bhebhe_1709022047693','bhebhe','3046','Irvines Chicks','Pending','2024-02-27 10:20:46'),(43,'bhebhe_1709025134309','bhebhe','194','Irvines Chicks','Pending','2024-02-27 11:12:13');
/*!40000 ALTER TABLE `chicken_feed_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chicks_placement`
--

DROP TABLE IF EXISTS `chicks_placement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chicks_placement` (
  `idchicks_placement` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) NOT NULL,
  `chicks_breed` varchar(45) NOT NULL,
  `chicks_quantity` varchar(45) NOT NULL,
  `unit_price` varchar(45) NOT NULL,
  `total_price` varchar(45) NOT NULL,
  `customer_username` varchar(45) NOT NULL,
  `placement_date` varchar(45) NOT NULL,
  `placement_status` varchar(45) NOT NULL,
  PRIMARY KEY (`idchicks_placement`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chicks_placement`
--

LOCK TABLES `chicks_placement` WRITE;
/*!40000 ALTER TABLE `chicks_placement` DISABLE KEYS */;
INSERT INTO `chicks_placement` VALUES (23,'Bhola','Ross 308','20','1','20','tj','Nov 26, 2023 1:02:57 PM','Approved'),(24,'Bhola','Arbor Cres','20','0.85','17','tj','Nov 26, 2023 1:03:23 PM','Approved'),(25,'Bhola','Habbard','200','0.6','120','tj','Nov 26, 2023 1:04:17 PM','Approved'),(26,'Bhola','Indian River','300','0.5','150','tj','Nov 26, 2023 1:05:37 PM','Pending'),(27,'Bhola','Ross 308','36','1','36','tj','Nov 26, 2023 1:11:07 PM','Approved'),(28,'Bhola','Cobb 500','25','0.9','22.5','tj','Nov 26, 2023 1:12:25 PM','Approved'),(29,'Profeeds','HBS 92','25','0.7','17.5','tj','Nov 26, 2023 1:13:28 PM','Pending'),(30,'Profeeds','HBS 92','250','0.7','175','Luke','Dec 7, 2023 11:15:56 AM','Pending'),(31,'Lukesoft Administration','Cobb 450','100','0.52','52','luke','Dec 19, 2023 8:09:33 PM','Pending'),(32,'Bhola','Habbard','250','0.6','150','tj','Dec 19, 2023 8:42:55 PM','Pending'),(33,'Bhola','Habbard','2505','0.6','1503','tj','Dec 19, 2023 8:43:06 PM','Approved'),(34,'Bhola','Habbard','25000','0.6','15000','luke','Dec 27, 2023 12:22:47 PM','Revoked'),(35,'Bhola','Habbard','25000','0.6','15000','luke','Dec 27, 2023 12:23:57 PM','Revoked'),(36,'Bhola','Arbor Cres','58','0.85','49.3','tj','Jan 14, 2024 3:33:22 PM','Approved'),(37,'Bhola','Ross 308','258','1','258','luke','Jan 15, 2024 10:01:17 AM','Revoked'),(38,'Profeeds','HBS 92','258','0.7','180.6','luke','Jan 15, 2024 11:07:29 AM','Pending'),(39,'Bhola','Ross 308','500','1','500','tj','Jan 28, 2024 11:43:57 AM','Approved'),(40,'Bhola','Habbard','5000','0.6','3000','tj','Jan 28, 2024 11:44:41 AM','Revoked'),(41,'Bhola','Habbard','250','0.6','150','tj','Jan 28, 2024 12:10:18 PM','Revoked'),(42,'Bhola','Ross 308','5800','1','5800','munya','Feb 6, 2024 2:49:08 PM','Revoked'),(43,'Profeeds','HBS 92','5000','0.7','3500','munya','Feb 6, 2024 2:50:50 PM','Revoked'),(44,'Profeeds','HBS 92','5000','0.7','3500','munya','Feb 6, 2024 2:52:13 PM','Revoked'),(45,'Bhola','Ross 308','20','1','20','tj','Feb 8, 2024 8:45:16 AM','Pending'),(46,'Bhola','Ross 308','258','1','258','sk','Feb 9, 2024 12:38:40 PM','Pending'),(47,'Bhola','Ross 308','250','1','250','tembani','Feb 25, 2024 3:00:13 PM','Approved'),(48,'Bhola','Arbor Cres','250','0.85','212.5','skilla','Feb 25, 2024 5:11:58 PM','Approved'),(49,'Bhola','Ross 308','100','1','100','test','Feb 25, 2024 8:25:11 PM','Approved'),(50,'Bhola','Indian River','250','0.5','125','test','Feb 25, 2024 8:27:58 PM','Approved'),(51,'Bhola','Indian River','250','0.5','125','test','Feb 25, 2024 8:28:43 PM','Approved'),(52,'Irvines Chicks','Ross','500','0.5','250','hit','Feb 26, 2024 11:13:45 AM','Approved'),(53,'testmania','HBS 92','25','60','1500','luke','Feb 26, 2024 11:40:54 AM','Approved'),(54,'Irvines Chicks','Ross','25','0.5','12.5','luke','Feb 26, 2024 11:50:15 AM','Pending'),(55,'Skelta','HBS 92','123','0.65','79.95','tj','Feb 26, 2024 11:55:34 AM','Approved'),(56,'Bhola','Habbard','258','0.6','154.79999999999998','bhebhe','Feb 27, 2024 10:48:37 AM','Pending'),(57,'Irvines Chicks','Ross','250','0.5','125','bhebhe','Feb 27, 2024 11:11:29 AM','Pending'),(58,'Bhola','Habbard','250','0.6','150','tj','Mar 29, 2024 8:33:46 PM','Pending');
/*!40000 ALTER TABLE `chicks_placement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chicksforsale`
--

DROP TABLE IF EXISTS `chicksforsale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chicksforsale` (
  `idchicksforsale` int NOT NULL AUTO_INCREMENT,
  `customer_username` varchar(45) NOT NULL,
  `number_of_birds` varchar(45) NOT NULL,
  `weight_of_birds` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `price` varchar(45) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  `status` varchar(45) DEFAULT 'available',
  PRIMARY KEY (`idchicksforsale`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chicksforsale`
--

LOCK TABLES `chicksforsale` WRITE;
/*!40000 ALTER TABLE `chicksforsale` DISABLE KEYS */;
INSERT INTO `chicksforsale` VALUES (85,'Luke','290','220','Harare','350','2023-10-19 16:59:29','claimed'),(86,'Luke','290','220','Harare','350','2023-10-28 16:20:29','claimed'),(87,'Munya','500','450','Harare','1300','2023-10-28 16:20:29','claimed'),(88,'luke','258','140','test','5','2023-10-29 09:53:56','claimed'),(89,'test','258','36','test','5','2023-11-17 08:41:45','claimed'),(90,'Luke','360','365','Harare ','6','2023-11-17 08:56:46','claimed'),(91,'Luke','360','365','Harare ','90','2023-11-17 08:56:54','claimed'),(92,'Demo_User','250','360','harare','450','2023-11-26 10:36:39','claimed'),(93,'tj','100','120','Rusape','5.5','2023-11-26 10:42:38','claimed'),(94,'tj','369','258','test','89','2023-11-26 01:14:43','claimed'),(95,'Luke','254','369','467','125','2023-12-07 11:16:23','claimed'),(96,'Luke','254','369','467','125','2023-12-07 11:17:26','claimed'),(97,'luke','185','180','Guruwe','4.5','2023-12-19 08:39:40','claimed'),(98,'luke','360','369','Hungwe','7','2023-12-19 08:40:17','claimed'),(101,'Munya','500','450','Harare','1300','2023-10-28 16:20:29','available'),(102,'Munya','500','450','Harare','1300','2023-10-28 16:20:29','available'),(103,'Munya','500','450','Harare','1300','2023-10-28 16:20:29','claimed'),(104,'Munya','500','450','Harare','1300','2023-10-28 16:20:29','claimed'),(105,'Tembani','500','450','Harare','1300','2024-01-02 16:20:29','claimed'),(106,'Tembani','500','450','Harare','1300','2024-01-03 16:20:29','claimed'),(107,'Tembani','500','450','Harare','1300','2024-01-03 16:20:29','claimed'),(108,'Tembani','500','450','Harare','1300','2024-01-03 16:20:29','claimed'),(109,'luke','250','250','Mutare','5','2024-01-15 09:02:07','claimed'),(110,'tj','250','250','Harare','5','2024-01-28 11:40:17','claimed'),(111,'tj','250','250','Harare','5','2024-01-28 11:47:01','claimed'),(112,'tj','250','250','Harare','5','2024-01-28 11:49:36','claimed'),(113,'tj','25','36','test','5','2024-01-28 12:09:42','claimed'),(114,'tj','25','36','test','5','2024-01-28 12:09:56','claimed'),(115,'tj','214','7886','882','9972','2024-01-28 12:13:41','claimed'),(117,'Luke','247','368','test','6','2024-02-07 03:53:32','claimed'),(121,'tj','250','250','harare','5','2024-02-08 09:50:24','claimed'),(122,'sk','100','120','Norton','5.5','2024-02-09 12:08:09','claimed'),(123,'tj','123','123','test','6.5','2024-02-09 02:09:00','claimed'),(127,'tembani','25','25','test','56','2024-02-25 04:28:36','claimed'),(128,'tembani','120','120','test house','5','2024-02-25 04:29:23','claimed'),(130,'skilla','125','123','test','5','2024-02-25 05:12:32','claimed'),(131,'skilla','280','280','Mutare','5','2024-02-25 06:30:36','claimed'),(132,'skilla','258','369','45','56','2024-02-25 08:11:57','claimed'),(133,'test','20','20','test','20','2024-02-25 08:58:36','claimed'),(134,'hit','10000','12500','Belvedere','5','2024-02-26 11:19:30','claimed'),(136,'test','258','369','test','126','2024-02-26 11:35:03','available'),(137,'bhebhe','50','2','test','7','2024-02-27 10:22:48','claimed'),(138,'bhebhe','25','36','13','68','2024-02-27 10:48:27','available'),(139,'bhebhe','120','250','harare','5','2024-02-27 11:12:48','claimed'),(145,'tj','58','258','-17.8123664,31.0464467','57','2024-03-01 12:59:58','claimed'),(149,'tj','100','101','-17.8360068,31.0037965','5','2024-03-29 08:38:23','claimed'),(150,'tj','100','101','-17.8360068,31.0037965','5','2024-03-29 08:38:35','claimed'),(151,'tj','360','360','-17.8360068,31.0037965','56','2024-03-29 08:40:32','claimed'),(155,'tj','120','130','-17.8388113,31.007885','5','2024-04-16 10:49:15','available');
/*!40000 ALTER TABLE `chicksforsale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `idcompany` int NOT NULL AUTO_INCREMENT,
  `companyname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `role` varchar(45) DEFAULT 'default',
  `has_feed` int NOT NULL DEFAULT '0',
  `pass_reset` varchar(100) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `companycol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcompany`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Bhola','Mutanda','$2a$10$5F4lMAyMqWQzEyI85xymPO7mxNrvrfS.DdT0aR5XZG2C.4lOKRzqC','Mutare','0774975876','default',1,NULL,NULL,NULL),(2,'Munya Investments','Tembani','$2a$10$NPNzk/beCVAkFLbcRTRqEeDVNOz7j6iGad6O3ThDMmLsvX8VvDPFC','Mutare','0712005026','admin',1,'8hVE','lukemunyandu@gmail.com',NULL),(3,'Profeeds','Munya','$2a$10$rfEW4nDLzIou7k7Zn8.eR.4rvhSlStUZvUP9gC7Bjt2Ilpq4kCqWm','Harare','0774975876',NULL,1,NULL,NULL,NULL),(10,'Farm & City','FC','$2a$10$w73QbWYwKKYGGc8tmA3UmuyS8P/VukZc0tnb4lceSq3xRgIDDYNHa','Harare','0774975876',NULL,1,NULL,NULL,NULL),(11,'testing','tester','$2a$10$eAvOhlejmWoA1qtxWay8yeWwKazYt3lI0hqe7SoHHnv61AuBS5Zk6','harare','07744567852',NULL,0,NULL,NULL,NULL),(12,'tester','tester4','$2a$10$Aq5vwsMIwLntRyQ1prWnoOAQBXgOhjFP6p/k.xrKb/Fgokr8e9PTq','test','0774545655',NULL,0,NULL,NULL,NULL),(13,'testmania','testmania','$2a$10$F5iEkOYQY7Leg3iUTQvKouYko9PP1P0nTj7saDbN6FrNn1UTLf9CO','testmania','12345678',NULL,1,NULL,NULL,NULL),(14,'cde','cde','$2a$10$J7h54hpBheGYV38b8iOgD.5syWUuNOX7xiLOKKWrwXkBTdhjYDXAS','cde','1234',NULL,0,NULL,NULL,NULL),(15,'Skelta','skelta','$2a$10$FhpQ8aKmPH2CONj.boZKjuGDGLsNd9Vdte2OM.efnkAdWviVlbTmK','Harare','0774975876',NULL,1,NULL,NULL,NULL),(16,'Irvines Chicks','irvines','$2a$10$x54de/LVeB58CpmxoU14iuVz2Cg7aDOmO0B2zHtPomUKehp/ejP96','Harare','0774785412',NULL,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_stock`
--

DROP TABLE IF EXISTS `company_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_stock` (
  `idcompany_stock` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `quantity` varchar(45) NOT NULL,
  `usd_price` varchar(45) DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idcompany_stock`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_stock`
--

LOCK TABLES `company_stock` WRITE;
/*!40000 ALTER TABLE `company_stock` DISABLE KEYS */;
INSERT INTO `company_stock` VALUES (10,'Farm & City','Broiler Starter 50Kg','10','33.5','feed'),(11,'Farm & City','Broiler Grower 50Kg','30','34','feed'),(12,'Farm & City','Broiler Finisher 50Kg','35','37','feed'),(13,'Bhola','test','45','40','test'),(14,'Farm & City','Stress Pack Mix 100G','100','5','Stress Pack Mix'),(15,'Bhola','Cobb 308 Booster','150','10','Booster'),(16,'Bhola','Test Product','200','25','Starter'),(17,'Bhola','Ross 308','90000','1','chicks'),(18,'Bhola','Cobb 500','100000','0.9','feed'),(19,'Bhola','Habbard','500','0.6','chicks'),(20,'Bhola','Arbor Cres','100000','0.85','chicks'),(21,'Bhola','Indian River','50000','0.50','chicks'),(22,'Profeeds','HBS 92','20000','0.70','chicks'),(23,'Lukesoft Administration','CDP','20','0.88','chicks'),(24,'Bhola','Test Feed','50','33.50','feed'),(25,'Bhola','Test Growers','60','45','feed'),(26,'Lukesoft Administration','Test Grower','1000','50','feed'),(27,'Lukesoft Administration','Cobb 450','5000','0.52','chicks'),(28,'Bhola','Stress Pack 500G','1000','7','feed'),(29,'Bhola','Test Name','120','120','feed'),(30,'Irvines Chicks','Starter','50','30','feed'),(31,'Irvines Chicks','Growers','50','35','feed'),(32,'Irvines Chicks','Finisher','50','32','feed'),(33,'Irvines Chicks','Ross','1000','0.50','chicks'),(34,'Skelta','Growers','100','50','feed'),(35,'testmania','HBS 92','1000','60','chicks'),(36,'Skelta','HBS 92','1000','0.65','chicks');
/*!40000 ALTER TABLE `company_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `components`
--

DROP TABLE IF EXISTS `components`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `components` (
  `component_id` int NOT NULL AUTO_INCREMENT,
  `component_name` varchar(45) NOT NULL,
  `component_status` varchar(10) NOT NULL,
  `gpio_pin` varchar(45) NOT NULL,
  PRIMARY KEY (`component_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `components`
--

LOCK TABLES `components` WRITE;
/*!40000 ALTER TABLE `components` DISABLE KEYS */;
INSERT INTO `components` VALUES (1,'Light','0','26'),(9,'Fan','0','21');
/*!40000 ALTER TABLE `components` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_farming_applications`
--

DROP TABLE IF EXISTS `contract_farming_applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_farming_applications` (
  `idcontract_farming_applications` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `supplier_name` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`idcontract_farming_applications`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_farming_applications`
--

LOCK TABLES `contract_farming_applications` WRITE;
/*!40000 ALTER TABLE `contract_farming_applications` DISABLE KEYS */;
INSERT INTO `contract_farming_applications` VALUES (1,'luke','Bhola','approved','2024-04-12 10:00:00');
/*!40000 ALTER TABLE `contract_farming_applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_farming_reporting`
--

DROP TABLE IF EXISTS `contract_farming_reporting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_farming_reporting` (
  `idcontract_farming_reporting` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `mortality` varchar(45) DEFAULT NULL,
  `stage` varchar(45) DEFAULT NULL,
  `growers` varchar(45) DEFAULT NULL,
  `finisher` varchar(45) DEFAULT NULL,
  `starter` varchar(45) DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`idcontract_farming_reporting`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_farming_reporting`
--

LOCK TABLES `contract_farming_reporting` WRITE;
/*!40000 ALTER TABLE `contract_farming_reporting` DISABLE KEYS */;
INSERT INTO `contract_farming_reporting` VALUES (1,'Luke',NULL,NULL,NULL,NULL,NULL,NULL,'2024-04-14 19:02:20'),(2,'Luke','100',NULL,NULL,NULL,NULL,NULL,'2024-04-14 19:03:10'),(3,'Luke','100','1','100g','200g','300g','400g','2024-04-14 19:04:36'),(4,'Luke','100','1','100g','200g','300g','Bhola','2024-04-14 19:20:01'),(5,'Luke','100','1','100g','200g','300g','Bhola','2024-04-14 19:20:01'),(6,'Luke','100','1','100g','200g','300g','Bhola','2024-04-14 19:20:02'),(7,'Luke','100','2','300g','400g','500g','Bhola','2024-04-16 11:04:16'),(8,'Luke','10','0 - 2 Weeks','36','25','25','Bhola','2024-04-16 11:57:44'),(9,'Luke','30','4 - 6 Weeks','50','60','40','Bhola','2024-04-16 11:59:31');
/*!40000 ALTER TABLE `contract_farming_reporting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_order_lines`
--

DROP TABLE IF EXISTS `feed_order_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed_order_lines` (
  `idfeed_order_lines` int NOT NULL AUTO_INCREMENT,
  `feed_order_id` varchar(45) NOT NULL,
  `feed_type` varchar(45) NOT NULL,
  `feed_quantity` varchar(45) NOT NULL,
  `feed_amount` varchar(45) NOT NULL,
  PRIMARY KEY (`idfeed_order_lines`),
  KEY `feed_order_id_idx` (`feed_order_id`),
  CONSTRAINT `feed_order_id` FOREIGN KEY (`feed_order_id`) REFERENCES `chicken_feed_orders` (`feed_order_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_order_lines`
--

LOCK TABLES `feed_order_lines` WRITE;
/*!40000 ALTER TABLE `feed_order_lines` DISABLE KEYS */;
INSERT INTO `feed_order_lines` VALUES (15,'Lukers_1701935535200','Starter 50KG','1000','500'),(16,'Lukers_1701935535200','Finisher 50KG','5032','500'),(17,'Lukers_1701935558412','Finisher 50KG','5032','500'),(18,'Lukers_1701935558412','Starter 50KG','1000','500'),(19,'Lukers_1701935946347','Starter 50KG','1000','500'),(20,'Lukers_1701935946347','Finisher 50KG','5032','500'),(21,'Lukers_1701936025082','Starter 50KG','1000','500'),(22,'Lukers_1701936025082','Finisher 50KG','5032','500'),(23,'tj_1701939528412','Broiler Grower 50Kg','4','136'),(24,'tj_1701939528412','Broiler Starter 50Kg','2','67'),(25,'tj_1701939785851','Broiler Grower 50Kg','96','3264'),(26,'tj_1701939785851','Broiler Starter 50Kg','36','1206'),(27,'tj_1701939785851','Broiler Finisher 50Kg','69','2553'),(28,'tj_1701940293724','Broiler Grower 50Kg','6','204'),(29,'tj_1701940293724','Broiler Starter 50Kg','2','67'),(30,'tj_1701940383308','Broiler Grower 50Kg','6','204'),(31,'tj_1701940383308','Broiler Starter 50Kg','2','67'),(32,'Luke_1701940450573','Broiler Grower 50Kg','10','340'),(33,'Luke_1701940450573','Broiler Starter 50Kg','10','335'),(34,'Luke_1701940450573','Broiler Finisher 50Kg','10','370'),(35,'Luke_1701940730289','Broiler Grower 50Kg','5','170'),(36,'luke_1703009441928','Test Grower','580','29000'),(37,'luke_1703009524863','Cobb 500','25','22.5'),(38,'luke_1703009524863','Test Feed','30','1005'),(39,'luke_1703009524863','Test Growers','35','1575'),(40,'luke_1703010364782','Broiler Grower 50Kg','36','1224'),(41,'luke_1703010364782','Broiler Finisher 50Kg','60','2220'),(42,'luke_1703010364782','Broiler Starter 50Kg','25','837.5'),(43,'tj_1703011304338','Cobb 500','25','22.5'),(44,'tj_1703011304338','Test Feed','25','837.5'),(45,'tj_1703011304338','Test Growers','25','1125'),(46,'tj_1703011319315','Cobb 500','80','72'),(47,'luke_1703672316051','Test Grower','25','1250'),(48,'luke_1703672796529','Cobb 500','25','22.5'),(49,'luke_1703672796529','Test Feed','30','1005'),(50,'luke_1703672796529','Test Growers','30','1350'),(51,'luke_1703672902246','Stress Pack 500G','25','175'),(52,'tj_1705239223969','Test Grower','25','1250'),(53,'luke_1705309680723','Broiler Starter 50Kg','10','335'),(54,'luke_1705309680723','Broiler Grower 50Kg','10','340'),(55,'luke_1705309680723','Broiler Finisher 50Kg','10','370'),(56,'munya_1707224376196','Cobb 500','25','22.5'),(57,'undefined_1707375507461','Cobb 500','123456','111110.40000000001'),(58,'undefined_1707375507461','Test Feed','123456','4135776'),(59,'undefined_1708866353392','Cobb 500','1','0.9'),(60,'undefined_1708866353392','Test Feed','1','33.5'),(61,'undefined_1708866353392','Stress Pack 500G','1','7'),(62,'undefined_1708866353392','Test Name','1','120'),(63,'undefined_1708866353392','Test Growers','1','45'),(64,'undefined_1708866430663','Cobb 500','1','0.9'),(65,'undefined_1708866430663','Test Feed','1','33.5'),(66,'undefined_1708866430663','Test Name','1','120'),(67,'undefined_1708866430663','Test Growers','1','45'),(68,'undefined_1708866430663','Stress Pack 500G','1','7'),(69,'tembani_1708866478706','Cobb 500','1','0.9'),(70,'tembani_1708866478706','Test Feed','1','33.5'),(71,'tembani_1708866478706','Test Growers','1','45'),(72,'tembani_1708866478706','Stress Pack 500G','1','7'),(73,'tembani_1708866478706','Test Name','1','120'),(74,'skilla_1708873937275','Broiler Grower 50Kg','36','1224'),(75,'skilla_1708873937275','Broiler Finisher 50Kg','36','1332'),(76,'skilla_1708873937275','Broiler Starter 50Kg','25','837.5'),(77,'skilla_1708874053231','Cobb 500','25','22.5'),(78,'test_1708887358177','Cobb 500','20','18'),(79,'test_1708887358177','Test Feed','20','670'),(80,'hit_1708939041492','Growers','25','875'),(81,'hit_1708939041492','Finisher','25','800'),(82,'hit_1708939041492','Starter','25','750'),(83,'luke_1708941224219','Growers','25','1250'),(84,'tj_1708941256407','Growers','30','1500'),(85,'bhebhe_1709022047693','Finisher','3','96'),(86,'bhebhe_1709022047693','Growers','80','2800'),(87,'bhebhe_1709022047693','Starter','5','150'),(88,'bhebhe_1709025134309','Growers','2','70'),(89,'bhebhe_1709025134309','Starter','2','60'),(90,'bhebhe_1709025134309','Finisher','2','64');
/*!40000 ALTER TABLE `feed_order_lines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_place_order`
--

DROP TABLE IF EXISTS `market_place_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `market_place_order` (
  `idmarket_place_order` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `order_id` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Placed',
  `seller_name` varchar(45) NOT NULL,
  `date_ordered` varchar(100) NOT NULL,
  PRIMARY KEY (`idmarket_place_order`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_place_order`
--

LOCK TABLES `market_place_order` WRITE;
/*!40000 ALTER TABLE `market_place_order` DISABLE KEYS */;
INSERT INTO `market_place_order` VALUES (1,'Skilla','120','Accepted','','19-12-2023'),(2,'Skilla','120','Accepted','','19-12-2023'),(3,'Mutanda','91','claimed','','Mon Dec 18 2023 21:50:50 GMT+0200 (Central Africa Time)'),(4,'Mutanda','86','Accepted','','Mon Dec 18 2023 22:36:47 GMT+0200 (Central Africa Time)'),(5,'Mutanda','85','claimed','','Mon Dec 18 2023 22:36:50 GMT+0200 (Central Africa Time)'),(6,'Mutanda','92','claimed','','Mon Dec 18 2023 22:37:38 GMT+0200 (Central Africa Time)'),(7,'Luke','87','claimed','','Tue Dec 19 2023 09:23:57 GMT+0200 (Central Africa Time)'),(8,'Luke','88','claimed','','Tue Dec 19 2023 09:24:02 GMT+0200 (Central Africa Time)'),(9,'Luke','90','claimed','','Tue Dec 19 2023 13:00:48 GMT+0200 (Central Africa Time)'),(10,'Luke','89','Revoked','','Tue Dec 19 2023 13:00:50 GMT+0200 (Central Africa Time)'),(11,'Mutanda','105','claimed','','Tue Jan 02 2024 09:48:31 GMT+0200 (Central Africa Time)'),(12,'Mutanda','106','claimed','','Tue Jan 02 2024 12:25:40 GMT+0200 (Central Africa Time)'),(13,'Mutanda','98','claimed','','Sun Jan 14 2024 11:23:13 GMT+0200 (Central Africa Time)'),(14,'Mutanda','110','claimed','','Sun Jan 28 2024 11:46:21 GMT+0200 (Central Africa Time)'),(15,'Mutanda','111','claimed','tj','Tue Feb 06 2024 15:57:33 GMT+0200 (Central Africa Time)'),(16,'Mutanda','107','claimed','Tembani','Tue Feb 06 2024 15:57:39 GMT+0200 (Central Africa Time)'),(17,'skelta','122','Revoked','sk','Fri Feb 09 2024 12:09:13 GMT+0200 (Central Africa Time)'),(18,'Mutanda','123','claimed','tj','Sun Feb 25 2024 14:49:12 GMT+0200 (Central Africa Time)'),(19,'Mutanda','130','Accepted','skilla','Sun Feb 25 2024 17:12:44 GMT+0200 (Central Africa Time)'),(20,'Mutanda','131','claimed','skilla','Sun Feb 25 2024 19:31:02 GMT+0200 (Central Africa Time)'),(21,'Mutanda','128','claimed','tembani','Sun Feb 25 2024 19:33:17 GMT+0200 (Central Africa Time)'),(22,'Mutanda','133','Accepted','test','Sun Feb 25 2024 20:59:11 GMT+0200 (Central Africa Time)'),(23,'irvines','134','Revoked','hit','Mon Feb 26 2024 11:20:12 GMT+0200 (Central Africa Time)'),(24,'irvines','104','claimed','Munya','Mon Feb 26 2024 11:22:45 GMT+0200 (Central Africa Time)'),(25,'irvines','132','claimed','skilla','Mon Feb 26 2024 11:22:48 GMT+0200 (Central Africa Time)'),(26,'irvines','127','claimed','tembani','Mon Feb 26 2024 11:22:49 GMT+0200 (Central Africa Time)'),(27,'irvines','121','claimed','tj','Mon Feb 26 2024 11:22:51 GMT+0200 (Central Africa Time)'),(28,'irvines','117','claimed','Luke','Mon Feb 26 2024 11:22:53 GMT+0200 (Central Africa Time)'),(29,'irvines','137','Accepted','bhebhe','Tue Feb 27 2024 10:23:00 GMT+0200 (Central Africa Time)'),(30,'irvines','139','claimed','bhebhe','Tue Feb 27 2024 11:17:09 GMT+0200 (Central Africa Time)'),(31,'Mutanda','145','claimed','tj','Fri Mar 01 2024 13:01:02 GMT+0200 (Central Africa Time)'),(32,'Mutanda','151','claimed','tj','Fri Mar 29 2024 20:40:54 GMT+0200 (Central Africa Time)'),(33,'Mutanda','149','Revoked','tj','Fri Mar 29 2024 20:41:01 GMT+0200 (Central Africa Time)'),(34,'Mutanda','150','claimed','tj','Fri Mar 29 2024 20:45:12 GMT+0200 (Central Africa Time)'),(35,'Mutanda','103','claimed','Munya','Fri Apr 05 2024 20:35:31 GMT+0200 (Central Africa Time)');
/*!40000 ALTER TABLE `market_place_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `isIntegrated` int NOT NULL DEFAULT '0',
  `fcm_token` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'Luke Tembani','Lukers','$2a$10$tHuMdKO5VcHy8yr9S2QivOusexIV1fmf0/fktyQPaZxsSNesdZ//.','Harare','0774975876',1,NULL),(7,'Elizabeth Manyame','EM','$2a$10$IjH7NTME1tlKi8m64eU7Eu9pyuklTXJB9Kh8t.V0vraCcqo7jMhvO','Harare','0774975879',0,NULL),(8,'Winsconsin','Win','$2a$10$jeX3GJndiLysAe42l7YdYuTLxlGxsRySKFjmlabj9W1OjeDkB0lJa','Harare','0774975879',0,NULL),(9,'Look Wineward','LW','$2a$10$MB6QBQusYIEOv1EZ9lCgRO4ZlEzF3xkpXU8K8Oi8mQ6hk1TIrv8k6','Harare','0774975879',0,NULL),(10,'Tanaka Dawam','TD','$2a$10$.lB2npFOe35eboW/6bKER.sQt/mxcGc.kDzwY70VACq6uHXsjzagu','Harare','0774975879',0,NULL),(11,'Tatenda Jani','tj','$2a$10$Ki7x8KNhKktB4EYbcxieTeNJfFIPOL7A4iwK8ALaXYwjhhzGLSMOm','6, 2nd Street Ext','+263778123432',0,'cjbnIDd-TgCZARSUvSRihJ:APA91bF4a5cuw-jg5XCjktqXaoDeKZjXI1B0vNnylow5SGmyxdjw2M4k3FKxyEjm1LgWXG9pb5uS2lWtesyN-ZJo9skl7_V9qiO_lpkYyV_j9WKp4ux3WP6rHqtCPFEQqE4b_5t5HY0F'),(12,'luke tembani','djmu','$2a$10$mi6yGxHsJ1.Z2heJczhTJeuL7m2RBrkrfmjAt05Pm8.CbBuoK0fyS','harare','0774975876',0,NULL),(13,'tester','tester','$2a$10$kPWDQPj.3.P0l1yqUWtTv.VNPsAbN2FnWhLOVXVQjUvWQeyDcz/hW','harare','0752319497',0,NULL),(14,'my','my','$2a$10$bkeJWHdXCk1lfxvQyr4KWu6nzfe9Sbfyhk5TexnCMoQYNQ0eWmHQ2','mutare','08451',0,NULL),(15,'Testing Team','test','$2a$10$bgWsyZTemDqJcFZTImvMTeF28vIUBSrwILPUJV8BkJqkyHmulDv2q','Belvedere','07712345678',0,'c6uHbBa2SnSXTCIk4fWjcN:APA91bGwA00duLmB7H-olIv_ipwnPm7C_OWaEXs8B63AtdLuXreX5kSrrlYnnQlNBXMQYxlgG5n34S0kzqEx3RmFpgBlX50iPx4lYHe69B2PwQGuInR7g5bLKhVoKW490RBBUFO7nqEP'),(16,'munya tembani','luke','$2a$10$i4W.4LRan9IyAS7lH/lR3ueDZS2SMmbb.7I0qavZUpyVFdVj6S1cO','52 Lawley Ave, Belvedere','0712005026',0,'c6uHbBa2SnSXTCIk4fWjcN:APA91bGwA00duLmB7H-olIv_ipwnPm7C_OWaEXs8B63AtdLuXreX5kSrrlYnnQlNBXMQYxlgG5n34S0kzqEx3RmFpgBlX50iPx4lYHe69B2PwQGuInR7g5bLKhVoKW490RBBUFO7nqEP'),(17,'Test Engineer Munya','qa','$2a$10$XJt2vmfFwOnM9Pz.l6oo7.gV7zo4gXoD8Q.29jsLTuOxUTR9o26Qi','Harare','0775123456',0,'csXRnq29T825XxM82wBZN9:APA91bHPfN5xJQA-PhXNsQgwz_FrsaiY0Z_xv5L_L8GYtt0w1CtSw1r4-qN4eRw2oTX__9erZ4qPs8eplOL0FCuLopByBnR4ym88tBvU2Ms4rDjNLzlpGVoXKo0S2cNa14rLbvJ9406G'),(18,'munyaradzi','munya','$2a$10$vLYMdtbA/ItJrpz0wleoMeCFSD2305WYwQVSllVcOEScqrr2MkV5W','test','123456789',0,'fqmSliRyQd6Sd1hPiq6G6a:APA91bGXHnVPCZ43lu7WgVaEeAjVpVtfYU_qgsTOvdAk1VfAOWmlkR0sGsNF82GruKc7nP7f0aNWKFUY3wJwpBczbSA7NJm89v8YznQTELq9wvJb8IvaorShPZZ2XDL_WhR6ZOnHFqfR'),(19,'test','cv','$2a$10$0AN.LtLyKPRrSLU..9F.AOLoUB1lvvcHR.gdTav5r3T111ttq.PLy','sry','134826',0,NULL),(20,'Munyaz','munyaz','$2a$10$u25yEcavTtAn9TeEJKkzqOfYkgrMF9RhwBJybqBDk7RoFj4Dby1eS','Harare','0774975876',0,NULL),(21,'Sk','sk','$2a$10$OUQ9tnzidkuRM4PNAwNQ2uPN4C6rPl0EwqWWoQpObqlXz1loOGh3u','Harare','0774975876',0,'fqmSliRyQd6Sd1hPiq6G6a:APA91bEsGd3zWQQrBvKbw9rWhBW14FdXrhl9vQNIldcmYOcu1WTBMAI2fJW3om2iKUOcUxO7Ubp59zi5thakoXDFbBiEeJtETzf6ZuIgeXCIPx7wD_XFXiwu-aL2Sje-Q6e6G4nFcJUF'),(22,'skx','skx','$2a$10$j4LowZ97/cnhe9u6nZiXQ.vVr551.LVisrnXp1icWOkAhJKbZdbBC','test','123456',0,NULL),(23,'skilla','skilla','$2a$10$bod.7msZjsZDOqBobIwRhenS4MWzSF0oNfYjquepS6jyV3KH/RvhK','test','0712005026',0,'fqmSliRyQd6Sd1hPiq6G6a:APA91bERnGjmR36PQhxBa936iEQ7bc8aqBhc-m6M5P6z1pNJUFy872rOYl3Q37sK7cRVkO7PHs6XWm_4SFnGh38JUsPcCjOVx_4l2vpK1oS_RrzyNyajyxw5c3WzO2tAtfG_6fHfT2Tb'),(24,'hit','hit','$2a$10$2mkJR5e2ZxWRzKBtCj9dauelumbN5gciDesNrfvTQvY20Dn9Kzb9O','Belvedere','0778456123',0,'c6uHbBa2SnSXTCIk4fWjcN:APA91bGwA00duLmB7H-olIv_ipwnPm7C_OWaEXs8B63AtdLuXreX5kSrrlYnnQlNBXMQYxlgG5n34S0kzqEx3RmFpgBlX50iPx4lYHe69B2PwQGuInR7g5bLKhVoKW490RBBUFO7nqEP'),(25,'ganda','bhebhe','$2a$10$ycx5hAMmnzGui3IGC8EPROl3Ds2rMXk0wMQIxrHnh2URvnKSRf2k6','belevedere','000000',0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinarians`
--

DROP TABLE IF EXISTS `veterinarians`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinarians` (
  `idveterinarians` int NOT NULL AUTO_INCREMENT,
  `vet_name` varchar(45) NOT NULL,
  `vet_contact` varchar(45) NOT NULL,
  `vet_location` varchar(45) NOT NULL,
  PRIMARY KEY (`idveterinarians`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinarians`
--

LOCK TABLES `veterinarians` WRITE;
/*!40000 ALTER TABLE `veterinarians` DISABLE KEYS */;
INSERT INTO `veterinarians` VALUES (1,'Tembani','0774975876','Harare'),(2,'Toringa','0771234567','Mutare'),(3,'Mugadza','0723145453','Rusape'),(4,'Ncube','0773123459','Bulawayo'),(5,'Magunda','0778555234','Chegutu');
/*!40000 ALTER TABLE `veterinarians` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather_data`
--

DROP TABLE IF EXISTS `weather_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weather_data` (
  `idweather_data` int NOT NULL AUTO_INCREMENT,
  `customer_username` varchar(45) NOT NULL,
  `capture_time` varchar(45) NOT NULL,
  `temperature` varchar(45) NOT NULL,
  `humidity` varchar(45) NOT NULL,
  PRIMARY KEY (`idweather_data`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather_data`
--

LOCK TABLES `weather_data` WRITE;
/*!40000 ALTER TABLE `weather_data` DISABLE KEYS */;
INSERT INTO `weather_data` VALUES (1,'LukeT','14:50','8','1'),(2,'LukeT','16:50','45','15'),(3,'Lukers','16:50','30','10'),(4,'Lukers','16:50','20','5'),(5,'Lukers','16:50','45.4','50%'),(6,'Lukers','16:50','45.4','50%'),(7,'Lukers','16:50','45.4','50%'),(8,'Lukers','16:50','45.4','50%'),(9,'Lukers','16:50','45.4','50%'),(10,'Lukers','16:50','45.4','50%'),(11,'Lukers','16:50','45.4','50%'),(12,'Lukers','16:50','45.4','50%'),(13,'Lukers','16:50','45.4','50%'),(14,'Lukers','16:50','45.4','50%'),(15,'Lukers','16:50','45.4','50%'),(16,'Lukers','16:50','45.4','50%'),(17,'Lukers','16:50','45.4','50%'),(18,'Lukers','16:50','45.4','50%'),(19,'Lukers','16:50','45.4','50%'),(20,'Lukers','16:50','45.4','50%'),(21,'Lukers','16:50','45.4','50%'),(22,'Lukers','16:50','45.4','50%'),(23,'Lukers','16:50','45.4','50%'),(24,'Lukers','16:50','45.4','50%'),(25,'Lukers','16:50','45.4','50%'),(26,'Lukers','16:50','45.4','50%'),(27,'Lukers','16:50','45.4','50%'),(28,'Lukers','16:50','45.4','50%'),(29,'Lukers','16:50','45.4','50%'),(30,'Lukers','16:50','45.4','50%'),(31,'Lukers','16:50','45.4','50%'),(32,'Lukers','16:50','45.4','50%'),(33,'Lukers','16:50','45.4','50%'),(34,'Lukers','16:50','45.4','50%'),(35,'Lukers','16:50','45.4','50%'),(36,'Lukers','16:50','45.4','50%'),(37,'Lukers','16:50','45.4','50%'),(38,'Lukers','16:50','45.4','50%'),(39,'Lukers','16:50','45.4','50%'),(40,'Lukers','16:50','45.4','50%'),(41,'Lukers','16:50','45.4','50%'),(42,'Lukers','16:50','45.4','50%'),(43,'Lukers','16:50','31.0','41.0'),(44,'Lukers','16:50','31.0','41.0'),(45,'Lukers','16:50','31.0','41.0'),(46,'Lukers','16:50','31.0','41.0'),(47,'Lukers','16:50','31.0','41.0'),(48,'Lukers','16:50','31.0','41.0'),(49,'Lukers','16:50','31.0','41.0'),(50,'Lukers','16:50','31.0','49.0'),(51,'Lukers','16:50','31.0','46.0'),(52,'Lukers','16:50','31.0','45.0'),(53,'Lukers','16:50','31.0','43.0'),(54,'Lukers','16:50','31.0','42.0'),(55,'Lukers','16:50','31.0','42.0'),(56,'Lukers','16:50','31.0','42.0'),(57,'Lukers','16:50','31.0','42.0'),(58,'Lukers','16:50','31.0','42.0'),(59,'Lukers','16:50','31.0','42.0'),(60,'Lukers','16:50','31.0','42.0'),(61,'Lukers','16:50','31.0','41.0'),(62,'Lukers','16:50','31.0','41.0'),(63,'Lukers','16:50','31.0','41.0'),(64,'Lukers','16:50','31.0','41.0'),(65,'Lukers','16:50','31.0','41.0'),(66,'Lukers','16:50','30.0','41.0'),(67,'Lukers','16:50','31.0','41.0'),(68,'Lukers','1694378098.0162797','30.0','40.0'),(69,'Lukers','1694378109.161417','30.0','41.0'),(70,'Lukers','1694378114.868992','30.0','41.0'),(71,'Lukers','1694378120.6004245','30.0','41.0'),(72,'Lukers','1694378126.340012','30.0','41.0'),(73,'Lukers','1694378132.0721276','30.0','41.0'),(74,'Lukers','1694378137.807305','30.0','41.0'),(75,'Lukers','1694378149.1128907','30.0','41.0'),(76,'Lukers','1694378154.8065746','30.0','41.0'),(77,'Lukers','1694378160.5414715','30.0','41.0'),(78,'Lukers','1694378166.2750797','30.0','41.0'),(79,'Lukers','1694378177.6394644','30.0','40.0'),(80,'Lukers','1694378183.263162','30.0','40.0'),(81,'Lukers','1694378188.8999712','30.0','40.0'),(82,'Lukers','1694378200.2723393','30.0','41.0'),(83,'Lukers','1694378206.0085564','30.0','41.0'),(84,'Lukers','16:50','20','5'),(85,'Lukers','16:50','20','5');
/*!40000 ALTER TABLE `weather_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16 12:08:34
