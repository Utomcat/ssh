# 服务器启动脚本
nohup java -jar -Dspring.profiles.active=prod -Denv=prod /tomcat/application/ssh-0.0.1-SNAPSHOT.jar >> /tomcat/application/ssh.log 2>&1&