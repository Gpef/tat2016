cd vagrant
@echo vagrant destroy --force
@echo vagrant up
cd ../..
rm -rf reports/
mkdir reports

# Block provided below can be copied for other browsers
# Browser can be changed in -Dwebdriver.driver var. See README for supported browsers.

# Chrome block
mvn clean verify -Dwebdriver.driver=chrome
mv target\report reports\chrome
cd reports\chrome\
cd ../..
# Chrome block end

mvn clean
cd auto/vagrant
vagrant destroy