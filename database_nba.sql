DROP DATABASE IF EXISTS nba;
CREATE DATABASE IF NOT EXISTS nba;
USE nba;

CREATE TABLE teams (
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(50),
  abbreviation VARCHAR(3),
  city VARCHAR(30),
  arena VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE players (
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(50),
  surname VARCHAR(50),
  position VARCHAR(20),
  height INT,
  weight INT,
  birthdate DATE,
  nationality VARCHAR(30),
  current_team_id INT UNSIGNED,
  PRIMARY KEY (id),
  FOREIGN KEY (current_team_id) REFERENCES teams(id)
);

CREATE TABLE matches (
  id INT UNSIGNED AUTO_INCREMENT,
  home_team_id INT UNSIGNED,
  away_team_id INT UNSIGNED,
  home_score INT,
  away_score INT,
  home_player_stats JSON,
  away_player_stats JSON,
  PRIMARY KEY (id),
  FOREIGN KEY (home_team_id) REFERENCES teams(id),
  FOREIGN KEY (away_team_id) REFERENCES teams(id)
);

INSERT INTO teams (name, abbreviation, city, arena) VALUES
  ('New York Knicks', 'NYK', 'New York City', 'Madison Square Garden'),
  ('Los Angeles Lakers', 'LAL', 'Los Angeles', 'Staples Center'),
  ('Chicago Bulls', 'CHI', 'Chicago', 'United Center'),
  ('Boston Celtics', 'BOS', 'Boston', 'TD Garden'),
  ('Golden State Warriors', 'GSW', 'Oakland', 'Chase Center');

INSERT INTO players (name, surname, position, height, weight, birthdate, nationality, current_team_id) VALUES
  ('John', 'Doe', 'Point Guard', 185, 80, '1995-02-12', 'American', 1),
  ('Jane', 'Smith', 'Shooting Guard', 178, 70, '1992-08-25', 'Canadian', 2),
  ('Bob', 'Johnson', 'Small Forward', 190, 85, '1990-11-01', 'British', 3),
  ('Alice', 'Williams', 'Power Forward', 183, 75, '1993-05-15', 'Australian', 4),
  ('Mike', 'Brown', 'Center', 205, 100, '1988-03-20', 'American', 5),
  ('Emily', 'Davis', 'Point Guard', 172, 65, '1996-01-05', 'American', 1),
  ('Tom', 'Harris', 'Shooting Guard', 188, 80, '1991-09-10', 'British', 3),
  ('Sarah', 'Taylor', 'Small Forward', 180, 70, '1994-07-22', 'Canadian', 2),
  ('David', 'Lee', 'Power Forward', 195, 90, '1989-06-15', 'American', 5),
  ('Rebecca', 'Hall', 'Center', 200, 95, '1992-04-01', 'Australian', 4);
  
  INSERT INTO matches (home_team_id, away_team_id, home_score, away_score) VALUES 
  (3, 1, 90, 85), 
  (2, 4, 105, 100), 
  (5, 3, 110, 105), 
  (1, 5, 95, 90), 
  (4, 2, 100, 95), 
  (3, 5, 105, 100), 
  (2, 1, 90, 85), 
  (5, 4, 110, 105), 
  (1, 3, 95, 90);
  
  
  -- funciones necesarias
  
  DELIMITER //
CREATE FUNCTION spMediaPuntos(pId INT) RETURNS FLOAT 
BEGIN
	DECLARE vPuntos FLOAT DEFAULT 0;
    
    WITH puntos_partidos_jugador AS (
		SELECT SUM(valor*substring(nom,length(nom)-1)) as puntospp
			FROM estadistic_partit_jugador epj
			INNER JOIN estadistics e ON(epj.estadistic_id=e.estadistic_id)
		WHERE e.estadistic_id IN (3,5,7) AND jugador_id = pId
	GROUP BY partit_id
    ) SELECT ROUND(AVG(puntospp),2) INTO vPuntos
		FROM puntos_partidos_jugador;
    RETURN vPuntos;
END 
// DELIMITER ;