-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
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
-- Table structure for table `paymentdata`
--

DROP TABLE IF EXISTS `paymentdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentdata` (
  `user` varchar(45) DEFAULT NULL,
  `roomnum` varchar(45) DEFAULT NULL,
  `total` varchar(45) DEFAULT NULL,
  `checkin` varchar(45) DEFAULT NULL,
  `checkout` varchar(45) DEFAULT NULL,
  `paymentdate` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentdata`
--

LOCK TABLES `paymentdata` WRITE;
/*!40000 ALTER TABLE `paymentdata` DISABLE KEYS */;
INSERT INTO `paymentdata` VALUES ('Moss','206','23800','05/07/2023','05/14/2023','Fri May 05 15:54:55 ICT 2023','Approve'),('1','109','4000','05/11/2023','05/12/2023','Thu May 11 02:25:01 ICT 2023',NULL),('Moss','103','17500','05/11/2023','05/18/2023','Thu May 11 02:40:06 ICT 2023',NULL),('Moss','209','4800','05/25/2023','05/26/2023','Thu May 11 05:29:24 ICT 2023','Approve'),('Moss','205','6000','05/16/2023','05/18/2023','Mon May 15 23:01:25 ICT 2023',NULL),('Stang','208','52800','05/20/2023','06/01/2023','Wed May 17 20:52:35 ICT 2023',NULL),('Lung_too','209','4800','05/19/2023','05/20/2023','Thu May 18 21:50:43 ICT 2023','Approve'),('Jhaja','207','4000','05/19/2023','05/20/2023','Thu May 18 22:48:11 ICT 2023',NULL),('MossJerpz','206','3400','05/19/2023','05/20/2023','Fri May 19 00:36:38 ICT 2023','Approve'),('Jhaja','109','124000','05/20/2023','06/20/2023','Fri May 19 12:54:14 ICT 2023','Approve'),('Moss','108','3400','05/20/2023','05/21/2023','Sat May 20 17:45:28 ICT 2023',NULL);
/*!40000 ALTER TABLE `paymentdata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-21  0:58:51
