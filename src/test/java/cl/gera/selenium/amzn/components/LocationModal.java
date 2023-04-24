package cl.gera.selenium.amzn.components;

import cl.gera.selenium.amzn.core.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocationModal extends BaseComponent {
    private final String zipConfirmationSectionId = "GLUXZipConfirmationSection";
    @FindBy(xpath = "//div[@id='a-popover-1']//div[@id='GLUXSpecifyLocationDiv']//input[@id='GLUXZipUpdateInput']")
    WebElement zipCodeInput;
    @FindBy(xpath = "//div[@id='a-popover-1']//div[@id='GLUXSpecifyLocationDiv']//input[@type='submit']")
    WebElement updateZipCodeButton;
    @FindBy(xpath = "//div[@id='a-popover-1']//div[@class='a-popover-footer']//input[@id='GLUXConfirmClose']")
    WebElement continueButton;
    @FindBy(xpath = "//*[@id='a-popover-1']//button[@name='glowDoneButton']")
    WebElement locationChangingDone;
    @FindBy(id = zipConfirmationSectionId)
    WebElement zipConfirmationSection;

    public LocationModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setZipCode(String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(zipCodeInput));
        zipCodeInput.sendKeys(zipCode);
        updateZipCodeButton.click();

        ExpectedCondition<WebElement> condition1 = ExpectedConditions.elementToBeClickable(zipConfirmationSection);
        ExpectedCondition<WebElement> condition2 = ExpectedConditions.elementToBeClickable(continueButton);

        wait.until(ExpectedConditions.or(condition1, condition2));

        if (!driver.findElements(By.id(zipConfirmationSectionId)).isEmpty())
            locationChangingDone.click();
        else if(continueButton.isDisplayed())
            continueButton.click();

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("html"))));
    }
}
