-- Crear nova base de dades
CREATE DATABASE activitat2;

-- Utilitzar la nova base de dades
USE activitat2;

-- Crear taula `Autor`
DROP TABLE IF EXISTS `Autor`;
CREATE TABLE `Autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `cognoms` varchar(200) NOT NULL,
  `data_naixement` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserir dades a la taula `Autor`
INSERT INTO `Autor` VALUES 
(1, 'Autor', 'Desconegut', '1900-01-01'), -- Autor desconegut
(2, 'George', 'Orwell', '1903-06-25'),
(3, 'Harper', 'Lee', '1926-04-28'),
(4, 'J.R.R.', 'Tolkien', '1892-01-03');

-- Crear taula `Categoria`
DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE `Categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_categoria` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserir dades a la taula `Categoria`
INSERT INTO `Categoria` VALUES 
(1, 'Sense categoria'),
(2, 'Ciència-ficció'),
(3, 'Història'),
(4, 'Fantasia');

-- Crear taula `Llibre`
DROP TABLE IF EXISTS `Llibre`;
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

-- Inserir dades a la taula `Llibre`
INSERT INTO `Llibre` VALUES 
(1, 'Harry Potter i la pedra filosofal', '978-0747532699', 1997, 1, 4),
(2, '1984', '978-0451524935', 1949, 2, 2),
(3, 'To Kill a Mockingbird', '978-0061120084', 1960, 3, 1),
(4, 'El Senyor dels Anells', '978-0261103573', 1954, 4, 4);
