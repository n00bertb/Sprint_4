package ru.praktikum.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    WebDriver webDriver;
    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //локатор для списка вопросов-ответов
    private By ListOfQuestions = By.className("Home_FAQ__3uVm4");
    //локатор верхней кнопки Заказать, та что в хедере
    private By UpOrderButton = By.xpath(".//*[@class='Header_Nav__AGCXC']/*[text()='Заказать']");
    //локатор нижней кнопки Заказать
    private By DownOrderButton = By.xpath(".//*[@class='Home_FinishButton__1_cWm']/*[text()='Заказать']");
    //локатор кнопки подтверждения кук, если их не принять то в фаерфоксе будут падать тесты
    private By cookieConfirmButton = By.xpath(".//*[text()='да все привыкли']");
    //метод скролит к блоку с вопросами
    public void scrollToListOfQuestions() {
        WebElement element = webDriver.findElement(ListOfQuestions);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    //метод кликает по нужному вопросу
    public void clickOnQuestion(int index) {
        webDriver.findElement(By.id(String.format("accordion__heading-%d",index))).click();
    }
    //метод забирает текст ответа
    public String getAnswerText(int index) {
        return webDriver.findElement(By.id(String.format("accordion__panel-%d",index))).getText();
    }
    //метод нажимает верхнюю кнопку Заказать
    public void clickOrderButton(boolean button) {
        if (button){
            webDriver.findElement(UpOrderButton).click();
        }else{
            webDriver.findElement(DownOrderButton).click();
        }
    }
    //метож жмет кнопку принятия кук
    public void cookieAgreeButtonClick() {
        webDriver.findElement(cookieConfirmButton).click();
    }
}
