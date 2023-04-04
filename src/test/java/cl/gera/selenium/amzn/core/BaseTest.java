package cl.gera.selenium.amzn.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;


public class BaseTest {
    private final ConcurrentHashMap<String, WebDriver> drivers = new ConcurrentHashMap<>();
    private final ChromeOptions options = new ChromeOptions();

    public BaseTest() {
        WebDriverManager.chromedriver().setup();
        options.addArguments("--headless=new");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    }

    @BeforeMethod
    public void beforeTests(Method method) {
        ChromeDriver driver = new ChromeDriver(options);
        drivers.put(method.getName(), driver);
    }

    @AfterMethod
    public void afterTests(Method method) {
        WebDriver driver = drivers.get(method.getName());
        driver.quit();
        drivers.remove(method.getName());
    }

    public WebDriver getDriver() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String methodName = stackTraceElements[2].getMethodName();
        return drivers.get(methodName);
    }
}