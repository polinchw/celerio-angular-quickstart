CREATE USER 'monitor'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON monitor.* TO 'monitor'@'localhost'WITH GRANT OPTION;

grant all on monitor.* to 'monitor'@'localhost';