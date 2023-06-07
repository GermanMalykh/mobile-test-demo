package browserstack.sample.drivers;

import browserstack.sample.config.DeviceConfig;
import browserstack.sample.config.UserConfig;
import browserstack.sample.helpers.WikiApkDownloader;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalMobileDriver implements WebDriverProvider {

    WikiApkDownloader wikiApkDownloader = new WikiApkDownloader();
    static DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class, System.getProperties());
    static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL(userConfig.localUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setLocale(deviceConfig.locale())
                .setLanguage(deviceConfig.language())
                .setDeviceName(deviceConfig.device())
                .setPlatformVersion(deviceConfig.os_version())
                .setApp(wikiApkDownloader.getAppPath())
                .setAppPackage(deviceConfig.appPackage())
                .setAppActivity(deviceConfig.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

}
