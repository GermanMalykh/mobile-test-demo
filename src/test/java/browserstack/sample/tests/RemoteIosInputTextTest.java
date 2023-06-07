package browserstack.sample.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tags(value = {@Tag("ios"), @Tag("remote")})
@DisplayName("Browserstack test")
public class RemoteIosInputTextTest extends TestBase {

    @DisplayName("Тест на ввод текста")
    @Test
    void successfulWikiSearchTest() {
        step("Переходим к вводу текста в поле", () -> {
            $(id("Text Button")).click();
        });
        step("Указываем параметры ввода", () -> {
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys("hello@browserstack.com");
            $(id("Text Input")).pressEnter();
        });
        step("Проверяем, что поле содержит введенный текст", () -> {
            $(id("Text Output")).shouldHave(Condition.text("hello@browserstack.com"));
        });
    }
}
