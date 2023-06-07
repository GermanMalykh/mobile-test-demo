package browserstack.sample.drivers;

import browserstack.sample.config.MobileConfig;
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

public class BrowserStackMobileDriver implements WebDriverProvider {

    static MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
    static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", userConfig.user());
        mutableCapabilities.setCapability("browserstack.key", userConfig.key());

        mutableCapabilities.setCapability("app", mobileConfig.app());
        mutableCapabilities.setCapability("device", mobileConfig.device());
        mutableCapabilities.setCapability("os_version", mobileConfig.os_version());

        mutableCapabilities.setCapability("project", "Mobile Test Demo");
        mutableCapabilities.setCapability("build", "browserstack-build-demo");
        mutableCapabilities.setCapability("name", mobileConfig.device() + "_" + mobileConfig.os_version() + "_test");

        try {
            return new RemoteWebDriver(
                    new URL(userConfig.remoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
