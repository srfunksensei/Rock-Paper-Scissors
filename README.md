
Rock-Paper-Scissors
-------------
This is a simple app which enables you to play Rock-Paper-Scissors game all day. The app is designed in a RESTful API way.

## How to run application

To be able to see the application in action you must follow these steps:

1. run `mvn install`
2. run `java -jar rps-0.0.1-SNAPSHOT.jar`

### API documentation

URL for Swagger API User Interface
```
http://localhost:8080/swagger-ui/
```

URL for Swagger API Docs Json
```
http://localhost:8080/v2/api-docs
```

### Examples of usage

To call the API you will need to install [curl](https://curl.se/download.html) to use following methods:

>curl -i -X GET http://localhost:8080/games/gameTypes
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 20 Jul 2017 10:08:34 GMT

["PersonVsComputer","ComputerVsComputer"]
```

>curl -i -H "Content-Type: application/json" -X POST -d '"PersonVsComputer"' http://localhost:8080/games/start
```
HTTP/1.1 201
Location: http://localhost:8080/games/060aa2e4-3325-4a90-9420-a8d0acf667ca
Content-Length: 0
Date: Thu, 20 Jul 2017 10:09:09 GMT
```

>curl -i -H "Content-Type: application/json" -X PUT -d '"Rock"' http://localhost:8080/games/060aa2e4-3325-4a90-9420-a8d0acf667ca
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 20 Jul 2017 10:09:46 GMT

"Win"
```
>curl -i -X GET http://localhost:8080/games/060aa2e4-3325-4a90-9420-a8d0acf667ca
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 20 Jul 2017 10:10:14 GMT

{"id":060aa2e4-3325-4a90-9420-a8d0acf667ca,"gameType":"PersonVsComputer","playerOne":"Rock","playerTwo":"Scissors"}
```
>curl -i -X DELETE http://localhost:8080/games/06be7094-d383-4d57-9999-6f8237453b75
```
HTTP/1.1 204
Date: Thu, 20 Jul 2017 10:10:42 GMT
```

### Dockerizing app

```bash
$ mvn clean package
$ docker build -t rps:latest .
```

running docker image

```bash
$ docker run -it -p 8080:8080 rps
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.