CREATE DATABASE controlsales CHARACTER SET utf8 COLLATE utf8_bin;
CREATE USER 'inventarioDB'@'%' IDENTIFIED BY PASSWORD 'admin2017';
GRANT ALL ON controlsales.* TO 'inventarioDB'@'%';
GRANT ALL ON controlsales TO 'inventarioDB'@'%';
GRANT CREATE ON controlsales TO 'inventarioDB'@'%';
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON *.* TO 'covesUser'@'localhost' IDENTIFIED BY 'admin2017';

CREATE DATABASE sonarqube CHARACTER SET utf8 COLLATE utf8_bin;
CREATE USER 'sonarqubeUser'@'%' IDENTIFIED BY PASSWORD 'sonarqubeUser';
GRANT ALL ON sonarqube.* TO 'sonarqubeUser'@'%';
GRANT ALL ON sonarqube TO 'sonarqubeUser'@'%';
GRANT CREATE ON sonarqube TO 'sonarqubeUser'@'%';
FLUSH PRIVILEGES;