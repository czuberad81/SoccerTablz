
DROP TABLE IF EXISTS teams;
CREATE TABLE teams(
    TeamID LONG PRIMARY KEY AUTO_INCREMENT,
    TeamName nvarchar(30),
    Continent nvarchar(30),
    Played int,
    Won int,
    Drawn int,
    Lost int
);