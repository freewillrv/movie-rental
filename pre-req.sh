# Docker Images Setup
sudo groupadd docker;
sudo usermod -aG docker ${USER};
newgrp docker;
docker pull mysql:8.0;
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=mysqlpassword -p 3306:3306 -d mysql:8.0

# MySQL Setup a schema
CREATE SCHEMA `movie_rental` DEFAULT CHARACTER SET utf16 ;


#Common commands
#Restart Docker Daemon
sudo systemctl restart docker