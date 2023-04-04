package cl.gera.selenium.amzn.components;

import cl.gera.selenium.amzn.core.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocationModal extends BaseComponent {
    @FindBy(xpath = "//div[@id='a-popover-1']//div[@id='GLUXSpecifyLocationDiv']//input[@id='GLUXZipUpdateInput']")
    WebElement zipCodeInput;
    @FindBy(xpath = "//div[@id='a-popover-1']//div[@id='GLUXSpecifyLocationDiv']//input[@type='submit']")
    WebElement updateZipCodeButton;
    @FindBy(xpath = "//div[@id='a-popover-1']//div[@class='a-popover-footer']//input[@id='GLUXConfirmClose']")
    WebElement closeModal;

    public LocationModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setZipCode(String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(zipCodeInput));
        zipCodeInput.sendKeys(zipCode);
        updateZipCodeButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(closeModal));
        closeModal.click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("html"))));
    }
}
