# spring-boot-docker-application
Simple spring boot rest api, creating docker image
1) Install Docker for Windows from official website, and install and restart. It will ask to sdw
cd "C:\Program Files\Docker\Docker"
>> ./DockerCli.exe -SwitchDaemon

docker run -d -p 80:80 docker/getting-started

Dockerfile in application tells docker what kinf of application we are making
FROM openjdk:8 (It basically downloads the image from the docker Hub(hub.docker.com)
ADD target/docker-spring-boot.jar docker-spring-boot.jar (We need to copy the target/jar name so that it can run, another name of jar is given to speciy localtion in container, like here we have given jar name so it will add it to root directory )
EXPOSE 8085 (specify port on which container will run)
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]

Now Open PowerShell terminal as adminstrator

Now we will deploy our application jar as a image
Step 1) Build an image 
1.a) go to directory of your application, cd C:\Users\Ezone\Documents\workspace-sts-3.9.7.RELEASE\spring-boot-docker-application
1.b) docker build -f Dockerfile -t docker-spring-boot .  (-t is tag name of the image, we have given it as docker-spring-boot, .(dot) specify where is our Dockerfile present, since it is in current directory so we have used .)
1.c) docker images     (will give you image name)
1.d) docker run -p 8085:8085 docker-spring-boot   (this will run docker image, 8085:8085 (first port is port of the container, and second port is port of the application)
1.e) http://localhost:8085/dockerEx/get/{name}


------------------- Pushing and Pulling Images in DockerHub -------------------
1) create acount on hub.docker.com    (abhay120593, antu.abhay@12)
2) Open Powershell as an adminstrator and user command -->  docker hub
3) check docker image is present or not --->  docker image ls
4) docker login -u abhay120593 -p antu@abhay
5) docker tag docker-spring-boot abhay120593/docker-spring-boot
6) docker push abhay120593/docker-spring-boot

--------------------- Buid and push spring boot docker image using maven plugin-------------------
1) Use the following in POM

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>com.spotify</groupId>
				<artifactId>dockerfile-maven-plugin</artifactId>
				<version>1.4.0</version>
				<configuration>
					<repository>abhay120593/${project.artifactId}</repository>
					<tag>${project.version}</tag>
					<buildArgs>
						<JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
					</buildArgs>
				</configuration>
				<executions>
					<execution>
						<id>default</id>
						<phase>install</phase>
						<goals>
							<goal>build</goal>
							<goal>push</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
		<finalName>spring-boot-docker-maven</finalName>
	</build>

2) Do a maven install, by right clicking on the project