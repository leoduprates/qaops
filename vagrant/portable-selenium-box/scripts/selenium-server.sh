echo "Install Selenium Server"
SELENIUM_VERSION=$(curl "https://selenium-release.storage.googleapis.com/" | perl -n -e'/.*<Key>([^>]+selenium-server-standalone-2[^<]+)/ && print $1')
wget "https://selenium-release.storage.googleapis.com/${SELENIUM_VERSION}" -O selenium-server-standalone.jar
chown vagrant:vagrant selenium-server-standalone.jar

echo "Create Selenium Server Runner"
echo 'sudo -H -u vagrant bash -c "nohup java -Dwebdriver.chrome.driver=chromedriver -jar selenium-server-standalone.jar -log selenium-server-"`date +"%Y%m%d%H%M%S"`".log &"' >> selenium-runner.sh

