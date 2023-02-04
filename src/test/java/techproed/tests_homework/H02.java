package techproed.tests_homework;

import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalHomePage;
import techproed.pages.BlueRentalLoginPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;

public class H02 {

    //Name:
    //US101122_Negative_Login
    //Description:
    //Geçerli email uzantısı olmadan kullanıcı girişi yapılamamalı
    //Acceptance Criteria:
    //Kullanici geçersiz email uzantısı yazdiginda, hata mesajini almalı
    //Error Message: email must be a valid email
    //Dogru email uzantisi girildiğinde hata mesajı alınmamalı
    //https://email-verify.my-addr.com/list-of-most-popular-email-domains.php

    BlueRentalHomePage blueRentalHomePage;
    BlueRentalLoginPage blueRentalLoginPage;

    @Test
    public void US101122_Negative_Login(){
        blueRentalLoginPage = new BlueRentalLoginPage();
        blueRentalHomePage = new BlueRentalHomePage();
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        blueRentalHomePage.loginLink.click();
        blueRentalLoginPage.emailBox.sendKeys(ConfigReader.getProperty("fake_sifre"));
        Assert.assertTrue(blueRentalLoginPage.error_message_3.isDisplayed());
        ReusableMethods.waitFor(4);
        blueRentalLoginPage.emailBox.clear();
        blueRentalLoginPage.emailBox.sendKeys(ConfigReader.getProperty("admin_email"));
        Assert.assertFalse(blueRentalLoginPage.error_message_3.isDisplayed());
        Driver.closeDriver();
    }
}
