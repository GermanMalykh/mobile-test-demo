package browserstack.sample.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.grid.config.ConfigException;

public enum ConfigReader {
    Instance;

    private MobileConfig mobileConfig;
    private UserConfig userConfig;

    public MobileConfig read1() {
        if (mobileConfig == null) {
            try {
                mobileConfig = ConfigFactory.create(
                        MobileConfig.class,
                        System.getProperties()
                );
            } catch (ConfigException e) {
            }
        }
        return mobileConfig;
    }

    public UserConfig read2() {
        if (userConfig == null) {
            try {
                userConfig = ConfigFactory.create(
                        UserConfig.class,
                        System.getProperties()
                );
            } catch (ConfigException e) {
            }
        }
        return userConfig;
    }
}
