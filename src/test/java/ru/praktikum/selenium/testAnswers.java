package ru.praktikum.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.selenium.pageobjects.MainPage;

import java.util.concurrent.TimeUnit;

import static ru.praktikum.selenium.config.AppConfig.APP_URL;

@RunWith(Parameterized.class)
public class testAnswers {
    private final int index;
    private final String text;
    WebDriver webDriver;
    MainPage mainPage;

    public testAnswers(String text,int index) {
        this.index = index;
        this.text = text;
    }
    //сделал это тест параметризованным т.к. у нас тут заранее известный набор входных параметров
    //опять таки чтобы не писать отдельно тест на каждый вопрос-ответ
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                { "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                { "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                { "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                { "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                { "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                { "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                { "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
        };
    }
    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkAnswers() {
        mainPage = new MainPage(webDriver);
        webDriver.get(APP_URL);
        mainPage.scrollToListOfQuestions();
        mainPage.clickOnQuestion(index);
        String answerText = mainPage.getAnswerText(index);//тут записываем в переменную ответ на вопрос
        Assert.assertEquals("Не верный ответ на вопрос",text,answerText);//тут сравниваем ожидаемый ответ на вопрос с полученным ответом
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}
