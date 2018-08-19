# easycms-spring-boot-starter
A simple file system based on mongo.



## How to use

Add repository in pom.xml

```xml
<repositories>
    <repository>
        <id>releases</id>
        <url>http://mvn.utopiavip.org/content/repositories/releases</url>
    </repository>
</repositories>
```

Add dependency in pom.xml

```xml
<dependency>
	<groupId>org.utopiavip</groupId>
	<artifactId>easycms-spring-boot-starter</artifactId>
	<version>RELEASE</version>
</dependency>
```

property example，`easycms.resource-domain` is the domain of uploaded resource

```yaml
spring:
  data:
    mongodb:
      host: 127.0.0.1
      port: 27017
      database: test
      username: root
      password: a123456
      authentication-database: admin
easycms:
  resource-domain: http://localhost:8080/
```



## About mongo

**/home/ubuntu/mongo** is the data directory of your machine

```shell
sudo docker run --name mongo -p 8081:8081 -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=a123456 -v /home/ubuntu/mongo:/data/db -d mongo
```



It recommanded to use officcal tools to manage MongoDB Data：[MongoDB Compass](https://www.mongodb.com/products/compass)



## REST Operations

### POST /cms/repositories

> create a repository

request

```json
{
    "repoCode":"required",
    "repoName":"required"
}
```

response

```json
{
    "code": "200",
    "data": {
        "creationDate": "2018-08-19T09:35:53.256+0000",
        "lastUpdateDate": "2018-08-19T09:35:53.256+0000",
        "id": "5b7939f9f818d40707290971",
        "repoCode": "required",
        "repoName": "required"
    },
    "message": ""
}
```



### POST /cms/folders

> create a folder

request, parentFolderId is optional

```json
{
    "folderName":"test",
    "repositoryId":"5b7939f9f818d40707290971",
    "parentFolderId":""
}
```

response

```json
{
    "code": "200",
    "data": {
        "creationDate": "2018-08-19T09:38:13.293+0000",
        "lastUpdateDate": "2018-08-19T09:38:13.293+0000",
        "id": "5b793a85f818d40707290972",
        "folderName": "test",
        "repositoryId": "5b7939f9f818d40707290971",
        "parentFolderId": ""
    },
    "message": ""
}
```



### POST /cms/resources

> upload a file with content-type: multipart/form-data

parameters

* file: required
* repositoryId: optional
* folderId: optional

response

```json
{
    "code": "200",
    "data": {
        "fileName": "说明书.pdf",
        "size": 357482,
        "id": "5b7933daf818d404145e45c5",
        "contentType": "application/pdf",
        "url": "http://localhost:8080/cms/resources/5b7933daf818d404145e45c5"
    },
    "message": ""
}
```



### GET /cms/resources/{id}

> download resource

request

```shell
http://localhost:8080/cms/resources/5b7933daf818d404145e45c5
```



## Design

* File must under a repository or a folder, folder must under a repository
* File less than 16M will store in custom mongo collection `CmsFileStorage`
* File greater or equal than 16M will store with mongo GridFS



