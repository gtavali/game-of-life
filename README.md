# Conway's Game Of Life
Developed by Gabor Tavali

More info about the game:
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

## Technology Stack
* Spring, Spring Boot
* H2 database
* FlyWay database migration
* jQuery
* REST
* Maven

## Requirements
* JDK 1.8 or later
* Maven 3.2+

## Run
1.) In the project folder:
```
mvn clean spring-boot:run
```
2.) Or create a runnable .jar file:
```
mvn clean install
```
then
```
java -jar target/game-of-life-1.0.jar
```

After that go to
```
http://localhost:8090
```

Feel free to configure another port to application.\
Visit the application.yml file (game-of-life/src/main/resources/) and modify the following line:
```
server:
  port: 8090
```

### Database
The application use the H2 in-memory database.\
H2 provides a web console to check your db.\
Go to
```
http://localhost:8090/h2-console
```
JDBC URL:
```
jdbc:h2:mem:gameoflife
```
The user name and password are also in the mentioned application.yml.

## How to use
* With the application you can simulate the life of a cell pattern.
* You can use the existing patterns from the dropdown list or you can create you own on the board.
* Anytime you can save the current state of your generation or your favourite start pattern.
* Feel free to download Life 1.05 files and load them into the application. You can do that with a simple copy to 
```
game-of-life/src/main/resources/patterns/
```
* Remove the existing patterns from the directory if you do not need them anymore.

## Packaged patterns (Life 1.05)
* Max
* Pi
* Pinball
* Puftrain
* Psrtrain
* Rabbits
* Rpento