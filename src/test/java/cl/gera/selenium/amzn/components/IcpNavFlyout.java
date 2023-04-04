package cl.gera.selenium.amzn.components;

import cl.gera.selenium.amzn.core.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IcpNavFlyout extends BaseComponent {
    @FindBy(xpath = "//a[@id='icp-nav-flyout']//div")
    WebElement currentLanguage;
    @FindBy(xpath = "//div[@id='nav-flyout-icp']//a[@href='#switch-lang=en_US']")
    WebElement englishOption;
    @FindBy(xpath = "//div[@id='nav-flyout-icp']//a[@href='#switch-lang=es_US']")
    WebElement spanishOption;

    public IcpNavFlyout(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCurrentLanguage() {
        return this.currentLanguage.getText();
    }

    public void switchLangToSpanish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(spanishOption));
        spanishOption.click();
    }

    public void switchLangToEnglish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(englishOption));
        englishOption.click();
    }
}
