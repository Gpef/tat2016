@echo off
title Wordpress automated testing
cd vagrant
@echo vagrant destroy --force
@echo vagrant up
cd ../..
rmdir /s /q reports
mkdir reports

:: Block provided below can be copied for other browsers
:: Browser can be changed in -Dwebdriver.driver var. See README for supported browsers.

:: Chrome block
cmd /c mvn clean verify -Dwebdriver.driver=chrome
move target\report reports\chrome
cd reports\chrome\
cmd /c index.html
cd ../..
:: Chrome block end

cmd /c mvn clean
cd auto\vagrant
vagrant destroy