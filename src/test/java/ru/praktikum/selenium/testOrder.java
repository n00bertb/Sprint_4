package ru.praktikum.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.selenium.pageobjects.MainPage;
import ru.praktikum.selenium.pageobjects.OrderPage;

import java.util.concurrent.TimeUnit;

import static ru.praktikum.selenium.config.AppConfig.APP_URL;

public class testOrder {
    WebDriver webDriver;
    MainPage mainPage;
    OrderPage orderPage;

    @Before
    public void init() {
        //Раскоментировать для проверки в фаерфоксе, закоментировав хром
        //WebDriverManager.firefoxdriver().clearDriverCache().setup();
        //webDriver = new FirefoxDriver();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    //Тут два теста один проверяет возможность заказа по кнопке в хедере, второй проверяет возможность заказа по кнопке заказа внизу.
    //Просили два набора данных, собственно для разных кнопок у меня свой набор данных
    //Тесты проходят в Firefox, но валятся в Chrome на моменте нажатия кнопки Да в форме подтверждения заказа.
    //Мое мнение что параметризованный тест тут лишний, если именно это имелось под проверками двумя разными наборами данных
    @Test
    public void checkUpOrderButton() {
        mainPage = new MainPage(webDriver);
        webDriver.get(APP_URL);
        mainPage.cookieAgreeButtonClick();
        mainPage.clickUpOrderButton();

        orderPage = new OrderPage(webDriver);
        orderPage.fillFistOrderForm("Антуан","Градухин","Какаято улица Какойто дом","Лубянка","88005553535");
        orderPage.fillSecondOrderForm("23.06.23","трое суток","серая безысходность","Тест");

        orderPage.clickYesButton();
        orderPage.checkOrderIsConfirm();
    }
    @Test
    public void checkDownOrderButton() {
        mainPage = new MainPage(webDriver);
        webDriver.get(APP_URL);
        mainPage.cookieAgreeButtonClick();
        mainPage.clickDownOrderButton();

        orderPage = new OrderPage(webDriver);
        orderPage.fillFistOrderForm("Артем","Самусенко","Митинская ул. д.38 корп.1","Митино","88005553535");
        orderPage.fillSecondOrderForm("29.07.23","сутки","чёрный жемчуг","Желательно в первой половине дня");

        orderPage.clickYesButton();
        orderPage.checkOrderIsConfirm();
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }

}
