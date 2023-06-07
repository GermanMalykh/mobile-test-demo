package browserstack.sample.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackMobileDriver implements WebDriverProvider {

    String username = "germanmalykh_eCmyoJ",
            key = "p5pfiQNqXsytd2TaqStP";

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", username);
        mutableCapabilities.setCapability("browserstack.key", key);

        mutableCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");

        mutableCapabilities.setCapability("device", "Samsung Galaxy S22 Ultra");
        mutableCapabilities.setCapability("os_version", "12.0");

        mutableCapabilities.setCapability("project", "Mobile Test Demo");
        mutableCapabilities.setCapability("build", "browserstack-build-demo");
        mutableCapabilities.setCapability("name", "first_test_demo");

        try {
            return new RemoteWebDriver(
                    new URL("http://hub.browserstack.com/wd/hub"), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
