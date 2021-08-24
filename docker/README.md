![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-%5cb012.svg?style=for-the-badge&logo=selenium&logoColor=white)

# Docker

Docker and Docker Compose examples focusing on automated testing.

## Getting Started

1\. Install the Docker.

```shell
$ sudo apt-get update
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
```

2\. At the terminal, run the command below to verify that the installation is completed successfully.

```shell
$ docker --version

Docker version 19.03.13, build 4484c46d9d
```

3\. Manage Docker as a non-root user

```shell
$ sudo groupadd docker
$ sudo usermod -aG docker $USER
```

4\. Download and install the Visual Studio Code. Currently, I recommend use the Visual Studio Code to run and customize the project. But you can choose the IDE of you choice.

```
https://code.visualstudio.com/
```

5\. Download the Docker Extension in the Visual Studio Code extension manager in the preferencies.

```
https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-docker
```

## Links

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Docker Hub](https://hub.docker.com/)
- [Selenium](https://www.selenium.dev/)