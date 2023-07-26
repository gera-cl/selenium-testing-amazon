package cl.gera.selenium.amzn.components;

import cl.gera.selenium.amzn.core.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavBelt extends BaseComponent {
    public final NavSearchBar navSearchBar;
    @FindBy(id = "icp-nav-flyout")
    WebElement icpNavFlyout;
    @FindBy(id = "nav-global-location-popover-link")
    WebElement navGlobalLocation;

    public NavBelt(WebDriver driver) {
        super(driver);

        // wait for the navbar visibility
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        int retries = 1;
        while (retries <= 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navbar-main")));
            } catch (TimeoutException timeoutException) {
                driver.navigate().refresh();
                retries++;
            }
        }

        this.navSearchBar = new NavSearchBar(driver);
        PageFactory.initElements(driver, this);
    }

    public IcpNavFlyout hoverNavFlyout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(icpNavFlyout));
        Actions action = new Actions(driver);
        action.moveToElement(icpNavFlyout).perform();
        return new IcpNavFlyout(driver);
    }

    public LocationModal clickNavGlobalLocation() {
        navGlobalLocation.click();
        return new LocationModal(driver);
    }
}
