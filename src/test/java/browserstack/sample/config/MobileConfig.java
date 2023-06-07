package browserstack.sample.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface MobileConfig extends Config {

    @Key("app")
    String app();

    @Key("device")
    String device();

    @Key("os_version")
    String os_version();

}
