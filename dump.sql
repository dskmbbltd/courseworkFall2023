-- --------------------------------------------------------
-- Verkkotietokone:              127.0.0.1
-- Palvelinversio:               10.11.5-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Versio:              12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for taulu perfumedatabase.designer
CREATE TABLE IF NOT EXISTS `designer` (
  `designer_id` bigint(20) NOT NULL,
  `designer_name` varchar(200) NOT NULL,
  PRIMARY KEY (`designer_id`),
  UNIQUE KEY `UK_r9wjpct4skv9jaw5u55q25bkl` (`designer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table perfumedatabase.designer: ~11 rows (suunnilleen)
INSERT INTO `designer` (`designer_id`, `designer_name`) VALUES
	(2, 'Acqua di Parma'),
	(54, 'Burberry'),
	(55, 'Bvlgari'),
	(56, 'Chanel'),
	(57, 'Dolce&Gabbana'),
	(102, 'Etat Libre d\'Orange'),
	(52, 'Gucci'),
	(53, 'Hermès'),
	(58, 'Kenzo'),
	(1, 'Prada'),
	(59, 'Versace');

-- Dumping structure for taulu perfumedatabase.designer_seq
CREATE TABLE IF NOT EXISTS `designer_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Dumping data for table perfumedatabase.designer_seq: ~1 rows (suunnilleen)
INSERT INTO `designer_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(100001, 1, 9223372036854775806, 1, 50, 1000, 0, 0);

-- Dumping structure for taulu perfumedatabase.perfume
CREATE TABLE IF NOT EXISTS `perfume` (
  `gender_spec` varchar(1) DEFAULT NULL,
  `publication_year` int(11) NOT NULL CHECK (`publication_year` >= 1900),
  `designer_id` bigint(20) DEFAULT NULL,
  `perfume_id` bigint(20) NOT NULL,
  `perfume_name` varchar(200) NOT NULL,
  PRIMARY KEY (`perfume_id`),
  KEY `FK3iy3hjf7rkthmhut6dvtr2dtd` (`designer_id`),
  CONSTRAINT `FK3iy3hjf7rkthmhut6dvtr2dtd` FOREIGN KEY (`designer_id`) REFERENCES `designer` (`designer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table perfumedatabase.perfume: ~9 rows (suunnilleen)
INSERT INTO `perfume` (`gender_spec`, `publication_year`, `designer_id`, `perfume_id`, `perfume_name`) VALUES
	('U', 1900, 1, 1, 'Luna Rossa'),
	('F', 2001, 56, 52, 'Coco Mademoiselle'),
	('M', 2010, 56, 53, 'Bleu de Chanel'),
	('U', 2006, 2, 54, 'Acqua di Parma Blu Mediterraneo - Fico di Amalfi'),
	('F', 2001, 57, 55, 'Light Blue'),
	('F', 2006, 57, 152, 'The One'),
	('U', 2017, 102, 153, 'You Or Someone Like You'),
	('M', 1900, 2, 50202, 'Oud'),
	('U', 1900, 2, 50302, 'ffff');

-- Dumping structure for taulu perfumedatabase.perfumer
CREATE TABLE IF NOT EXISTS `perfumer` (
  `perfumer_id` bigint(20) NOT NULL,
  `perfumer_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`perfumer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table perfumedatabase.perfumer: ~6 rows (suunnilleen)
INSERT INTO `perfumer` (`perfumer_id`, `perfumer_name`) VALUES
	(1, 'Daniela Andrier'),
	(2, 'François Demachy'),
	(52, 'Jacques Polge'),
	(103, 'Caroline Sabas'),
	(50104, 'Christine Nagel'),
	(50154, 'TestPerfumer');

-- Dumping structure for taulu perfumedatabase.perfumer_seq
CREATE TABLE IF NOT EXISTS `perfumer_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Dumping data for table perfumedatabase.perfumer_seq: ~1 rows (suunnilleen)
INSERT INTO `perfumer_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(100001, 1, 9223372036854775806, 1, 50, 1000, 0, 0);

-- Dumping structure for taulu perfumedatabase.perfume_perfumer
CREATE TABLE IF NOT EXISTS `perfume_perfumer` (
  `perfume_id` bigint(20) NOT NULL,
  `perfumer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`perfume_id`,`perfumer_id`),
  KEY `FK5ltljuvshtalg60ins74k6ctd` (`perfumer_id`),
  CONSTRAINT `FK5ltljuvshtalg60ins74k6ctd` FOREIGN KEY (`perfumer_id`) REFERENCES `perfumer` (`perfumer_id`),
  CONSTRAINT `FKn1aln0tnu28tvdylq5eijxobj` FOREIGN KEY (`perfume_id`) REFERENCES `perfume` (`perfume_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table perfumedatabase.perfume_perfumer: ~9 rows (suunnilleen)
INSERT INTO `perfume_perfumer` (`perfume_id`, `perfumer_id`) VALUES
	(1, 1),
	(1, 2),
	(52, 52),
	(53, 52),
	(54, 2),
	(55, 2),
	(153, 103),
	(702, 2),
	(50302, 1);

-- Dumping structure for taulu perfumedatabase.perfume_seq
CREATE TABLE IF NOT EXISTS `perfume_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Dumping data for table perfumedatabase.perfume_seq: ~1 rows (suunnilleen)
INSERT INTO `perfume_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(100001, 1, 9223372036854775806, 1, 50, 1000, 0, 0);

-- Dumping structure for taulu perfumedatabase.users
CREATE TABLE IF NOT EXISTS `users` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `UK_en3wad7p8qfu8pcmh62gvef6v` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table perfumedatabase.users: ~2 rows (suunnilleen)
INSERT INTO `users` (`userid`, `password`, `authority`, `username`) VALUES
	(1, '$2a$10$OFBvfxQTiXtNR9RUT/6PXO7m6iuHAT9S134eHFTYB5xcMLYPAUyFO', 'USER', 'user'),
	(2, '$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C', 'ADMIN', 'admin');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
