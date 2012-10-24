CREATE DATABASE gamesmoon;

USE gamesmoon;

CREATE TABLE users (
id int,
Alias char(50) UNIQUE,
pwd_hash char(50)
);

CREATE TABLE sessions (
id int,
userid int,
sessionkey char(50),
login TIMESTAMP,
logout TIMESTAMP
);

CREATE TABLE games (
id int,
name char(50),
path char(50)
);

CREATE TABLE scores (
id int,
game int,
userid int,
created TIMESTAMP,
points int
);
