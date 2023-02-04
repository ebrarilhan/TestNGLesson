package techproed.tests.excelautomation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalHomePage;
import techproed.pages.BlueRentalLoginPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ExcelUtils;
import techproed.utilities.ReusableMethods;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Day23_ExcelLogin {

    BlueRentalHomePage blueRentalHomePage;
    BlueRentalLoginPage blueRentalLoginPage;
    ExcelUtils excelUtils;
    List<Map<String, String>> excelDatalari;

    //bu method login sayfasina gitmek icin kullanilacak
    public void login(){
        //sayfaya git
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        //homepage logine tikla
        blueRentalHomePage = new BlueRentalHomePage();
        blueRentalLoginPage = new BlueRentalLoginPage();

        //-----SADECE ILK GIRIS-----
        // loginLink methodu sadece ilk giriste sayfada goruur
        // ikinci ve ucuncu girislerde sayfada gorunmeyeceginden noSuchElementException alinir
        // biz bu exceptioni try catch kullanarak yakalariz ve test cases calismaya devam eder.
        try {
            blueRentalHomePage.loginLink.click();
            ReusableMethods.waitFor(1); //bir saniye bekle
        }catch (Exception e){

        }

        // ----------sonraki girisler:

        try {
            //kullanici idye tikla
            blueRentalHomePage.userID.click();
            ReusableMethods.waitFor(1);
            //logout linkine tikla
            blueRentalHomePage.logOutLink.click();
            ReusableMethods.waitFor(1);
            //OK'e tikla
            blueRentalHomePage.OK.click();
            ReusableMethods.waitFor(1);
            //homepage logine tikla
            blueRentalHomePage.loginLink.click();
            ReusableMethods.waitFor(1);
        }catch (Exception e){

        }

    }

    @Test
    public void customerLogin() throws IOException {
        String path = "./src/test/java/resources/mysmoketestdata.xlsx";
        //pathi content roottan kopyalayip basina ./ koyduk: bu, onceki diger tum dosyalari gorme anlamindadir. RELATIVE PATH
        String sayfa = "customer_info"; //sheet ismini aldik
        //datalari excel utils methodlarini kullanarak buraya alacagiz
        excelUtils = new ExcelUtils(path,sayfa);
        //excel datalarini getDataList methodu ile cekelim
        excelDatalari = excelUtils.getDataList();
        //loop kullanarak datalari tek tek test casede kullan
        for (Map<String, String> data : excelDatalari){
            login(); //her loopta login sayfasina goturecek
            //kullanici adini girme
            ReusableMethods.waitFor(1);
            blueRentalLoginPage.emailBox.sendKeys(data.get("username"));
            //kullanici sifresini girme
            ReusableMethods.waitFor(1);
            blueRentalLoginPage.passwordBox.sendKeys(data.get("password"));
            //login butonuna tikla
            ReusableMethods.waitFor(1);
            blueRentalLoginPage.loginButton.click();
            ReusableMethods.waitFor(1);
            // giris isleminin basarili oldugunu gostermek icin ASSERTION yaptik
            // bunun icin hazir methodumuz var
            ReusableMethods.verifyElementDisplayed(blueRentalHomePage.userID);
            ReusableMethods.waitFor(1);
            //her bir giristen sonra ekran goruntusu aldik
            ReusableMethods.getScreenshot("EkranGoruntusu");
        }
    }


    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
}



/*
sam.walker@bluerentalcars.com	c!fas_art
kate.brown@bluerentalcars.com	tad1$Fas
raj.khan@bluerentalcars.com	v7Hg_va^
pam.raymond@bluerentalcars.com	Nga^g6!


------ILK GIRIS---------
HOME PAGE DEYIZ
home page logine tikla --->>> try catch
LOGIN PAGE DEYIZ
kullanici adini gir(excelden al)
kullanici sifresini git(excelden al)
login page login buttonuna tikla

------2. GIRIS-----
HOME PAGE DEYIZ
kullanici ID ye tikla      --->>> try catch
Logout linkine tikla       --->>> try catch
OK e tikla                --->>> try catch
home page logine tikla    --->>> try catch
LOGIN PAGE DEYIZ
kullanici adini gir(excelden al)
kullanici sifresini git(excelden al)
login page login buttonuna tikla

------3. GIRIS---------
HOME PAGE DEYIZ
kullanici ID ye tikla
Logout linkine tikla
OK e tikla
home page logine tikla
LOGIN PAGE DEYIZ
kullanici adini gir(excelden al)
kullanici sifresini git(excelden al)
login page login buttonuna tikla

---------4. GIRIS------------
HOME PAGE DEYIZ
kullanici ID ye tikla
Logout linkine tikla
OK e tikla
home page logine tikla
LOGIN PAGE DEYIZ
kullanici adini gir(excelden al)
kullanici sifresini git(excelden al)
login page login buttonuna tikla

 */