-- Crear la base de dades
CREATE DATABASE IF NOT EXISTS matricula_db;
USE matricula_db;

-- Inserir dades inicials en estudiants
INSERT INTO Estudiant (nom, cognoms, email, dataNaixement) VALUES
('Anna', 'Serrat Puig', 'anna.serrat@example.com', '2000-05-15'),
('Jordi', 'Martínez Roca', 'jordi.martinez@example.com', '1999-08-22'),
('Marta', 'Lopez Vila', 'marta.lopez@example.com', '2001-02-12');

-- Inserir dades inicials en assignatures
INSERT INTO Assignatura (nom, codi, descripcio) VALUES
('Matemàtiques', 'MAT101', 'Introducció a les Matemàtiques'),
('Història', 'HIS102', 'Història Antiga i Medieval'),
('Programació', 'PRO103', 'Fonaments de Programació');

-- Inserir dades inicials en matrícules
INSERT INTO Matricula (estudiant_id, dataMatricula) VALUES
(1, '2024-01-10'),
(2, '2024-01-15');

-- Inserir dades en la taula de relació matrícula-assignatura
INSERT INTO Matricula_Assignatura (Matricula_id, assignatures_id) VALUES
(1, 1), -- Anna matriculada en Matemàtiques
(1, 3), -- Anna matriculada en Programació
(2, 2); -- Jordi matriculat en Història
