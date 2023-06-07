package browserstack.sample.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("android")
@DisplayName("Browserstack test")
public class AndroidSearchWikiTest extends TestBase {

    @DisplayName("Тест на поиск")
    @Test
    void successfulWikiSearchTest() {
        step("Переходим к поиску", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });
        step("Указываем параметры поиска", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Проверяем, что результаты поиска не равны нулю", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0));
        });
    }
}
