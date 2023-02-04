package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.utilities.Driver;

public class BlueRentalLoginPage {

    public BlueRentalLoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "formBasicEmail")
    public WebElement emailBox;

    @FindBy(id = "formBasicPassword")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement error_message_1;

    @FindBy(xpath = "//*[@class='Toastify__toast-body']")
    public WebElement error_message_2; //Bad credentials

    @FindBy(xpath = "(//*[@class='invalid-feedback'])[1]")
    public WebElement error_message_3; //email must be a valid email

}
