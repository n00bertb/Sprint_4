package ru.praktikum.selenium;

import com.sun.tools.javac.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.selenium.pageobjects.MainPage;
import ru.praktikum.selenium.pageobjects.OrderPage;

import java.util.concurrent.TimeUnit;

import static ru.praktikum.selenium.config.AppConfig.APP_URL;

@RunWith(Parameterized.class)
public class TestOrder {
    private final String name;
    private final String secondname;
    private final String addres;
    private final String metro;
    private final String telefon;
    private final String date;
    private final String arendatime;
    private final String colour;
    private final String coment;
    private final boolean button;
    WebDriver webDriver;
    MainPage mainPage;
    OrderPage orderPage;

    public TestOrder(String name, String secondname, String addres, String metro, String telefon, String date, String arendatime, String colour, String coment, boolean button) {
        this.name = name;
        this.secondname = secondname;
        this.addres = addres;
        this.metro = metro;
        this.telefon = telefon;
        this.date = date;
        this.arendatime = arendatime;
        this.colour = colour;
        this.coment = coment;
        this.button = button;
    }
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { "Антуан","Градухин","Какаято улица Какойто дом","Лубянка","88005553535","23.06.23","трое суток","серая безысходность","Тест",true},
                { "Артем","Самусенко","Митинская ул. д.38 корп.1","Митино","88005553535","29.07.23","сутки","чёрный жемчуг","Желательно в первой половине дня",false},
        };
    }
    @Before
    public void init() {
        //Раскоментировать для проверки в фаерфоксе, закоментировав хром
        //WebDriverManager.firefoxdriver().clearDriverCache().setup();
        //webDriver = new FirefoxDriver();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void checkOrderButton() {
        mainPage = new MainPage(webDriver);
        webDriver.get(APP_URL);
        mainPage.cookieAgreeButtonClick();
        mainPage.clickOrderButton(button);
        orderPage = new OrderPage(webDriver);
        orderPage.fillFistOrderForm(name,secondname,addres,metro,telefon);
        orderPage.fillSecondOrderForm(date,arendatime,colour,coment);
        orderPage.clickYesButton();
        orderPage.checkOrderIsConfirm();
    }
    @After
    public void closeBrowser() {
        webDriver.quit();
    }

}
