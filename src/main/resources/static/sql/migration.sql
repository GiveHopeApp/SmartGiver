#Run these scripts to create the database and the user we will be using. Make sure your mySQL
# server is running.

CREATE DATABASE IF NOT EXISTS give_hope_db;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';

GRANT ALL ON give_hope_db.* TO 'admin'@'localhost';