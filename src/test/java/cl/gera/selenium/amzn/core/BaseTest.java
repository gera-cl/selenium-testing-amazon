package cl.gera.selenium.amzn.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    private final ChromeOptions options = new ChromeOptions();

    public BaseTest() {
        // chrome driver initialization
        String CHROME_VERSION = System.getenv("CHROME_VERSION");
        WebDriverManager.chromedriver().setup().browserVersion(CHROME_VERSION);
        options.addArguments("--no-sandbox");
        options.addArguments("--headless=new");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
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
        // chrome driver
        ChromeDriver driver = new ChromeDriver(options);
        drivers.put(testName, driver);
    }

    @AfterMethod
    public void afterTests(ITestResult testResult) {
        String testName = getTestName(testResult);
        WebDriver driver = drivers.get(testName);
        ExtentTest extentTest = extentTests.get(testName);

        try {
            if (testResult.isSuccess())
                extentTest.pass("PASSED");
            else {
                extentTest.log(Status.FAIL, testResult.getThrowable());
                String base64Screenshot =
                        "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
                extentTest.log(Status.FAIL, "FAILED",
                        extentTest.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
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