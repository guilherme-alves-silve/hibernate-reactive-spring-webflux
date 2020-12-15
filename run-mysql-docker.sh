#!/bin/bash
docker run --rm -d -e MYSQL_ROOT_PASSWORD=pass -p 3325:3306 --name mysql_test mysql:5.7
echo "Connect to mysql client: "
echo "Windows: winpty docker exec -it mysql_test bash -l"
echo "Other OS: docker exec -it mysql_test bash -l"
echo "mysql -uroot -ppass -hlocalhost -P3325"
