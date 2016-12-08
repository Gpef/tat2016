cd vagrant
echo -----------------------------------------------------
echo [TEST-INFO] Installing vagrant vm. Please, stand by.
echo -----------------------------------------------------
vagrant destroy --force
vagrant up
echo -----------------------------------------------------
echo [TEST-INFO] VM is now running. Start verifying tests.
echo -----------------------------------------------------
cd ..
cd ..
mvn clean
rm -rf reports/
mkdir reports
mvn verify -Dwebdriver.driver=chrome
mv target/report reports/chrome
mvn clean
mvn verify -Dwebdriver.driver=edge
mv target/report reports/edge
mvn clean
mvn verify -Dwebdriver.driver=firefox
mv target/report reports/firefox
mvn clean
echo -----------------------------------------------------
echo [TEST-INFO] Tests passed. Reports placed in /reports.
echo -----------------------------------------------------
cd auto/vagrant
vagrant destroy