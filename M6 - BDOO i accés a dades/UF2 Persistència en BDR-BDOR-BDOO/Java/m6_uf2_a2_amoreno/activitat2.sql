-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: activitat2
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Autor`
--

DROP TABLE IF EXISTS `Autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `cognoms` varchar(200) NOT NULL,
  `data_naixement` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Autor`
--

LOCK TABLES `Autor` WRITE;
/*!40000 ALTER TABLE `Autor` DISABLE KEYS */;
INSERT INTO `Autor` VALUES (1,'J.K.','Rowling','1965-07-31'),(2,'George','Orwell','1903-06-25'),(3,'Harper','Lee','1926-04-28'),(4,'J.R.R.','Tolkien','1892-01-03');
/*!40000 ALTER TABLE `Autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_categoria` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
INSERT INTO `Categoria` VALUES (1,'Ficció'),(2,'Ciència-ficció'),(3,'Història'),(4,'Fantasia');
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Llibre`
--

DROP TABLE IF EXISTS `Llibre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Llibre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titol` varchar(200) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `any_publicacio` year NOT NULL,
  `autor_id` int DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `autor_id` (`autor_id`),
  KEY `categoria_id` (`categoria_id`),
  CONSTRAINT `Llibre_ibfk_1` FOREIGN KEY (`autor_id`) REFERENCES `Autor` (`id`),
  CONSTRAINT `Llibre_ibfk_2` FOREIGN KEY (`categoria_id`) REFERENCES `Categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Llibre`
--

LOCK TABLES `Llibre` WRITE;
/*!40000 ALTER TABLE `Llibre` DISABLE KEYS */;
INSERT INTO `Llibre` VALUES (1,'Harry Potter i la pedra filosofal','978-0747532699',1997,1,4),(2,'1984','978-0451524935',1949,2,1),(3,'To Kill a Mockingbird','978-0061120084',1960,3,1),(4,'El Senyor dels Anells','978-0261103573',1954,4,4);
/*!40000 ALTER TABLE `Llibre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prova1`
--

DROP TABLE IF EXISTS `prova1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prova1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `cognoms` varchar(200) NOT NULL,
  `data_naixement` date NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prova1`
--

LOCK TABLES `prova1` WRITE;
/*!40000 ALTER TABLE `prova1` DISABLE KEYS */;
INSERT INTO `prova1` VALUES (1,'Joan','Garcia Pérez','1990-05-15','joan.garcia@email.com'),(2,'Maria','Lopez Martínez','1985-08-22','maria.lopez@email.com'),(3,'Pere','Fernandez Ruiz','1992-12-03','pere.fernandez@email.com'),(4,'Anna','Sánchez Gómez','1988-02-17','anna.sanchez@email.com');
/*!40000 ALTER TABLE `prova1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-15 14:40:55
