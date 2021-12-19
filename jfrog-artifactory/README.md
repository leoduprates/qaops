![JFrog](https://img.shields.io/badge/JFrog-%2341BF47.svg?style=for-the-badge&logo=JFrog&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Java](https://img.shields.io/badge/Java-%23007396.svg?style=for-the-badge&logo=Java&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-%2302303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

# JFrog Artifactory

JFrog’s Artifactory open source (Artifactory OSS) project was created to manage binary artifacts for Maven, Gradle, Ivy and SBT as well as generic packages in the open source version.

This document describes instructions for starting a JFrog Artifactory container and publishing a package using Gradle.

## Getting Started

1\. Run Docker Compose to up the JFrog’s container.

```shell
$ docker-compose up
```

2\. Or you can run the command below to start the container without using Docker Compose.

```shell
$ docker run --name artifactory -d -p 8081:8081 -p 8082:8082 docker.bintray.io/jfrog/artifactory-oss:latest
```

3\. Open the project gradle-example in your IDE (in my case the Intellij).

```shell
$ cd gradle-example
```

4\. Login in JFrog and create a repository:

    a. Click on the "Repositories" button.
    b. Click on the "Add Repositories" > "Local Repository".
    c. Choose "Gradle" option.
    d. Fill "libs-snapshot-local" in the "Respository Key" field.
    e. Click on the "Save & Finish" to create the repository.

**Note**: The default JFrog’s Artifactory username and password is:

    • admin
    • password


5\. Publish the artifact using Gradle.

```shell
$ ./gradlew artifactoryPublish

> Task :artifactoryDeploy
Deploying build info...
Build-info successfully deployed. Browse it in Artifactory under http://[localhost]:[port]/artifactory/webapp/builds/gradle-example/[id]

BUILD SUCCESSFUL
```

## Links

- [JFrog’s Artifactory OSS](https://jfrog.com/open-source/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
