### Enviroment

To run autotests next apps need to be installed:
* Vagrant (with Oracle VirtualBox)
* Maven (path to mvn\bin mast be in system property PATH)
* JDK 1.8
* Webdrivers for browsers (see: [Before run](#before-run))

### Properties

Serenity framework properties can be set up in `serenity.properties`. 
For more information read
<a href="http://thucydides.info/docs/serenity/#_serenity_system_properties_and_configuration" title="Serenity properties and configuration">official guide</a>.

### Before run

Specify path to drivers in `src/test/resources/test.properties` or in system environment variables.

### Running
Run maven goal `verify` or one of the next scripts: 
* Windows: execute `auto/win_run.exe`
* Unix-based: run `auto/unix_run.sh`

To run tests in any browsers, put it's name and path to driver executable into `src/test/resources/test.properties` and
run in command line `mvn verify -Dwebdriver.driver=${drivername}`, for example, properties file contains
`webdriver.chrome.driver=auto\\drivers\\win\\chromedriver.exe` and you can run tests with command 
`mvn verify -Dwebdriver.driver=chrome`.

### Report 
* After running `mvn verify` automatically generated report can be opened by `target/report/index.html`.
Examples can be find in `img/` directory.