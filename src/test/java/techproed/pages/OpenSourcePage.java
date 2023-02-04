package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.utilities.Driver;

public class OpenSourcePage {

    // Page objelerini bu sinifta buluruz.
    // 1. constructor

    public OpenSourcePage(){
        // PageFactory seleniumdan gelen ve bu sayfa elementlerini baslangic degeri vermek icin kullanilir.
        // Bu sekilde sayfa objeleri cagrildiginda nullpointer exception alinmaz
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //PAGE OBJELERINI OLUSTUR
    // GELENEKSEL  :  public WebElement username= Driver.getDriver().findElement(By.name("username"));

    @FindBy(name = "username")
    public WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

}
