package browserstack.sample.drivers;

import browserstack.sample.config.DeviceConfig;
import browserstack.sample.config.UserConfig;
import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteMobileDriver implements WebDriverProvider {

    static DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class, System.getProperties());
    static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", userConfig.user());
        mutableCapabilities.setCapability("browserstack.key", userConfig.key());

        mutableCapabilities.setCapability("app", deviceConfig.app());

        mutableCapabilities.setCapability("device", deviceConfig.device());
        mutableCapabilities.setCapability("os_version", deviceConfig.os_version());

        mutableCapabilities.setCapability("project", "Mobile Test Demo");
        mutableCapabilities.setCapability("build", "browserstack-build-demo");
        mutableCapabilities.setCapability("name", deviceConfig.device() + "_" + deviceConfig.os_version() + "_test");

        try {
            return new RemoteWebDriver(
                    new URL(userConfig.remoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
