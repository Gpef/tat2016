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
cmd /c mvn verify -Dwebdriver.driver=chrome
echo -----------------------------------------------------
echo [TEST-INFO] Tests passed. Opening report
echo -----------------------------------------------------
cmd /c target\report\index.html
cd auto\vagrant
vagrant destroy