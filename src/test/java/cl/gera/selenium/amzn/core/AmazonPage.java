package cl.gera.selenium.amzn.core;

import cl.gera.selenium.amzn.components.NavBelt;
import org.openqa.selenium.WebDriver;

public class AmazonPage extends BaseComponent {
    public final NavBelt navBelt;

    public AmazonPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.amazon.com");
        this.navBelt = new NavBelt(this.driver);
    }
}
