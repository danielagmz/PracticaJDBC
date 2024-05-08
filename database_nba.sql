DROP DATABASE IF EXISTS nba;
CREATE DATABASE IF NOT EXISTS nba;
USE nba;

CREATE TABLE players (
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(50),
  surname VARCHAR(50),
  position VARCHAR(20),
  height INT,
  weight INT,
  birthdate DATE,
  nationality VARCHAR(30),
  PRIMARY KEY (id)
);

CREATE TABLE teams (
  id INT UNSIGNED AUTO_INCREMENT,
  name VARCHAR(50),
  abbreviation VARCHAR(3),
  city VARCHAR(30),
  arena VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE matches (
  id INT UNSIGNED AUTO_INCREMENT,
  date DATE,
  home_team_id INT UNSIGNED,
  away_team_id INT UNSIGNED,
  home_score INT,
  away_score INT,
  PRIMARY KEY (id),
  FOREIGN KEY (home_team_id) REFERENCES teams(id),
  FOREIGN KEY (away_team_id) REFERENCES teams(id)
);

INSERT INTO players (name, surname, position, height, weight, birthdate, nationality) VALUES
  ('John', 'Doe', 'Point Guard', 185, 80, '1995-02-12', 'American'),
  ('Jane', 'Smith', 'Shooting Guard', 178, 70, '1992-08-25', 'Canadian'),
  ('Bob', 'Johnson', 'Small Forward', 190, 85, '1990-11-01', 'British'),
  ('Alice', 'Williams', 'Power Forward', 183, 75, '1993-05-15', 'Australian'),
  ('Mike', 'Brown', 'Center', 205, 100, '1988-03-20', 'American'),
  ('Emily', 'Davis', 'Point Guard', 172, 65, '1996-01-05', 'American'),
  ('Tom', 'Harris', 'Shooting Guard', 188, 80, '1991-09-10', 'British'),
  ('Sarah', 'Taylor', 'Small Forward', 180, 70, '1994-07-22', 'Canadian'),
  ('David', 'Lee', 'Power Forward', 195, 90, '1989-06-15', 'American'),
  ('Rebecca', 'Hall', 'Center', 200, 95, '1992-04-01', 'Australian');
  
  INSERT INTO teams (name, abbreviation, city, arena) VALUES
  ('New York Knicks', 'NYK', 'New York City', 'Madison Square Garden'),
  ('Los Angeles Lakers', 'LAL', 'Los Angeles', 'Staples Center'),
  ('Chicago Bulls', 'CHI', 'Chicago', 'United Center'),
  ('Boston Celtics', 'BOS', 'Boston', 'TD Garden'),
  ('Golden State Warriors', 'GSW', 'Oakland', 'Chase Center');
  
  INSERT INTO matches (date, home_team_id, away_team_id, home_score, away_score) VALUES
  ('2022-01-01', 1, 2, 100, 95),
  ('2022-01-05', 3, 1, 90, 85),
  ('2022-01-10', 2, 4, 105, 100),
  ('2022-01-15', 5, 3, 110, 105),
  ('2022-01-20', 1, 5, 95, 90),
  ('2022-01-25', 4, 2, 100, 95),
  ('2022-02-01', 3, 5, 105, 100),
  ('2022-02-05', 2, 1, 90, 85),
  ('2022-02-10', 5, 4, 110, 105),
  ('2022-02-15', 1, 3, 95, 90);