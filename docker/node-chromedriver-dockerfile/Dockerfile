FROM node:latest

# install curl, wget, unzip, chromium and xvfb
RUN apt-get update -y && apt-get install -y curl wget unzip chromium xvfb

# download, unzip, and install chromedriver
RUN export CHROMIUM_VERSION=`chromium -version | grep -Po '(?<=Chromium )[^.]+'`; wget -O /tmp/chromedriver.zip http://chromedriver.storage.googleapis.com/`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE_$CHROMIUM_VERSION`/chromedriver_linux64.zip
RUN unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/
RUN export PATH=$PATH:/usr/local/bin/chromedriver

# set display, dbus and chromium envs
ENV DISPLAY=:99
ENV DBUS_SESSION_BUS_ADDRESS=/dev/null
ENV CHROMIUM_FLAGS='--no-sandbox --disable-dev-shm-usage'

# cmd xvfb
CMD Xvfb :99 -screen 0 1024x768x24 -nolisten tcp