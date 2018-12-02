# SpringBootMongoDB
Implementing MongoDB with Spring Boot and Spring security


## MongoDB Setup:
1. Download MongoDB from https://www.mongodb.com/download-center
2. Install MongoDB (follow https://www.tutorialspoint.com/mongodb/)
3. cd to 'bin' (Server\{version}\bin) path after MongoDB installaion 
   and add 'bin path' (like G:\Settings\Windows\ProgramFiles\MongoDB\Server\3.4) to 'environment variable' 
   and add also 'path variable' (like G:\Settings\Windows\ProgramFiles\MongoDB\Server\3.4\bin) to 'system path'
4. Specify data path for MongoDB (springbootmongodb is application database) 
	mongod --dbpath C:\data\springbootmongodb
5. run mongodb by
	mongo


	
## SpringBootMongoDB Application:
1. Specify build tool (Maven Project / Gradle Project) and its version
2. Go to https://start.spring.io/
3. Provide project name under 'Artifact' such as 'SpringBootMongoDB'
4. Add dependency
	Web
	MongoDB
5. Click on 'Generate project' and download generated project
6. Extract zipped project and open it through IDE (Intellij Idea) & add code (follow https://spring.io/guides/gs/accessing-data-mongodb/) & finally run it



## Add username & password for accessing springbootmongodb:

### at mongo:
1. run (https://docs.mongodb.com/manual/tutorial/enable-authentication/)
	use springbootmongodb
	db.createUser(
	  {
		user: "johir",
		pwd: "123456",
		roles: [ { role: "readWrite", db: "springbootmongodb" } ]
	  }
	)
2. also run
	mongo --port 27017 -u "johir" -p "123456" --authenticationDatabase "springbootmongodb"


### at application:
1. add following commands at 'application.properties' (https://tests4geeks.com/spring-data-boot-mongodb-example/)
	spring.data.mongodb.database=springbootmongodb
	spring.data.mongodb.host=localhost
	spring.data.mongodb.port=27017
	spring.data.mongodb.username=johir
	spring.data.mongodb.password=123456
2. run with following command
	gradle -Dauth.key=supersecretz bootRun
