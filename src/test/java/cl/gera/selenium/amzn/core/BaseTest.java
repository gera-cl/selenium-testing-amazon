package cl.gera.selenium.amzn.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {
    private final ExtentReports extentReports = new ExtentReports();
    private final ConcurrentHashMap<String, WebDriver> drivers = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ExtentTest> extentTests = new ConcurrentHashMap<>();
    private final AbstractDriverOptions driverOptions;
    private final Class<? extends WebDriver> webDriverClass;

    public BaseTest() {
        // driver initialization
        if (WebDriverManager.chromedriver().getBrowserPath().isPresent()) {
            System.out.println("CHROME_BROWSER");
            WebDriverManager.chromedriver().setup();
            // set driver options
            driverOptions = new ChromeOptions();
            ((ChromeOptions) driverOptions).addArguments("--headless=new");
            ((ChromeOptions) driverOptions).addArguments("--no-sandbox");
            ((ChromeOptions) driverOptions).addArguments("--remote-allow-origins=*");
            ((ChromeOptions) driverOptions).addArguments("--window-size=1920,1200");
            ((ChromeOptions) driverOptions).addArguments("--disable-extensions");
            ((ChromeOptions) driverOptions).addArguments("--disable-infobars");
            ((ChromeOptions) driverOptions).addArguments("--disable-dev-shm-usage");

            webDriverClass = ChromeDriver.class;
        } else if (WebDriverManager.firefoxdriver().getBrowserPath().isPresent()) {
            System.out.println("FIREFOX_BROWSER");
            WebDriverManager.firefoxdriver().setup();

            // initialize profile
            FirefoxProfile profile = new FirefoxProfile();

            // Modificar userAgent para que se parezca a un navegador real
            profile.setPreference("general.useragent.override",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.71");

            // Deshabilitar la detección de webdriver
            profile.setPreference("dom.webdriver.enabled", false);
            profile.setPreference("useAutomationExtension", false);

            // Deshabilitar notificaciones
            profile.setPreference("dom.webnotifications.enabled", false);

            // Configurar almacenamiento local y cookies
            profile.setPreference("network.cookie.cookieBehavior", 0);
            profile.setPreference("network.cookie.lifetimePolicy", 0);
            profile.setPreference("privacy.clearOnShutdown.cookies", false);
            profile.setPreference("privacy.clearOnShutdown.offlineApps", false);
            profile.setPreference("privacy.clearOnShutdown.siteSettings", false);
            profile.setPreference("privacy.sanitize.sanitizeOnShutdown", false);

            // set driver options
            driverOptions = new FirefoxOptions();
            ((FirefoxOptions) driverOptions).addArguments("-headless");
            ((FirefoxOptions) driverOptions).setProfile(profile);

            webDriverClass = FirefoxDriver.class;
        } else {
            throw new RuntimeException("No driver found");
        }

        // extent reports
        String reportName = "extent-report-" + System.currentTimeMillis() + ".html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/extent-reports/" + reportName);
        extentReports.attachReporter(extentSparkReporter);
    }

    @BeforeMethod
    public void beforeTests(ITestResult testResult) {
        String testName = getTestName(testResult);
        // extent reports
        ExtentTest extentTest = extentReports.createTest(testName);
        extentTests.put(testName, extentTest);
        // driver initialization
        WebDriver driver = WebDriverManager.getInstance(webDriverClass).capabilities(driverOptions).create();
        driver.manage().window().maximize();
        drivers.put(testName, driver);
    }

    @AfterMethod
    public void afterTests(ITestResult testResult) {
        String testName = getTestName(testResult);
        WebDriver driver = drivers.get(testName);
        ExtentTest extentTest = extentTests.get(testName);

        try {
            String base64Screenshot = "data:image/png;base64,"
                    + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
            Media screenshot = extentTest.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);
            if (testResult.isSuccess()) {
                extentTest.pass("PASSED");
                extentTest.log(Status.PASS, "PASSED", screenshot);
            } else {
                extentTest.log(Status.FAIL, testResult.getThrowable());
                extentTest.log(Status.FAIL, "FAILED", screenshot);
            }
        } finally {
            driver.quit();
            drivers.remove(testName);
        }
    }

    @AfterSuite
    public void afterSuite() {
        extentReports.flush();
    }

    public WebDriver getDriver() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String[] classNameArray = stackTraceElements[2].getClassName().split("\\.");
        String simpleClassName = classNameArray[classNameArray.length - 1];
        String testName = simpleClassName.concat("#").concat(stackTraceElements[2].getMethodName());
        return drivers.get(testName);
    }

    public String getTestName(ITestResult testResult) {
        return testResult.getMethod().getRealClass().getSimpleName()
                .concat("#")
                .concat(testResult.getMethod().getMethodName());
    }
}