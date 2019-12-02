import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.EnvParams;

import java.io.File;
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
    @Before
    public void basicSetUp() throws IOException {
        /*File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("/home/Ubuntu/IdeaProjects/SoftServeInternship/src/test/java/screenshot.png"));*/
        envParams = new EnvParams();
        System.setProperty("webdriver.chrome.driver","/home/ubuntu/IdeaProjects/SoftServeInternship/src/test/java/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--headless");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
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
        wait = new WebDriverWait(driver, 5);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
