### Enviroment

To run autotests next apps need to be installed:
* Vagrant (with Oracle VirtualBox)
* Maven (path to mvn\bin mast be in system property PATH)
* JDK 1.8

### Properties

Serenity framework properties can be set up in `serenity.properties`. 
For more information read
<a href="http://thucydides.info/docs/serenity/#_serenity_system_properties_and_configuration" title="Serenity properties and configuration">official guide</a>.

Drivers stored in folders filtered by OS at `auto/drivers/`. Paths can be changed in `test.properties`.

All test properties stored in `src/test/resources/test.properties`.

To run tests in any browsers, put it's name and path to driver executable into `test.properties` and
run in command line `mvn verify -Dwebdriver.driver=${drivername}`, for example, properties file contains
`webdriver.chrome.driver=auto\\drivers\\win\\chromedriver.exe` and you can run tests with command 
`mvn verify -Dwebdriver.driver=chrome`.

### Running
Run maven goal `verify` or one of the next scripts: 
* Windows: execute `auto/win_run.exe`
* Unix-based: run `auto/unix_run.sh`

### Report 
* After running `mvn verify` automatically generated report can be opened by `target/report/index.html`.
Examples can be find in `img/` directory.
* After running automated script, new vm with wordpress will be installed, All test results put into /reports/folder
