cd vagrant
vagrant destroy --force
vagrant up
cd ../..
rm -rf reports/
mkdir reports

mvn clean verify -Dwebdriver.driver=chrome
mv target\report reports\chrome
cd reports\chrome\
index.html
cd ../..

mvn clean
cd auto/vagrant
vagrant destroy