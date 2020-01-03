import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import setup.EnvParams;


import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestPreparation {

    private EnvParams envParams;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String validAdminUsername;
    protected String validAdminPassword;
    protected String validCreatorUsername;
    protected String validCreatorPassword;
    protected String validEditorUsername;
    protected String validEditorPassword;
    protected String validReadOnlyUsername;
    protected String validReadOnlyPassword;
    protected String chromeDriverPath;
    @BeforeMethod
    public void basicSetUp() throws IOException {
        envParams = new EnvParams();
        chromeDriverPath = envParams.getChromeDriver();
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        /*ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--headless");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");*/
        driver = new ChromeDriver(/*options*/);
        //driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        //driver.manage().window().setSize(new Dimension(2100, 1050));
        driver.manage().window().maximize();

        driver.get("https://dev.opsdashboard.ibm.com/");
        validAdminUsername = envParams.getAdminUsername();
        validAdminPassword = envParams.getAdminPassword();
        validCreatorUsername = envParams.getCreatorUsername();
        validCreatorPassword = envParams.getCreatorPassword();
        validEditorUsername = envParams.getEditorUsername();
        validEditorPassword = envParams.getEditorPassword();
        validReadOnlyUsername = envParams.getReadOnlyUsername();
        validReadOnlyPassword = envParams.getReadOnlyPassword();
        wait = new WebDriverWait(driver, 15);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        driver.quit();
    }

}
