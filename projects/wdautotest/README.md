### Environment

To run autotests next apps need to be installed:
* Vagrant (with Oracle VirtualBox)
* Maven (path to mvn\bin mast be in system property PATH)
* JDK 1.8
* Webdrivers for [supported](#supported-browsers) browsers (see: [Before run](#before-run))

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
`webdriver.chrome.driver=drivers\\chromedriver.exe` and you can run tests with command 
`mvn verify -Dwebdriver.driver=chrome`.


### Supported browsers
* Chrome
* Microsoft Edge
* Firefox
* Opera
You can found example drivers binaries in `/drivers` package.

### Report  
After running `mvn verify` automatically generated report can be opened by `target/report/index.html`. Automated 
tests script contains example of how to move this reports in folder specified for browser.
Examples can be find in `img/` directory.