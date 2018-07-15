
Rock-Paper-Scissors
-------------
This is simple app which enables you to play Rock-Paper-Scissors game all day. The app is designed in a RESTful API way.


### How to use it

To start app open console window in the directory where .jar is placed and type the following:
```
java -jar rps-0.0.1-SNAPSHOT.jar

```
this will start up the application.

To call the API you will need to install **curl** to use following methods:

 1. To retrieve all game types
     curl -i -X GET http://localhost:8080/games
 2. To start a new game
    curl -i -H "Content-Type: application/json" -X POST -d "*gameType*" http://localhost:8080/games
 3. To make a move
     curl -i -H "Content-Type: application/json" -X PUT -d "*move*" http://localhost:8080/games/*gameId*
 4. To get the game
     curl -i -X GET http://localhost:8080/games/*gameId*
 5. To abort the game
     curl -i -X DELETE http://localhost:8080/games/*gameId*
where 
* *gameType* is type of the game
* *gameId* is id of the game which you wish to play
* *move* is one of the moves you want to throw

Examples:

>curl -i -X GET http://localhost:8080/games
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 20 Jul 2017 10:08:34 GMT

["PersonVsComputer","ComputerVsComputer"]
```

>curl -i -H "Content-Type: application/json" -X POST -d "0" http://localhost:8080/games
```
HTTP/1.1 201
Location: http://localhost:8080/games/-6728794896628448155
Content-Length: 0
Date: Thu, 20 Jul 2017 10:09:09 GMT
```

>curl -i -H "Content-Type: application/json" -X PUT -d "0" http://localhost:8080/games/-6728794896628448155
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 20 Jul 2017 10:09:46 GMT

"Win"
```
>curl -i -X GET http://localhost:8080/games/-6728794896628448155
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 20 Jul 2017 10:10:14 GMT

{"id":-6728794896628448155,"playerOne":"Rock","playerTwo":"Scissors"}
```
>curl -i -X DELETE http://localhost:8080/games/-6728794896628448155
```
HTTP/1.1 200
Content-Length: 0
Date: Thu, 20 Jul 2017 10:10:42 GMT
```