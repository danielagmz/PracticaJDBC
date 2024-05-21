-- Crear la base de datos "nba" si no existe
DROP DATABASE IF EXISTS nba;
CREATE DATABASE IF NOT EXISTS nba;
USE nba;

-- Crear tabla "players"
CREATE TABLE players (
    id INT AUTO_INCREMENT,
    nom VARCHAR(50),
    alcada INT,
    pes INT,
    equipo_actual INT,
    CONSTRAINT pk_players_id PRIMARY KEY (id)
);

-- Insertar datos en la tabla "players"
INSERT INTO players (nom, alcada, pes, equipo_actual)
VALUES 
('LeBron James', 206, 113, 1),
('Kevin Durant', 208, 109, 2),
('Stephen Curry', 191, 84, 3),
('Giannis Antetokounmpo', 211, 110, 4),
('Luka Dončić', 201, 104, 5),
('James Harden', 196, 100, 6),
('Anthony Davis', 208, 115, 1),
('Kawhi Leonard', 201, 102, 7),
('Damian Lillard', 188, 88, 8),
('Nikola Jokić', 211, 129, 9),
('Jayson Tatum', 203, 95, 10),
('Jimmy Butler', 201, 104, 11),
('Joel Embiid', 213, 127, 6),
('Devin Booker', 196, 93, 12),
('Trae Young', 185, 82, 13),
('Karl-Anthony Towns', 211, 112, 14),
('Bradley Beal', 191, 94, 15),
('Zion Williamson', 201, 129, 16),
('Donovan Mitchell', 185, 98, 17),
('Ja Morant', 191, 79, 18);

-- Crear tabla "matches"
CREATE TABLE matches (
    id INT AUTO_INCREMENT,
    id_visitante INT,
    punts_visitant INT,
    id_local INT,
    punts_local INT,
    CONSTRAINT pk_matches_id PRIMARY KEY (id)
);

-- Insertar datos en la tabla "matches"
INSERT INTO matches (id_visitante, punts_visitant, id_local, punts_local)
VALUES 
(1, 102, 2, 98),
(3, 115, 4, 110),
(5, 105, 1, 100),
(2, 110, 3, 112),
(4, 120, 5, 118),
(6, 95, 7, 103),
(8, 109, 9, 97),
(10, 101, 11, 106),
(12, 99, 13, 104),
(14, 107, 15, 102),
(16, 108, 17, 101),
(18, 112, 19, 109),
(20, 110, 6, 107),
(7, 105, 8, 103),
(9, 98, 10, 101),
(11, 100, 12, 97),
(13, 104, 14, 106),
(15, 103, 16, 102),
(17, 110, 18, 107),
(19, 109, 20, 106);

-- Crear tabla "teams"
CREATE TABLE teams (
    id INT AUTO_INCREMENT,
    nom VARCHAR(50),
    CONSTRAINT pk_teams_id PRIMARY KEY (id)
);

-- Insertar datos en la tabla "teams"
INSERT INTO teams (nom)
VALUES 
('Lakers'),
('Nets'),
('Warriors'),
('Bucks'),
('Mavericks'),
('76ers'),
('Clippers'),
('Blazers'),
('Nuggets'),
('Celtics'),
('Heat'),
('Suns'),
('Hawks'),
('Timberwolves'),
('Wizards'),
('Pelicans'),
('Cavaliers'),
('Grizzlies');

-- Crear tabla "players_matches"
CREATE TABLE players_matches (
    id_match INT,
    id_jugador INT,
    punts INT,
    rebots INT,
    assistencies INT,
    CONSTRAINT fk_players_matches_matches FOREIGN KEY (id_match) REFERENCES matches(id),
    CONSTRAINT fk_players_matches_players FOREIGN KEY (id_jugador) REFERENCES players(id)
);

-- Insertar datos en la tabla "players_matches"
INSERT INTO players_matches (id_match, id_jugador, punts, rebots, assistencies)
VALUES
(1, 1, 30, 8, 10),
(2, 1, 25, 10, 7),
(3, 2, 35, 5, 4),
(4, 2, 40, 7, 6),
(5, 3, 28, 3, 9),
(6, 3, 32, 4, 11),
(7, 4, 22, 12, 5),
(8, 4, 18, 15, 7),
(9, 5, 20, 6, 8),
(10, 5, 25, 8, 9),
(11, 6, 27, 5, 10),
(12, 6, 31, 6, 8),
(13, 7, 24, 10, 6),
(14, 7, 30, 12, 7),
(15, 8, 26, 7, 5),
(16, 8, 21, 8, 4),
(17, 9, 29, 4, 10),
(18, 9, 35, 5, 7),
(19, 10, 23, 11, 9),
(20, 10, 20, 12, 8);

-- Crear tabla "player_stats"
CREATE TABLE player_stats (
    id_jugador INT,
    avg_puntos DECIMAL(3,1),
    avg_rebotes DECIMAL(3,1),
    avg_asistencias DECIMAL(3,1),
    CONSTRAINT fk_player_stats_players FOREIGN KEY (id_jugador) REFERENCES players(id)
);

-- Insertar datos en la tabla "player_stats"
INSERT INTO player_stats (id_jugador, avg_puntos, avg_rebotes, avg_asistencias)
VALUES
(1, 27.5, 9.0, 8.5),
(2, 37.5, 6.0, 5.0),
(3, 30.0, 3.5, 10.0),
(4, 20.0, 13.5, 6.0),
(5, 22.5, 7.0, 8.5),
(6, 29.0, 5.5, 9.0),
(7, 27.0, 11.0, 6.5),
(8, 23.5, 7.5, 4.5),
(9, 21.5, 11.5, 8.5),
(10, 23.0, 8.0, 9.5),
(11, 25.0, 6.5, 7.5),
(12, 24.5, 11.5, 6.5),
(13, 28.5, 11.0, 6.5),
(14, 25.5, 8.0, 4.5),
(15, 24.5, 6.5, 8.5),
(16, 28.0, 5.5, 9.5),
(17, 26.5, 8.0, 5.0),
(18, 21.5, 8.0, 7.0),
(19, 22.5, 6.0, 7.0);

-- Crear tabla "historic_players"
CREATE TABLE historic_players (
    id INT,
    punts DECIMAL(5,2),
    rebots DECIMAL(4,2),
    assistencies DECIMAL(4,2),
    ultim_equip VARCHAR(30)
);

-- Insertar datos en la tabla "historic_players"
INSERT INTO historic_players (id, punts, rebots, assistencies, ultim_equip)
VALUES
	(21,138.9,33.4,44.5,'Cavaliers'),
	(22,116.7,27.8,19.5,'Thunder'),
	(23,105.5,22.3,27.8,'Warriors'),
	(24,77.8,36.1,16.7,'Bucks'),
	(25,38.8,11.1,13.9,'Mavericks'),
	(26,100.0,25.0,28.9,'Rockets'),
	(27,66.6,27.7,12.2,'Pelicans'),
	(28,83.3,23.8,15.5,'Raptors'),
	(29,94.4,16.7,24.4,'Trail Blazers'),
	(30,61.1,31.1,22.2,'Nuggets'),
	(31,55.6,16.1,18.3,'Celtics'),
	(32,77.8,20.0,13.9,'Bulls'),
	(33,69.4,33.3,12.2,'76ers'),
	(34,61.1,13.3,21.1,'Suns'),
	(35,49.4,8.9,24.4,'Hawks'),
	(36,46.7,24.4,12.2,'Timberwolves'),
	(37,65.0,13.9,18.3,'Wizards'),
	(38,41.1,16.7,8.9,'Pelicans'),
	(39,54.4,13.9,17.2,'Jazz'),
	(40,44.4,12.8,21.7,'Grizzlies');

-------------------- Constraints --------------------------------
    
    
    ALTER TABLE players
		ADD CONSTRAINT fk_players_teams FOREIGN KEY (equipo_actual)
		REFERENCES teams (id);
        
        
------------------ Procedures -----------------------------------

DROP PROCEDURE IF EXISTS Partidos;
DELIMITER //
CREATE PROCEDURE Partidos(IN pNom VARCHAR(50))
BEGIN
	DECLARE vId_equipo INT;

    SELECT id INTO vId_equipo
		FROM teams
	WHERE nom = pNom;
SELECT CONCAT(te1.nom, ' - ', te2.nom, ': ', ma.punts_visitant, '-', ma.punts_local) AS match_result
	FROM matches ma
	INNER JOIN teams te1 ON ma.id_visitante = te1.id
	INNER JOIN teams te2 ON ma.id_local = te2.id
WHERE ma.id_visitante = vId_equipo OR ma.id_local = vId_equipo;
END //
DELIMITER ;
