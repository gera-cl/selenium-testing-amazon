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

public class NavSearchBar extends BaseComponent {
    @FindBy(xpath = "//form[@id='nav-search-bar-form']//select[@id='searchDropdownBox']")
    WebElement searchDropdownBox;
    @FindBy(xpath = "//form[@id='nav-search-bar-form']//input[@id='twotabsearchtextbox']")
    WebElement searchTextBox;
    @FindBy(xpath = "//form[@id='nav-search-bar-form']//input[@id='nav-search-submit-button']")
    WebElement searchSubmitButton;

    public NavSearchBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void search(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
        searchTextBox.sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(searchSubmitButton));
        searchSubmitButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-component-type='s-search-results']")));
    }


}
