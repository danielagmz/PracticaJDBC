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
('Kevin Durant', 201, 91, 1),
('Stephen Curry', 202, 92, 1),
('Giannis Antetokounmpo', 203, 93, 1),
('Luka Dončić', 204, 94, 1),
('James Harden', 205, 95, 1),
('Anthony Davis', 206, 96, 1),
('Kawhi Leonard', 207, 97, 1),
('Damian Lillard', 208, 98, 1),
('Nikola Jokić', 209, 99, 1),
('Jayson Tatum', 200, 90, 2),
('Jimmy Butler', 201, 91, 2),
('Joel Embiid', 202, 92, 2),
('Devin Booker', 203, 93, 2),
('Trae Young', 204, 94, 2),
('Karl-Anthony Towns', 205, 95, 2),
('Bradley Beal', 206, 96, 2),
('Zion Williamson', 207, 97, 2),
('Donovan Mitchell', 208, 98, 2),
('Ja Morant', 209, 99, 2),
('Chris Paul', 200, 90, 3),
('Paul George', 201, 91, 3),
('Rudy Gobert', 202, 92, 3),
('Kyrie Irving', 203, 93, 3),
('Bam Adebayo', 204, 94, 3),
('Russell Westbrook', 205, 95, 3),
('Klay Thompson', 206, 96, 3),
('Draymond Green', 207, 97, 3),
('Andrew Wiggins', 208, 98, 3),
('Zach LaVine', 209, 99, 3),
('Brandon Ingram', 200, 90, 4),
('Jrue Holiday', 201, 91, 4),
('DeMar DeRozan', 202, 92, 4),
('Domantas Sabonis', 203, 93, 4),
('Nikola Vučević', 204, 94, 4),
('CJ McCollum', 205, 95, 4),
('Jamal Murray', 206, 96, 4),
('Michael Porter Jr.', 207, 97, 4),
('Darius Garland', 208, 98, 4),
('Aaron Fox', 209, 99, 4),
('LaMelo Ball', 200, 90, 5),
('Shai Gilgeous-Alexander', 201, 91, 5),
('Jaylen Brown', 202, 92, 5),
('Julius Randle', 203, 93, 5),
('Kristaps Porziņģis', 204, 94, 5),
('Fred VanVleet', 205, 95, 5),
('Pascal Siakam', 206, 96, 5),
('John Collins', 207, 97, 5),
('Clint Capela', 208, 98, 5),
('Jaren Jackson Jr.', 209, 99, 5),
('Jonas Valančiūnas', 200, 90, 6),
('Myles Turner', 201, 91, 6),
('Tobias Harris', 202, 92, 6),
('Gordon Hayward', 203, 93, 6),
('Tyler Herro', 204, 94, 6),
('Derrick Rose', 205, 95, 6),
('Malcolm Brogdon', 206, 96, 6),
('Buddy Hield', 207, 97, 6),
('Richaun Holmes', 208, 98, 6),
('Marcus Smart', 209, 99, 6),
('Terry Rozier', 200, 90, 7),
('Bogdan Bogdanović', 201, 91, 7),
('Joe Harris', 202, 92, 7),
('Jarrett Allen', 203, 93, 7),
('Jusuf Nurkić', 204, 94, 7),
('Harrison Barnes', 205, 95, 7),
('Mitchell Robinson', 206, 96, 7),
('Ricky Rubio', 207, 97, 7),
('Collin Sexton', 208, 98, 7),
('Seth Curry', 209, 99, 7),
('Norman Powell', 200, 90, 8),
('Gary Trent Jr.', 201, 91, 8),
('Aaron Gordon', 202, 92, 8),
('Robert Covington', 203, 93, 8),
('Ivica Zubac', 204, 94, 8),
('Will Barton', 205, 95, 8),
('Duncan Robinson', 206, 96, 8),
('Kelly Oubre Jr.', 207, 97, 8),
('Dennis Schröder', 208, 98, 8),
('Montrezl Harrell', 209, 99, 8),
('Eric Bledsoe', 200, 90, 9),
('Steven Adams', 201, 91, 9),
('Spencer Dinwiddie', 202, 92, 9),
('Jae Crowder', 203, 93, 9),
('Reggie Jackson', 204, 94, 9),
('P.J. Tucker', 205, 95, 9),
('Brook Lopez', 206, 96, 9),
('Carmelo Anthony', 207, 97, 9),
('Danny Green', 208, 98, 9),
('Patty Mills', 209, 99, 9),
('Derrick White', 200, 90, 10),
('Thaddeus Young', 201, 91, 10),
('Lou Williams', 202, 92, 10),
('Tim Hardaway Jr.', 203, 93, 10),
('Bobby Portis', 204, 94, 10),
('Kevin Love', 205, 95, 10),
('Marvin Bagley III', 206, 96, 10),
('Blake Griffin', 207, 97, 10),
('Eric Gordon', 208, 98, 10),
('Kyle Kuzma', 209, 99, 10);


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
(1, 238, 2, 257),
(3, 249, 4, 233),
(5, 236, 1, 208),
(2, 247, 3, 230),
(4, 227, 5, 213),
(6, 223, 7, 215),
(8, 177, 9, 208),
(10, 217, 1, 222),
(2, 213, 3, 220),
(4, 208, 5, 218);

-- Crear tabla "teams"
CREATE TABLE teams (
    id INT AUTO_INCREMENT,
    nom VARCHAR(50),
    franquicia VARCHAR(50),
    nom_complet VARCHAR(100) AS (concat(franquicia," ",nom)),
    CONSTRAINT pk_teams_id PRIMARY KEY (id)
);

-- Insertar datos en la tabla "teams"
INSERT INTO teams (nom,franquicia)
VALUES 
('Lakers','Los Angeles'),
('Nets','Brooklyn'),
('Warriors','San Francisco'),
('Bucks','Milwaukee'),
('Mavericks','Dallas'),
('76ers','Philadelphia'),
('Clippers','Los Angeles'),
('Blazers','Portland'),
('Nuggets','Denver'),
('Celtics','Boston');


-- Crear tabla "players_matches"
CREATE TABLE players_matches (
    id_match INT,
    id_jugador INT,
    punts INT,
    rebots INT,
    assistencies INT,
    CONSTRAINT fk_players_matches_matches FOREIGN KEY (id_match) REFERENCES matches(id)

);

-- Insertar datos en la tabla "players_matches"
INSERT INTO players_matches (id_match, id_jugador, punts, rebots, assistencies)
VALUES
(1, 1, 25, 10, 9),
(1, 2, 46, 11, 1),
(1, 3, 14, 6, 6),
(1, 4, 24, 3, 7),
(1, 5, 26, 2, 9),
(1, 6, 30, 11, 3),
(1, 7, 14, 13, 5),
(1, 8, 25, 5, 2),
(1, 9, 12, 7, 4),
(1, 10, 22, 4, 7),
(1, 11, 18, 8, 2),
(1, 12, 25, 6, 5),
(1, 13, 20, 9, 3),
(1, 14, 16, 12, 6),
(1, 15, 23, 5, 8),
(1, 16, 29, 4, 7),
(1, 17, 52, 7, 4),
(1, 18, 27, 3, 9),
(1, 19, 17, 8, 5),
(1, 20, 31, 6, 7),
(2, 21, 42, 10, 9),
(2, 22, 15, 11, 1),
(2, 23, 21, 6, 6),
(2, 24, 24, 3, 7),
(2, 25, 25, 2, 9),
(2, 26, 30, 11, 3),
(2, 27, 14, 13, 5),
(2, 28, 44, 5, 2),
(2, 29, 12, 7, 4),
(2, 30, 22, 4, 7),
(2, 31, 18, 8, 2),
(2, 32, 25, 6, 5),
(2, 33, 24, 9, 3),
(2, 34, 16, 12, 6),
(2, 35, 23, 5, 8),
(2, 36, 29, 4, 7),
(2, 37, 23, 7, 4),
(2, 38, 27, 3, 9),
(2, 39, 17, 8, 5),
(2, 40, 31, 6, 7),
(3, 41, 28, 10, 9),
(3, 42, 15, 11, 1),
(3, 43, 21, 6, 6),
(3, 44, 20, 3, 7),
(3, 45, 26, 2, 9),
(3, 46, 30, 11, 3),
(3, 47, 14, 13, 5),
(3, 48, 35, 5, 2),
(3, 49, 12, 7, 4),
(3, 50, 35, 4, 7),
(3, 1, 18, 8, 2),
(3, 2, 25, 6, 5),
(3, 3, 20, 9, 3),
(3, 4, 16, 12, 6),
(3, 5, 15, 5, 8),
(3, 6, 29, 4, 7),
(3, 7, 10, 7, 4),
(3, 8, 27, 3, 9),
(3, 9, 17, 8, 5),
(3, 10, 31, 6, 7),
(4, 11, 28, 10, 9),
(4, 12, 20, 11, 1),
(4, 13, 21, 6, 6),
(4, 14, 24, 3, 7),
(4, 15, 26, 2, 9),
(4, 16, 30, 11, 3),
(4, 17, 29, 13, 5),
(4, 18, 35, 5, 2),
(4, 19, 12, 7, 4),
(4, 20, 22, 4, 7),
(4, 21, 18, 8, 2),
(4, 22, 25, 6, 5),
(4, 23, 20, 9, 3),
(4, 24, 16, 12, 6),
(4, 25, 47, 5, 8),
(4, 26, 10, 4, 7),
(4, 27, 19, 7, 4),
(4, 28, 27, 3, 9),
(4, 29, 17, 8, 5),
(4, 30, 31, 6, 7),
(5, 31, 28, 10, 9),
(5, 32, 15, 11, 1),
(5, 33, 21, 6, 6),
(5, 34, 24, 3, 7),
(5, 35, 26, 2, 9),
(5, 36, 30, 11, 3),
(5, 37, 14, 13, 5),
(5, 38, 35, 5, 2),
(5, 39, 12, 7, 4),
(5, 40, 22, 4, 7),
(5, 41, 18, 8, 2),
(5, 42, 25, 6, 5),
(5, 43, 20, 9, 3),
(5, 44, 16, 12, 6),
(5, 45, 23, 5, 8),
(5, 46, 29, 4, 7),
(5, 47, 19, 7, 4),
(5, 48, 15, 3, 9),
(5, 49, 17, 8, 5),
(5, 50, 31, 6, 7),
(6, 51, 22, 10, 9),
(6, 52, 19, 11, 1),
(6, 53, 21, 6, 6),
(6, 54, 24, 3, 7),
(6, 55, 26, 2, 9),
(6, 56, 28, 11, 3),
(6, 57, 14, 13, 5),
(6, 58, 35, 5, 2),
(6, 59, 12, 7, 4),
(6, 60, 22, 4, 7),
(6, 61, 18, 8, 2),
(6, 62, 25, 6, 5),
(6, 63, 20, 9, 3),
(6, 64, 16, 12, 6),
(6, 65, 23, 5, 8),
(6, 66, 19, 4, 7),
(6, 67, 19, 7, 4),
(6, 68, 27, 3, 9),
(6, 69, 17, 8, 5),
(6, 70, 31, 6, 7),
(7, 71, 12, 10, 9),
(7, 72, 15, 11, 1),
(7, 73, 21, 6, 6),
(7, 74, 24, 3, 7),
(7, 75, 26, 2, 9),
(7, 76, 10, 11, 3),
(7, 77, 14, 13, 5),
(7, 78, 21, 5, 2),
(7, 79, 12, 7, 4),
(7, 80, 22, 4, 7),
(7, 81, 18, 8, 2),
(7, 82, 22, 6, 5),
(7, 83, 20, 9, 3),
(7, 84, 16, 12, 6),
(7, 85, 23, 5, 8),
(7, 86, 29, 4, 7),
(7, 87, 20, 7, 4),
(7, 88, 17, 3, 9),
(7, 89, 17, 8, 5),
(7, 90, 26, 6, 7),
(8, 91, 28, 10, 9),
(8, 92, 15, 11, 1),
(8, 93, 21, 6, 6),
(8, 94, 24, 3, 7),
(8, 95, 26, 2, 9),
(8, 96, 30, 11, 3),
(8, 97, 14, 13, 5),
(8, 98, 25, 5, 2),
(8, 99, 12, 7, 4),
(8, 100, 22, 4, 7),
(8, 1, 20, 8, 2),
(8, 2, 25, 6, 5),
(8, 3, 20, 9, 3),
(8, 4, 16, 12, 6),
(8, 5, 23, 5, 8),
(8, 6, 24, 4, 7),
(8, 7, 19, 7, 4),
(8, 8, 27, 3, 9),
(8, 9, 17, 8, 5),
(8, 10, 31, 6, 7),
(9, 11, 20, 10, 9),
(9, 12, 15, 11, 1),
(9, 13, 21, 6, 6),
(9, 14, 24, 3, 7),
(9, 15, 26, 2, 9),
(9, 16, 24, 11, 3),
(9, 17, 14, 13, 5),
(9, 18, 35, 5, 2),
(9, 19, 12, 7, 4),
(9, 20, 22, 4, 7),
(9, 21, 18, 8, 2),
(9, 22, 25, 6, 5),
(9, 23, 15, 9, 3),
(9, 24, 16, 12, 6),
(9, 25, 23, 5, 8),
(9, 26, 29, 4, 7),
(9, 27, 19, 7, 4),
(9, 28, 27, 3, 9),
(9, 29, 17, 8, 5),
(9, 30, 31, 6, 7),
(10, 31, 28, 10, 9),
(10, 32, 15, 11, 1),
(10, 33, 21, 6, 6),
(10, 34, 24, 3, 7),
(10, 35, 15, 2, 9),
(10, 36, 30, 11, 3),
(10, 37, 14, 13, 5),
(10, 38, 35, 5, 2),
(10, 39, 12, 7, 4),
(10, 40, 14, 4, 7),
(10, 41, 18, 8, 2),
(10, 42, 25, 6, 5),
(10, 43, 20, 9, 3),
(10, 44, 16, 12, 6),
(10, 45, 23, 5, 8),
(10, 46, 29, 4, 7),
(10, 47, 19, 7, 4),
(10, 48, 20, 3, 9),
(10, 49, 17, 8, 5),
(10, 50, 31, 6, 7);

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

-- ------------------ Constraints --------------------------------
    
    
    ALTER TABLE players
		ADD CONSTRAINT fk_players_teams FOREIGN KEY (equipo_actual)
    REFERENCES teams (id);
        
        
-- ---------------- Procedures -----------------------------------
 
 
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

-- ---------------- Triggers -----------------------------------

DROP TRIGGER IF EXISTS player_stats_INS;
DELIMITER //
CREATE TRIGGER player_stats_INS AFTER INSERT ON players FOR EACH ROW
BEGIN
	INSERT INTO player_stats(id_jugador,avg_puntos,avg_rebotes,avg_asistencias)
    VALUES(NEW.id,0,0,0);
END
// DELIMITER ;

DELIMITER //
DROP TRIGGER IF EXISTS player_stats_DEL;
DELIMITER //
CREATE TRIGGER player_stats_DEL AFTER DELETE ON players FOR EACH ROW
BEGIN
	DELETE FROM player_stats
    WHERE id_jugador=OLD.id;
END
// DELIMITER ;

DROP TRIGGER IF EXISTS calculate_avg_points;
DELIMITER //
CREATE TRIGGER calculate_avg_stats AFTER INSERT ON players_matches
FOR EACH ROW
BEGIN
    DECLARE total_games INT;
    DECLARE total_points DECIMAL(10,2);
    DECLARE total_rebounds INT;
    DECLARE total_assists INT;
    DECLARE player_id INT;
    DECLARE avg_points DECIMAL(10,2);
    DECLARE avg_rebounds DECIMAL(10,2);
    DECLARE avg_assists DECIMAL(10,2);
    
    -- Obtener el id del jugador del nuevo registro insertado
    SET player_id = NEW.id_jugador;
    
    -- Contar el total de partidos jugados por el jugador
    SELECT COUNT(DISTINCT id_match) INTO total_games
    FROM players_matches
    WHERE id_jugador = player_id;
    
    -- Sumar los puntos, rebotes y asistencias del jugador en todos los partidos
    SELECT SUM(punts), SUM(rebots), SUM(assistencies) INTO total_points, total_rebounds, total_assists
    FROM players_matches
    WHERE id_jugador = player_id;
    
    -- Calcular el promedio de puntos, rebotes y asistencias del jugador
    SET avg_points = total_points / total_games;
    SET avg_rebounds = total_rebounds / total_games;
    SET avg_assists = total_assists / total_games;
    
    -- Actualizar el promedio de puntos, rebotes y asistencias en la tabla player_stats
    UPDATE player_stats
    SET avg_puntos = avg_points,
        avg_rebotes = avg_rebounds,
        avg_asistencias = avg_assists
    WHERE id_jugador = player_id;
END //
DELIMITER ;
