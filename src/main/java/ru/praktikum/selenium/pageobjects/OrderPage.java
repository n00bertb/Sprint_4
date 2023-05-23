package ru.praktikum.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;

public class OrderPage {
    WebDriver webDriver;

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //локатор для инпута имени
    private By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    //локатор для инпута фамилии
    private By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор для адреса доставки
    private By adressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    //локатор для дропбокса станций метро
    private By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор для импута номера телефона
    private By telephoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки Далее из первой формы заказа
    private By continueButton = By.cssSelector(".Order_NextButton__1_rCA .Button_Button__ra12g");
    //локатор инпута указания даты заказа
    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор инпута коментария для курьера
    private By сomentDelivery = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор дропбокса длительности аренды самоката
    private By dropBox = By.className("Dropdown-arrow");
    //локатор кнопки Заказать со второй формы заказа
    private By lastOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/*[text()='Заказать']");
    //локатор кнопки "Да" подтверждения заказа
    private By YesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/*[text()='Да']");
    //локатор текста во всплывающем окне Заказ оформлен, удачное оформление заказа
    private By OrderСonfirmWindow = By.xpath(".//*[text()='Заказ оформлен']");
    //метод записывает имя в инпут имени
    public void writeName(String name) {
        webDriver.findElement(nameInput).sendKeys(name);
    }
    //метод записывает фамилию в инпут фамилии
    public void writeSurname(String surname) {
        webDriver.findElement(surnameInput).sendKeys(surname);
    }
    //метод записывает адрес в инпут адреса
    public void writeAdress(String adress) {
        webDriver.findElement(adressInput).sendKeys(adress);
    }
    //метод ищет дропбокс станций метро кликает по нему, и далее кликает по нужной станции метро
    public void writeMetro(String metro) {
        webDriver.findElement(metroInput).click();
        webDriver.findElement(By.xpath(String.format("//*[text()='%s']",metro))).click();
    }
    //метод записывает номер телефона в инпут телефона
    public void writeTelephone(String telephone) {
        webDriver.findElement(telephoneInput).sendKeys(telephone);
    }
    //метод жмет кнопку Далее в перфой форме заказа
    public void clickOnContinueButton() {
        webDriver.findElement(continueButton).click();
    }
    //метод записывает желаемую дату доставки
    public void writeDateInput(String date) {
        webDriver.findElement(dateInput).sendKeys(date);
    }
    //метод ищет дропбокс длительности аренды самоката кликает в него и выбирает желаемое время аренды
    public void getArendaTime(String time) {
        webDriver.findElement(dropBox).click();
        webDriver.findElement(By.xpath(String.format("//*[text()='%s']",time))).click();
    }
    //метод выбирает цвет самоката
    public void getColour(String colour) {
        webDriver.findElement(By.xpath(String.format("//*[text()='%s']",colour))).click();
    }
    //метод записывает коментарий курьеру
    public void writeComentDelivery(String coment) {
        webDriver.findElement(сomentDelivery).sendKeys(coment);
    }
    //метод жмет кнопку Заказать во второй форме заказа
    public void clickLastOrderButton() {
        webDriver.findElement(lastOrderButton).click();
    }
    //метод жмет кнопку Да для подтверждения заказа
    public void clickYesButton() {
        webDriver.findElement(YesButton).click();
    }
    //Метод проверки что заказ успешен
    public void checkOrderIsConfirm() {
        var isDisplayed=  webDriver.findElement(OrderСonfirmWindow).isDisplayed();
        assertTrue("Заказ не зарегистрирован",isDisplayed);
    }
    //Чейн метод заполнения первой формы заказа
    public void fillFistOrderForm(String name,String secondname,String addres,String metro,String telefon) {
        writeName(name);
        writeSurname(secondname);
        writeAdress(addres);
        writeMetro(metro);
        writeTelephone(telefon);
        clickOnContinueButton();
    }
    //Чейн метод заполнения второй формы заказа
    public void fillSecondOrderForm(String date,String arendatime,String colour,String coment) {
        writeDateInput(date);
        getArendaTime(arendatime);
        getColour(colour);
        writeComentDelivery(coment);
        clickLastOrderButton();
    }

}
