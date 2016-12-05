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
mvn verify -Dwebdriver.driver=chrome
mvn verify -Dwebdriver.driver=edge
echo -----------------------------------------------------
echo [TEST-INFO] Tests passed. Opening report
echo -----------------------------------------------------
cd auto/vagrant
vagrant destroy
