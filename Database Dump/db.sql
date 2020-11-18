-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: social-network-tiac
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `post_comment_tiac`
--

DROP TABLE IF EXISTS `post_comment_tiac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_comment_tiac` (
  `idpost_comment_tiac` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `createdOn` datetime NOT NULL,
  `user_tiac_iduser_tiac` int(11) NOT NULL,
  `post_tiac_idpost_tiac` int(11) NOT NULL,
  PRIMARY KEY (`idpost_comment_tiac`),
  UNIQUE KEY `idpost_comment_tiac_UNIQUE` (`idpost_comment_tiac`),
  KEY `fk_post_comment_tiac_user_tiac1_idx` (`user_tiac_iduser_tiac`),
  KEY `fk_post_comment_tiac_post_tiac1_idx` (`post_tiac_idpost_tiac`),
  CONSTRAINT `fk_post_comment_tiac_post_tiac1` FOREIGN KEY (`post_tiac_idpost_tiac`) REFERENCES `post_tiac` (`idpost_tiac`),
  CONSTRAINT `fk_post_comment_tiac_user_tiac1` FOREIGN KEY (`user_tiac_iduser_tiac`) REFERENCES `user_tiac` (`iduser_tiac`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_comment_tiac`
--

LOCK TABLES `post_comment_tiac` WRITE;
/*!40000 ALTER TABLE `post_comment_tiac` DISABLE KEYS */;
INSERT INTO `post_comment_tiac` VALUES (35,'My first comment','2020-11-18 01:54:33',2,49),(36,'Nice post','2020-11-18 01:57:56',1,49),(37,'Random text','2020-11-18 02:03:44',1,50),(40,'novi komentar','2020-11-18 02:38:27',2,57);
/*!40000 ALTER TABLE `post_comment_tiac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_grade_tiac`
--

DROP TABLE IF EXISTS `post_grade_tiac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_grade_tiac` (
  `idpost_grade_tiac` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) NOT NULL,
  `user_tiac_iduser_tiac` int(11) NOT NULL,
  `post_tiac_idpost_tiac` int(11) NOT NULL,
  PRIMARY KEY (`idpost_grade_tiac`),
  UNIQUE KEY `idpost_grade_tiac_UNIQUE` (`idpost_grade_tiac`),
  KEY `fk_post_grade_tiac_user_tiac1_idx` (`user_tiac_iduser_tiac`),
  KEY `fk_post_grade_tiac_post_tiac1_idx` (`post_tiac_idpost_tiac`),
  CONSTRAINT `fk_post_grade_tiac_post_tiac1` FOREIGN KEY (`post_tiac_idpost_tiac`) REFERENCES `post_tiac` (`idpost_tiac`),
  CONSTRAINT `fk_post_grade_tiac_user_tiac1` FOREIGN KEY (`user_tiac_iduser_tiac`) REFERENCES `user_tiac` (`iduser_tiac`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_grade_tiac`
--

LOCK TABLES `post_grade_tiac` WRITE;
/*!40000 ALTER TABLE `post_grade_tiac` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_grade_tiac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tiac`
--

DROP TABLE IF EXISTS `post_tiac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_tiac` (
  `idpost_tiac` int(11) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime NOT NULL,
  `content` varchar(500) NOT NULL,
  `user_tiac_iduser_tiac` int(11) NOT NULL,
  PRIMARY KEY (`idpost_tiac`),
  UNIQUE KEY `idpost_tiac_UNIQUE` (`idpost_tiac`),
  KEY `fk_post_tiac_user_tiac_idx` (`user_tiac_iduser_tiac`),
  CONSTRAINT `fk_post_tiac_user_tiac` FOREIGN KEY (`user_tiac_iduser_tiac`) REFERENCES `user_tiac` (`iduser_tiac`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tiac`
--

LOCK TABLES `post_tiac` WRITE;
/*!40000 ALTER TABLE `post_tiac` DISABLE KEYS */;
INSERT INTO `post_tiac` VALUES (49,'2020-11-18 01:54:19','My first post',2),(50,'2020-11-18 01:54:25','My second post',2),(51,'2020-11-18 01:55:14','Test post eee',1),(53,'2020-11-18 01:56:04','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent at est euismod, posuere ligula posuere, ornare arcu. Nunc quis iaculis metus. Phasellus porta nunc ut vestibulum interdum.',1),(55,'2020-11-18 01:56:04','novi post',4),(57,'2020-11-18 01:56:04','nesto novo',4),(58,'2020-11-18 02:39:11','izmena posta',6);
/*!40000 ALTER TABLE `post_tiac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tiac`
--

DROP TABLE IF EXISTS `user_tiac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tiac` (
  `iduser_tiac` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`iduser_tiac`),
  UNIQUE KEY `iduser_tiac_UNIQUE` (`iduser_tiac`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tiac`
--

LOCK TABLES `user_tiac` WRITE;
/*!40000 ALTER TABLE `user_tiac` DISABLE KEYS */;
INSERT INTO `user_tiac` VALUES (1,'test','test'),(2,'abc','123'),(3,'nikola','nikola'),(4,'abcd','1234'),(5,'iadw','123'),(6,'asdfgh','123');
/*!40000 ALTER TABLE `user_tiac` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-18  2:54:39
