To run autotests next apps need to be installed:
* Vagrant (with Oracle VirtualBox)
* Maven (path to mvn\bin mast be in system property PATH)
* JDK 1.8

### Properties

Serenity framework properties can be set up in `serenity.properties`. 
For more information read
<a href="http://thucydides.info/docs/serenity/#_serenity_system_properties_and_configuration" title="Serenity properties and configuration">official guide</a>.

Drivers stored by OS folders at `auto/drivers/`. Paths can be changed in `test.properties`.

All test properties stored in `src/test/resources/test.properties`

### Running
Run maven goal `verify` or one of the next scripts: 
* Windows: execute `auto/win_run.exe`
* Unix-based: run `auto/unix_run.sh`

### Report 
After running `mvn verify` automatically generated report can be opened by `target/report/index.html`.
Examples can be find in `img/` directory.