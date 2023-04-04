package cl.gera.selenium.amzn.core;

import org.openqa.selenium.WebDriver;

public class BaseComponent {
    protected final WebDriver driver;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
    }
}
