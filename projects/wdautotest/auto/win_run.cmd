@echo off
title Automated testing
cd vagrant
echo -----------------------------------------------------
echo [TEST-INFO] Installing vagrant vm. Please, stand by.
echo -----------------------------------------------------
vagrant destroy --force
vagrant up
echo -----------------------------------------------------
echo [TEST-INFO] VM is now running. Start verifying tests.
echo -----------------------------------------------------
cd..
cd..
cmd /c mvn clean
rmdir /s /q reports
mkdir reports
cmd /c mvn verify -Dwebdriver.driver=chrome
move target\report reports\chrome
cmd /c mvn clean
cmd /c mvn verify -Dwebdriver.driver=edge
move target\report reports\edge
cmd /c mvn clean
cmd /c mvn verify -Dwebdriver.driver=firefox
move target\report reports\firefox
cmd /c mvn clean
echo -----------------------------------------------------
echo [TEST-INFO] Tests passed. Reports placed in /reports.
echo -----------------------------------------------------
cd auto\vagrant
vagrant destroy