package techproed.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.TechproHomePage;
import techproed.pages.TechproLoginPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;

public class Day21_TechproLoginTest {

    //https://testcenter.techproeducation.com/index.php?page=form-authentication
    //Page object Model kullanarak sayfaya giriş yapildigini test edin
    //Sayfadan cikis yap ve cikis yapildigini test et
    //techproed
    //SuperSecretPassword

    //TechproLoginPage
    //userName
    //password
    //submitButton

    //TechproHomePage :
    //homeHeader
    //homeLogoutButton

    //TEST:
    //Class: TechproLoginTest
    //Metot : loginTest()

    //CONFIG READER:
    //techpro_test_url = https://testcenter.techproeducation.com/index.php?page=form-authentication
    //techpro_test_username = techproed
    //techpro_test_password=SuperSecretPassword


    @Test(groups = "regression-tests")
    public void loginTest(){
        TechproLoginPage techproLoginPage = new TechproLoginPage();
        TechproHomePage techproHomePage = new TechproHomePage();

        //SAYFAYA GIT
        Driver.getDriver().get(ConfigReader.getProperty("techpro_test_url"));

        //kullanici adi, sifre ve giris yapma
        techproLoginPage.username.sendKeys(ConfigReader.getProperty("techpro_test_username"));
        techproLoginPage.password.sendKeys(ConfigReader.getProperty("techpro_test_password"));
        techproLoginPage.submitButton.click();

        //home pagede oldugunu dogrulama ve cikis yapma
        Assert.assertTrue(techproHomePage.homeHeader.isDisplayed());
        techproHomePage.homeLogoutButton.click();

        //tekrar logine dondugunu dogrulama
        Assert.assertTrue(techproLoginPage.submitButton.isDisplayed());

        //driveri kapat
        Driver.closeDriver();
    }


}
