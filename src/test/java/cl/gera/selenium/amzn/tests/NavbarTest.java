package cl.gera.selenium.amzn.tests;

import cl.gera.selenium.amzn.components.LocationModal;
import cl.gera.selenium.amzn.core.BaseTest;
import cl.gera.selenium.amzn.components.IcpNavFlyout;
import cl.gera.selenium.amzn.pages.HomePage;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.Locale;

public class NavbarTest extends BaseTest {
    @Test
    public void switchLang() {
        HomePage homePage = new HomePage(getDriver());
        // Switch language
        IcpNavFlyout icpNavFlyout = homePage.navBelt.hoverNavFlyout();
        String currentLang = icpNavFlyout.getCurrentLanguage();
        if (!currentLang.equals("EN"))
            icpNavFlyout.switchLangToEnglish();
        else
            icpNavFlyout.switchLangToSpanish();
    }

    @Test
    public void changeAddress() {
        Faker faker = new Faker(Locale.US);
        HomePage homePage = new HomePage(getDriver());
        // Change location
        LocationModal locationModal = homePage.navBelt.clickNavGlobalLocation();
        locationModal.setZipCode(faker.address().zipCodeByState("CA"));
    }

    @Test
    public void searchProduct() {
        HomePage homePage = new HomePage(getDriver());
        // Search product
        homePage.navBelt.navSearchBar.search("alexa");
    }
}
