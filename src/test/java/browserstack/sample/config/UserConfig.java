package browserstack.sample.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:user.properties")
public interface UserConfig extends Config {

    @Key("browserstack.user")
    @DefaultValue("germanmalykh_eCmyoJ")
    String user();

    @Key("browserstack.key")
    @DefaultValue("p5pfiQNqXsytd2TaqStP")
    String key();

    @Key("remoteUrl")
    @DefaultValue("http://hub.browserstack.com/wd/hub")
    String remoteUrl();

    @Key("localUrl")
    @DefaultValue("http://localhost:4723/wd/hub")
    String localUrl();

}
