package browserstack.sample.tests;

import browserstack.sample.drivers.LocalMobileDriver;
import browserstack.sample.drivers.RemoteMobileDriver;
import browserstack.sample.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestBase {

    static String deviceHost = System.getProperty("deviceHost", "remote_android");

    @BeforeAll
    public static void setup() throws Exception {
        switch (deviceHost) {
            case "remote_android":
            case "remote_ios":
                Configuration.browser = RemoteMobileDriver.class.getName();
                break;
            case "emulation_android":
            case "real_android":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            default:
                throw new Exception("Unrecognised deviceHost");
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = sessionId().toString();
        if (deviceHost.equals("emulation_android") || deviceHost.equals("real_android")) {
            Attach.screenshotAs("Last screenshot");
        }
        Attach.pageSource();
        closeWebDriver();
        if (deviceHost.equals("remote_android") || deviceHost.equals("remote_ios")) {
            Attach.addVideo(sessionId);
        }
    }
}
