package elements;

import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class elementsTest {

    @Test
    public void RadioButtonTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver");
        open("https://demoqa.com/");
        $x("//div[@class='category-cards']/a[@href='/elements']").click();
        $x("//div[1][@class='element-group']").shouldBe(exist).click();
//        System.out.println("1й элемент найден");
        $x("//div[1][@class='element-group']//li[@id='item-2']").click();
//        System.out.println("2й элемент найден");
        $x("//input[@id='yesRadio']").click();
        $x("//input[@id='impressiveRadio']").click();
        $x("//input[@id='noRadio']").shouldBe(disabled);
        sleep(5000);
    }
}
