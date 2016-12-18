@echo off
title Wordpress automated testing
cd vagrant
@echo vagrant destroy --force
@3cho vagrant up
cd ../..
rmdir /s /q reports
mkdir reports

cmd /c mvn clean verify -Dwebdriver.driver=chrome
move target\report reports\chrome
cd reports\chrome\
cmd /c index.html
cd ../..

cmd /c mvn clean
cd auto\vagrant
vagrant destroy