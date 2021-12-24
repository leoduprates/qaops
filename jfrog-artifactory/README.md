![JFrog](https://img.shields.io/badge/JFrog-%2341BF47.svg?style=for-the-badge&logo=JFrog&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Java](https://img.shields.io/badge/Java-%23007396.svg?style=for-the-badge&logo=Java&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-%2302303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

# JFrog Artifactory

JFrog’s Artifactory OOS (open-source software) manages binary artifacts.

Community version supported repositories:

- Generic
- Gradle
- Ivy
- Maven
- sbt

## Getting Started

This document describes instructions for starting a # JFrog Artifactory by container and publishing and consuming a dependency using Gradle.

### Start JFrog

1\. Run Docker Compose to up the JFrog’s container.

```shell
$ docker-compose up
```

2\. Or you can run the command below to start the container without using Docker Compose.

```shell
$ docker run --name artifactory -d -p 8081:8081 -p 8082:8082 docker.bintray.io/jfrog/artifactory-oss:latest
```

4\. Login in JFrog and create a repository:

    a. Click on the "Repositories" button.
    b. Click on the "Add Repositories" > "Local Repository".
    c. Choose "Gradle" option.
    d. Fill "libs-release" in the "Respository Key" field.
    e. Click on the "Save & Finish" to create the repository.

**Note**: The default JFrog’s Artifactory username and password is:

    • admin
    • password

### Publish Dependency

1\. Open the project publisher-project in your IDE (in my case the Intellij).

```shell
$ cd publisher-project
```

2\. Create a gradle.properties file with the information below.

```groovy
currentVersion=1.0-SNAPSHOT
artifactory_url=http://localhost:8082
artifactory_user=admin
artifactory_password=password
```

3\. In the build.gradle file, add the maven-publish and com.jfrog.artifactory in plugins.

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(group: 'org.jfrog.buildinfo', name: 'build-info-extractor-gradle', version: '4.+')
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

apply plugin: 'java'
apply plugin: 'maven-publish'
apply plugin: 'com.jfrog.artifactory'
```

4\. In the build.gradle file, add the publish task.

```groovy
publishing {
    publications {
        mavenJava(MavenPublication) {
            from components.java
        }
    }
}

artifactory {
    contextUrl = "${artifactory_url}/artifactory"
    publish {
        repository {
            repoKey = 'libs-release' // The Artifactory repository key to publish to
            username = "${artifactory_user}"
            password = "${artifactory_password}"
        }
        defaults {
            publications('mavenJava')
            publishArtifacts = true
            publishPom = true
        }
    }
}
```

5\. Publish the artifact using Gradle.

```shell
$ ./gradlew artifactoryPublish

> Task :artifactoryDeploy
Deploying build info...
Build-info successfully deployed. Browse it in Artifactory under http://[localhost]:[port]/artifactory/webapp/builds/publisher-project/[id]

BUILD SUCCESSFUL
```

### Consume Dependency

1\. Open the project consumer-project in your IDE (in my case the Intellij).

```shell
$ cd consumer-project
```

2\. Create a gradle.properties file with the information below.

```groovy
artifactory_url=http://localhost:8082
artifactory_user=admin
artifactory_password=password
```

3\. In the build.gradle file, add your repository.

```groovy
repositories {
    mavenCentral()
    maven {
        url "${artifactory_url}/artifactory/libs-release"
        allowInsecureProtocol true
        credentials {
            username "${artifactory_user}"
            password "${artifactory_password}"
        }
    }
}
```

4\. In the build.gradle file, add the dependency.

```groovy
dependencies {
    implementation 'org.example:publisher-project:1.0-SNAPSHOT'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
}
```

5\. Update the project

```shell
$ ./gradlew clean build

> Task :build

BUILD SUCCESSFUL
```

## Links

- [JFrog’s Artifactory OSS](https://jfrog.com/open-source/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
