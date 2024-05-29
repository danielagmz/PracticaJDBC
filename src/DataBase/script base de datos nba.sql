CREATE DATABASE  IF NOT EXISTS `nba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `nba`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 192.168.56.103    Database: nba
-- ------------------------------------------------------
-- Server version	8.0.29-21

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
-- Table structure for table `historic_players`
--

DROP TABLE IF EXISTS `historic_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historic_players` (
  `id` int DEFAULT NULL,
  `punts` decimal(5,2) DEFAULT NULL,
  `rebots` decimal(4,2) DEFAULT NULL,
  `assistencies` decimal(4,2) DEFAULT NULL,
  `ultim_equip` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historic_players`
--

LOCK TABLES `historic_players` WRITE;
/*!40000 ALTER TABLE `historic_players` DISABLE KEYS */;
INSERT INTO `historic_players` VALUES (21,138.90,33.40,44.50,'Cavaliers'),(22,116.70,27.80,19.50,'Thunder'),(23,105.50,22.30,27.80,'Warriors'),(24,77.80,36.10,16.70,'Bucks'),(25,38.80,11.10,13.90,'Mavericks'),(26,100.00,25.00,28.90,'Rockets'),(27,66.60,27.70,12.20,'Pelicans'),(28,83.30,23.80,15.50,'Raptors'),(29,94.40,16.70,24.40,'Trail Blazers'),(30,61.10,31.10,22.20,'Nuggets'),(31,55.60,16.10,18.30,'Celtics'),(32,77.80,20.00,13.90,'Bulls'),(33,69.40,33.30,12.20,'76ers'),(34,61.10,13.30,21.10,'Suns'),(35,49.40,8.90,24.40,'Hawks'),(36,46.70,24.40,12.20,'Timberwolves'),(37,65.00,13.90,18.30,'Wizards'),(38,41.10,16.70,8.90,'Pelicans'),(39,54.40,13.90,17.20,'Jazz'),(40,44.40,12.80,21.70,'Grizzlies');
/*!40000 ALTER TABLE `historic_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_visitante` int DEFAULT NULL,
  `punts_visitant` int DEFAULT NULL,
  `id_local` int DEFAULT NULL,
  `punts_local` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (1,1,238,2,257),(2,3,249,4,233),(3,5,236,1,208),(4,2,247,3,230),(5,4,227,5,213),(6,6,223,7,215),(7,8,177,9,208),(8,10,217,1,222),(9,2,213,3,220),(10,4,208,5,218);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_stats`
--

DROP TABLE IF EXISTS `player_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_stats` (
  `id_jugador` int DEFAULT NULL,
  `avg_puntos` decimal(3,1) DEFAULT NULL,
  `avg_rebotes` decimal(3,1) DEFAULT NULL,
  `avg_asistencias` decimal(3,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_stats`
--

LOCK TABLES `player_stats` WRITE;
/*!40000 ALTER TABLE `player_stats` DISABLE KEYS */;
INSERT INTO `player_stats` VALUES (1,21.0,8.7,4.3),(2,32.0,7.7,3.7),(3,18.0,8.0,4.0),(4,18.7,9.0,6.3),(5,21.3,4.0,8.3),(6,27.7,6.3,5.7),(7,14.3,9.0,4.3),(8,26.3,3.7,6.7),(9,15.3,7.7,4.7),(10,28.0,5.3,7.0),(11,22.0,9.3,6.7),(12,20.0,9.3,2.3),(13,20.7,7.0,5.0),(14,21.3,6.0,6.7),(15,25.0,3.0,8.7),(16,27.7,8.7,4.3),(17,31.7,11.0,4.7),(18,32.3,4.3,4.3),(19,13.7,7.3,4.3),(20,25.0,4.7,7.0),(21,26.0,8.7,4.3),(22,21.7,7.7,3.7),(23,18.7,8.0,4.0),(24,18.7,9.0,6.3),(25,31.7,4.0,8.3),(26,23.0,6.3,5.7),(27,17.3,9.0,4.3),(28,32.7,3.7,6.7),(29,15.3,7.7,4.7),(30,28.0,5.3,7.0),(31,24.7,9.3,6.7),(32,18.3,9.3,2.3),(33,22.0,7.0,5.0),(34,21.3,6.0,6.7),(35,21.3,3.0,8.7),(36,29.7,8.7,4.3),(37,17.0,11.0,4.7),(38,32.3,4.3,4.3),(39,13.7,7.3,4.3),(40,22.3,4.7,7.0),(41,21.3,8.7,4.3),(42,21.7,7.7,3.7),(43,20.3,8.0,4.0),(44,17.3,9.0,6.3),(45,24.0,4.0,8.3),(46,29.3,6.3,5.7),(47,17.3,9.0,4.3),(48,23.3,3.7,6.7),(49,15.3,7.7,4.7),(50,32.3,5.3,7.0),(51,22.0,10.0,9.0),(52,19.0,11.0,1.0),(53,21.0,6.0,6.0),(54,24.0,3.0,7.0),(55,26.0,2.0,9.0),(56,28.0,11.0,3.0),(57,14.0,13.0,5.0),(58,35.0,5.0,2.0),(59,12.0,7.0,4.0),(60,22.0,4.0,7.0),(61,18.0,8.0,2.0),(62,25.0,6.0,5.0),(63,20.0,9.0,3.0),(64,16.0,12.0,6.0),(65,23.0,5.0,8.0),(66,19.0,4.0,7.0),(67,19.0,7.0,4.0),(68,27.0,3.0,9.0),(69,17.0,8.0,5.0),(70,31.0,6.0,7.0),(71,12.0,10.0,9.0),(72,15.0,11.0,1.0),(73,21.0,6.0,6.0),(74,24.0,3.0,7.0),(75,26.0,2.0,9.0),(76,10.0,11.0,3.0),(77,14.0,13.0,5.0),(78,21.0,5.0,2.0),(79,12.0,7.0,4.0),(80,22.0,4.0,7.0),(81,18.0,8.0,2.0),(82,22.0,6.0,5.0),(83,20.0,9.0,3.0),(84,16.0,12.0,6.0),(85,23.0,5.0,8.0),(86,29.0,4.0,7.0),(87,20.0,7.0,4.0),(88,17.0,3.0,9.0),(89,17.0,8.0,5.0),(90,26.0,6.0,7.0),(91,28.0,10.0,9.0),(92,15.0,11.0,1.0),(93,21.0,6.0,6.0),(94,24.0,3.0,7.0),(95,26.0,2.0,9.0),(96,30.0,11.0,3.0),(97,14.0,13.0,5.0),(98,25.0,5.0,2.0),(99,12.0,7.0,4.0),(100,22.0,4.0,7.0);
/*!40000 ALTER TABLE `player_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `alcada` int DEFAULT NULL,
  `pes` int DEFAULT NULL,
  `equipo_actual` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'LeBron James',200,90,1),(2,'Kevin Durant',201,91,1),(3,'Stephen Curry',202,92,1),(4,'Giannis Antetokounmpo',203,93,1),(5,'Luka Dončić',204,94,1),(6,'James Harden',205,95,1),(7,'Anthony Davis',206,96,1),(8,'Kawhi Leonard',207,97,1),(9,'Damian Lillard',208,98,1),(10,'Nikola Jokić',209,99,1),(11,'Jayson Tatum',200,90,2),(12,'Jimmy Butler',201,91,2),(13,'Joel Embiid',202,92,2),(14,'Devin Booker',203,93,2),(15,'Trae Young',204,94,2),(16,'Karl-Anthony Towns',205,95,2),(17,'Bradley Beal',206,96,2),(18,'Zion Williamson',207,97,2),(19,'Donovan Mitchell',208,98,2),(20,'Ja Morant',209,99,2),(21,'Chris Paul',200,90,3),(22,'Paul George',201,91,3),(23,'Rudy Gobert',202,92,3),(24,'Kyrie Irving',203,93,3),(25,'Bam Adebayo',204,94,3),(26,'Russell Westbrook',205,95,3),(27,'Klay Thompson',206,96,3),(28,'Draymond Green',207,97,3),(29,'Andrew Wiggins',208,98,3),(30,'Zach LaVine',209,99,3),(31,'Brandon Ingram',200,90,4),(32,'Jrue Holiday',201,91,4),(33,'DeMar DeRozan',202,92,4),(34,'Domantas Sabonis',203,93,4),(35,'Nikola Vučević',204,94,4),(36,'CJ McCollum',205,95,4),(37,'Jamal Murray',206,96,4),(38,'Michael Porter Jr.',207,97,4),(39,'Darius Garland',208,98,4),(40,'Aaron Fox',209,99,4),(41,'LaMelo Ball',200,90,5),(42,'Shai Gilgeous-Alexander',201,91,5),(43,'Jaylen Brown',202,92,5),(44,'Julius Randle',203,93,5),(45,'Kristaps Porziņģis',204,94,5),(46,'Fred VanVleet',205,95,5),(47,'Pascal Siakam',206,96,5),(48,'John Collins',207,97,5),(49,'Clint Capela',208,98,5),(50,'Jaren Jackson Jr.',209,99,5),(51,'Jonas Valančiūnas',200,90,6),(52,'Myles Turner',201,91,6),(53,'Tobias Harris',202,92,6),(54,'Gordon Hayward',203,93,6),(55,'Tyler Herro',204,94,6),(56,'Derrick Rose',205,95,6),(57,'Malcolm Brogdon',206,96,6),(58,'Buddy Hield',207,97,6),(59,'Richaun Holmes',208,98,6),(60,'Marcus Smart',209,99,6),(61,'Terry Rozier',200,90,7),(62,'Bogdan Bogdanović',201,91,7),(63,'Joe Harris',202,92,7),(64,'Jarrett Allen',203,93,7),(65,'Jusuf Nurkić',204,94,7),(66,'Harrison Barnes',205,95,7),(67,'Mitchell Robinson',206,96,7),(68,'Ricky Rubio',207,97,7),(69,'Collin Sexton',208,98,7),(70,'Seth Curry',209,99,7),(71,'Norman Powell',200,90,8),(72,'Gary Trent Jr.',201,91,8),(73,'Aaron Gordon',202,92,8),(74,'Robert Covington',203,93,8),(75,'Ivica Zubac',204,94,8),(76,'Will Barton',205,95,8),(77,'Duncan Robinson',206,96,8),(78,'Kelly Oubre Jr.',207,97,8),(79,'Dennis Schröder',208,98,8),(80,'Montrezl Harrell',209,99,8),(81,'Eric Bledsoe',200,90,9),(82,'Steven Adams',201,91,9),(83,'Spencer Dinwiddie',202,92,9),(84,'Jae Crowder',203,93,9),(85,'Reggie Jackson',204,94,9),(86,'P.J. Tucker',205,95,9),(87,'Brook Lopez',206,96,9),(88,'Carmelo Anthony',207,97,9),(89,'Danny Green',208,98,9),(90,'Patty Mills',209,99,9),(91,'Derrick White',200,90,10),(92,'Thaddeus Young',201,91,10),(93,'Lou Williams',202,92,10),(94,'Tim Hardaway Jr.',203,93,10),(95,'Bobby Portis',204,94,10),(96,'Kevin Love',205,95,10),(97,'Marvin Bagley III',206,96,10),(98,'Blake Griffin',207,97,10),(99,'Eric Gordon',208,98,10),(100,'Kyle Kuzma',209,99,10);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players_matches`
--

DROP TABLE IF EXISTS `players_matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players_matches` (
  `id_match` int DEFAULT NULL,
  `id_jugador` int DEFAULT NULL,
  `punts` int DEFAULT NULL,
  `rebots` int DEFAULT NULL,
  `assistencies` int DEFAULT NULL,
  KEY `fk_players_matches_matches` (`id_match`),
  CONSTRAINT `fk_players_matches_matches` FOREIGN KEY (`id_match`) REFERENCES `matches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players_matches`
--

LOCK TABLES `players_matches` WRITE;
/*!40000 ALTER TABLE `players_matches` DISABLE KEYS */;
INSERT INTO `players_matches` VALUES (1,1,25,10,9),(1,2,46,11,1),(1,3,14,6,6),(1,4,24,3,7),(1,5,26,2,9),(1,6,30,11,3),(1,7,14,13,5),(1,8,25,5,2),(1,9,12,7,4),(1,10,22,4,7),(1,11,18,8,2),(1,12,25,6,5),(1,13,20,9,3),(1,14,16,12,6),(1,15,23,5,8),(1,16,29,4,7),(1,17,52,7,4),(1,18,27,3,9),(1,19,17,8,5),(1,20,31,6,7),(2,21,42,10,9),(2,22,15,11,1),(2,23,21,6,6),(2,24,24,3,7),(2,25,25,2,9),(2,26,30,11,3),(2,27,14,13,5),(2,28,44,5,2),(2,29,12,7,4),(2,30,22,4,7),(2,31,18,8,2),(2,32,25,6,5),(2,33,24,9,3),(2,34,16,12,6),(2,35,23,5,8),(2,36,29,4,7),(2,37,23,7,4),(2,38,27,3,9),(2,39,17,8,5),(2,40,31,6,7),(3,41,28,10,9),(3,42,15,11,1),(3,43,21,6,6),(3,44,20,3,7),(3,45,26,2,9),(3,46,30,11,3),(3,47,14,13,5),(3,48,35,5,2),(3,49,12,7,4),(3,50,35,4,7),(3,1,18,8,2),(3,2,25,6,5),(3,3,20,9,3),(3,4,16,12,6),(3,5,15,5,8),(3,6,29,4,7),(3,7,10,7,4),(3,8,27,3,9),(3,9,17,8,5),(3,10,31,6,7),(4,11,28,10,9),(4,12,20,11,1),(4,13,21,6,6),(4,14,24,3,7),(4,15,26,2,9),(4,16,30,11,3),(4,17,29,13,5),(4,18,35,5,2),(4,19,12,7,4),(4,20,22,4,7),(4,21,18,8,2),(4,22,25,6,5),(4,23,20,9,3),(4,24,16,12,6),(4,25,47,5,8),(4,26,10,4,7),(4,27,19,7,4),(4,28,27,3,9),(4,29,17,8,5),(4,30,31,6,7),(5,31,28,10,9),(5,32,15,11,1),(5,33,21,6,6),(5,34,24,3,7),(5,35,26,2,9),(5,36,30,11,3),(5,37,14,13,5),(5,38,35,5,2),(5,39,12,7,4),(5,40,22,4,7),(5,41,18,8,2),(5,42,25,6,5),(5,43,20,9,3),(5,44,16,12,6),(5,45,23,5,8),(5,46,29,4,7),(5,47,19,7,4),(5,48,15,3,9),(5,49,17,8,5),(5,50,31,6,7),(6,51,22,10,9),(6,52,19,11,1),(6,53,21,6,6),(6,54,24,3,7),(6,55,26,2,9),(6,56,28,11,3),(6,57,14,13,5),(6,58,35,5,2),(6,59,12,7,4),(6,60,22,4,7),(6,61,18,8,2),(6,62,25,6,5),(6,63,20,9,3),(6,64,16,12,6),(6,65,23,5,8),(6,66,19,4,7),(6,67,19,7,4),(6,68,27,3,9),(6,69,17,8,5),(6,70,31,6,7),(7,71,12,10,9),(7,72,15,11,1),(7,73,21,6,6),(7,74,24,3,7),(7,75,26,2,9),(7,76,10,11,3),(7,77,14,13,5),(7,78,21,5,2),(7,79,12,7,4),(7,80,22,4,7),(7,81,18,8,2),(7,82,22,6,5),(7,83,20,9,3),(7,84,16,12,6),(7,85,23,5,8),(7,86,29,4,7),(7,87,20,7,4),(7,88,17,3,9),(7,89,17,8,5),(7,90,26,6,7),(8,91,28,10,9),(8,92,15,11,1),(8,93,21,6,6),(8,94,24,3,7),(8,95,26,2,9),(8,96,30,11,3),(8,97,14,13,5),(8,98,25,5,2),(8,99,12,7,4),(8,100,22,4,7),(8,1,20,8,2),(8,2,25,6,5),(8,3,20,9,3),(8,4,16,12,6),(8,5,23,5,8),(8,6,24,4,7),(8,7,19,7,4),(8,8,27,3,9),(8,9,17,8,5),(8,10,31,6,7),(9,11,20,10,9),(9,12,15,11,1),(9,13,21,6,6),(9,14,24,3,7),(9,15,26,2,9),(9,16,24,11,3),(9,17,14,13,5),(9,18,35,5,2),(9,19,12,7,4),(9,20,22,4,7),(9,21,18,8,2),(9,22,25,6,5),(9,23,15,9,3),(9,24,16,12,6),(9,25,23,5,8),(9,26,29,4,7),(9,27,19,7,4),(9,28,27,3,9),(9,29,17,8,5),(9,30,31,6,7),(10,31,28,10,9),(10,32,15,11,1),(10,33,21,6,6),(10,34,24,3,7),(10,35,15,2,9),(10,36,30,11,3),(10,37,14,13,5),(10,38,35,5,2),(10,39,12,7,4),(10,40,14,4,7),(10,41,18,8,2),(10,42,25,6,5),(10,43,20,9,3),(10,44,16,12,6),(10,45,23,5,8),(10,46,29,4,7),(10,47,19,7,4),(10,48,20,3,9),(10,49,17,8,5),(10,50,31,6,7);
/*!40000 ALTER TABLE `players_matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `franquicia` varchar(50) DEFAULT NULL,
  `nom_complet` varchar(100) GENERATED ALWAYS AS (concat(`franquicia`,_utf8mb4' ',`nom`)) VIRTUAL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` (`id`, `nom`, `franquicia`) VALUES (1,'Lakers','Los Angeles'),(2,'Nets','Brooklyn'),(3,'Warriors','San Francisco'),(4,'Bucks','Milwaukee'),(5,'Mavericks','Dallas'),(6,'76ers','Philadelphia'),(7,'Clippers','Los Angeles'),(8,'Blazers','Portland'),(9,'Nuggets','Denver'),(10,'Celtics','Potato');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-29 13:13:35
