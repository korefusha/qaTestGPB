import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeTest {

//    @Test

    public void testGoogleSearch() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            String path = "https://www.gazprombank.ru/";
            driver.get(path);
            Thread.sleep(1000);
            
            //2) Перейти на страницу «Кредиты» -> «Кредит наличными»
            // Кредиты - //*[contains(@class,'header_full_menu_item_menu_item' )]
            WebElement creditHref = driver.findElement(By.xpath("//div[@role='menuitem']/div[contains(text(), 'Кредиты')]"));
            creditHref.click();
            Thread.sleep(1000);  // Let the user actually see something!

            //«Кредит наличными» //a[contains(text(),'Кредит наличными')]
            WebElement creditAndCashHref = driver.findElement(By.xpath("//a[contains(text(),'Кредит наличными')]"));
            creditAndCashHref.click();
            Thread.sleep(1000);

            //3)Проскролить страницу до «Калькулятор расчета по кредиту»
            WebElement calculatorBlock = driver.findElement(By.xpath("//h2[contains(text(), 'Калькулятор расчета по кредиту наличными')]"));
            WebElement checkBoxSalaryClientGPB = driver.findElement(By.xpath("//div[contains(text(), 'Расчет предварительный, произведен с учетом личного страхования.')]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", calculatorBlock);
            assertTrue(calculatorBlock.isDisplayed());
            assertTrue(checkBoxSalaryClientGPB.isDisplayed());
            Thread.sleep(1000);

        /*4) Ввести сумму кредита меньше 5 миллионов
        "Сумма кредита"   //div[@class='nr-range js-nr-range']/input
        ввести 4999999*/
            WebElement creditSumRange = driver.findElement(By.xpath("//div[@class='nr-calculator__fields']//div[@class='nr-range js-nr-range'][1]//div[@class='nr-range__bar--pin js-nr-range-pin']"));
            if (creditSumRange.isDisplayed()) {
                new Actions(driver).dragAndDropBy(creditSumRange, -100, 0).perform();
                Thread.sleep(1000);
            }
            //5) Передвинуть(!) ползунок «Срок кредита»
            WebElement creditTimeTextField = driver.findElement(By.xpath("//div[@class='nr-range js-nr-range'][2]//input[1]"));
            WebElement creditTimeRange = driver.findElement(By.xpath("//div[@class='nr-calculator__fields']//div[@class='nr-range js-nr-range'][2]//div[@class='nr-range__bar--pin js-nr-range-pin']"));
            if (creditTimeRange.isDisplayed()){
                new Actions(driver).dragAndDropBy(creditTimeRange, -100, 0).perform();
                Thread.sleep(1000);
            }

//            6) Снять галочку с чекбокса «Зарплатный клиент Газпромбанка»
            WebElement checkBoxGPBclient = driver.findElement(By.xpath("//div[@*='nr-calculator__fields']//*[@*='nr-checkbox__check']"));
            if (checkBoxGPBclient.isDisplayed()) {
                checkBoxGPBclient.click();
                Thread.sleep(1000);
                System.out.println("checkBoxGPBclient had been clicked");
            } else {
                System.out.println("checkBoxGPBclient isn't displayed");
            }
//             7) Проверить, что значение «Ваша ставка» равно 6.9%
            WebElement youCreditRate = driver.findElement(By.xpath("//span[@class='js-nr-calc-rate']"));
            if (youCreditRate.isDisplayed()) {
                assertTrue(youCreditRate.getText().equals("6.9"));
                System.out.println("Credit rate is " + youCreditRate.getText());
                Thread.sleep(1000);
            }
            // 8 ) Проверить, что значение «Ежемесячный платеж» больше 0 и имеет знак валюты ₽
            WebElement mounthPayment = driver.findElement(By.xpath("//*[@class='js-nr-calc-monthPayment']"));
            WebElement rubleSymbol = driver.findElement(By.xpath("//div[@class='nr-calculator__result-item'][2]//div[@class='nr-calculator__result-item--value']"));
            int mounthPaymentint = Integer.parseInt(mounthPayment.getText().replaceAll(" ", ""));
            assertTrue(mounthPaymentint>0);
            String rubSymbol = rubleSymbol.getText().substring(rubleSymbol.getText().length()-1);
            System.out.println("Symbol is + " + rubSymbol);
            assertTrue(rubSymbol.equals("₽"));
            Thread.sleep(1000);  // Let the user actually see something!
            //(Часть локаторов составить используя css, часть xpath)
        }
        catch (Exception e){
            System.out.println("ERROR - " + e.getMessage());
        }
        finally{
            driver.quit();
            System.out.println("Test done. All good.");
        }
    }

    public static void main(String[] args) throws Exception{
        new ChromeTest().testGoogleSearch();
    }

}
