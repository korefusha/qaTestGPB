package elements;

import org.junit.Test;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class elementsTest {



    @Test
    public void RadioButtonTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver");
        open("https://demoqa.com/");
//        переход на страницу Elements
        $x("//div[@class='category-cards']/a[@href='/elements']").click();
        $$x("//div[@class='element-group']").get(0).shouldBe(exist).click();
        $x("//div[1][@class='element-group']//li[@id='item-2']").click();
        $x("//input[@id='yesRadio']").click();
        $x("//input[@id='impressiveRadio']").click();
        $x("//input[@id='noRadio']").shouldBe(disabled);
        sleep(3000);
    }

    @Test
    public void TextBoxTest(){
//        переменные для теста
        String invalidEmail = "invalidEmail";
        String validEmail = "valid@Email.com";

        String userName = "userNameTest";
        String currentAddress = "currentAddressTest";
        String permanentAddress = "permanentAddressTest";

        open("https://demoqa.com/");
        //переход на страницу Elements
        $x("//div[@class='category-cards']/a[@href='/elements']").click();
        $x("//span[text()='Text Box']").click();
        //проверка почты
        $x("//input[@id='userEmail']").setValue(invalidEmail);
        $x("//button[@id='submit']").scrollTo().click();;
        $x("//input[@class='mr-sm-2 field-error form-control']").shouldBe(visible);
        //Заполнение полей валидными данными
        $x("//input[@id='userName']").setValue(userName);
        $x("//input[@id='userEmail']").setValue(validEmail);
        $x("//textarea[@id='currentAddress']").setValue(currentAddress);
        $x("//textarea[@id='permanentAddress']").setValue(permanentAddress);
        $x("//button[@id='submit']").scrollTo().click();
        sleep(2000);//чтобы можно было наглядно убедиться как работает
        //Проверка соответствия введенных данных и результата
        $x("//div[@id='output']").shouldBe(visible);
        $x("//p[@id='name']").shouldHave(text(userName));
        $x("//p[@id='email']").shouldHave(text(validEmail));
        $x("//p[@id='currentAddress']").shouldHave(text(currentAddress));
        $x("//p[@id='permanentAddress']").shouldHave(text(permanentAddress));
        sleep(3000);
    }

    @Test
    public void CheckBoxTest(){

        open("https://demoqa.com/");
        //        переход на страницу Elements
        $x("//div[@class='category-cards']/a[@href='/elements']").click();
        $x("//span[text()='Check Box']").click();
        $x("//div[@role='treeitem' and .//span[text()='Home']]/span[contains(@class,'rc-tree-switcher')]").click();
        $x("//div[@role='treeitem' and .//span[text()='Documents']]/span[contains(@class,'rc-tree-switcher')]").click();
        $x("//div[@role='treeitem' and .//span[text()='Office']]/span[contains(@class,'rc-tree-switcher')]").click();
        //нажимаем на чекбок
        $x("//div[@role='treeitem' and .//span[text()='Office']]/span[@role='checkbox']").click();
        $x("//div[@id='result']").shouldBe(visible);
        $x("//div[@id='result']").shouldHave(text("office\n" +
                "public\n" +
                "private\n" +
                "classified\n" +
                "general"));
        sleep(3000);
    }
}
