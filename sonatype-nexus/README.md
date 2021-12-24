![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Java](https://img.shields.io/badge/Java-%23007396.svg?style=for-the-badge&logo=Java&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-%2302303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

# Sonatype Nexus Repository

Nexus Repository OOS (open-source software) manages binary artifacts.

Community version supported repositories:

- APT
- Composer
- Conan
- CPAN
- Docker
- ELPA
- Git LFS
- Go
- Helm
- Maven
- npm
- NuGet
- p2
- PyPI
- R
- Raw
- RubyGems
- Yum

## Getting Started

This document describes instructions for starting a Nexus Repository by container and publishing and consuming a dependency using Gradle.

### Start Nexus

1\. Run Docker Compose to up Nexus Repository container.

```shell
$ docker-compose up
```

2\. Or you can run the command below to start the container without using Docker Compose.

```shell
$ docker run -d -p 8081:8081 --name nexus sonatype/nexus3
```

3\. To log into the Nexus for the first time, log into the container and get the administrator password.

```shell
docker exec -it [container_id] bash

cat /nexus-data/admin.password
```

### Publish Dependency

1\. Open the project publisher-project in your IDE (in my case the Intellij).

```shell
$ cd publisher-project
```

2\. Create a gradle.properties file with the information below.

```groovy
currentVersion=1.0-SNAPSHOT
artifactory_url=http://localhost:8081
artifactory_user=admin
artifactory_password=password
```

3\. In the build.gradle file, add the maven-publish in plugins.

```groovy
plugins {
    id 'java'
    id 'maven-publish'
}
```

4\. In the build.gradle file, add the publish task.

```groovy
publishing {
    publications {
        maven(MavenPublication) {
            artifact("build/libs/publisher-project-${version}"+".jar") {
                extension 'jar'
            }
        }
    }
    repositories {
        maven {
            name 'nexus'
            url "${artifactory_url}/repository/maven-snapshots/"
            allowInsecureProtocol true
            credentials {
                username "${artifactory_user}"
                password "${artifactory_password}"
            }
        }
    }
}
```

5\. Run publish command

```groovy
$ ./gradlew clean build publish

> Task :publish

BUILD SUCCESSFUL
```

### Consume Dependency

1\. Open the project consumer-project in your IDE (in my case the Intellij).

```shell
$ cd consumer-project
```

2\. Create a gradle.properties file with the information below.

```groovy
artifactory_url=http://localhost:8081
artifactory_user=admin
artifactory_password=password
```

3\. In the build.gradle file, add your repository.

```groovy
repositories {
    mavenCentral()
    maven {
        url "${artifactory_url}/repository/maven-snapshots/"
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

- [Nexus Repository](https://help.sonatype.com/repomanager3)
- [Sonatype Nexus Docker: sonatype/nexus3](https://github.com/sonatype/docker-nexus3)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
