import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.rmi.server.ExportException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WeatherAppTest {

    WebDriver driver;
    WebDriverWait wait;
    String invalidUsername;
    String invalidPassword;
    String invalidLocation;
    String validDashboardName;
    String city;

    private By loginInput = By.xpath("//input[@class='text-line']");
    private By loginError = By.xpath("//div[@class='error']");
    private By loginButton = By.xpath("//button[@class='login-button']");
    private By applicationLogo = By.xpath("//img[@id='twc-logo']");
    private By dashboardDropdownButton = By.xpath("//button[@id='dashboard-dropdown']");
    private By createNewDashboardOption = By.xpath( "//button[contains(@class, 'dropdown-item')]/span[contains(text(), 'Create New Dashboard')]");
    private By confirmButton = By.xpath("//button[@class='button-highlight']");
    private By locationDisabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[@disabled]");
    private By searchLocationInput = By.xpath("//input[@title='Search by City, Zip or Lat, Lon']");
    private String cityInList = "(//div[contains(@class, 'search-results')]//div[contains(@class, 'location') and contains(text(), '%s')])[1]";
    private By locationEnabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[contains(text(), 'Next')]");
    private By nameInput = By.xpath("//input[@placeholder='Enter name']");
    private By nameDisabledButton = By.xpath("//div[contains(text(), 'What do you want to call this dashboard?')]/../../..//button[@disabled]");
    private By nameEnabledButton = By.xpath("//div[contains(text(), 'What do you want to call this dashboard?')]/../../..//button[contains(text(), 'Next')]");
    private By monitorText = By.xpath("//strong[contains(text(),'every day, all year.')]");
    private By monitorButton = By.xpath("//button[contains(text(), 'Next')]");
    private By createButton = By.xpath("//button[contains(text(),'Create')]");
    private By newDashboardText;
    private By summaryPageLink = By.xpath("//a[contains(text(),'Summary')]");
    private By dashboardPageLink = By.xpath( "//a[contains(text(),'Dashboards')]");
    private By dashboardPageText = By.xpath("//span[contains(text(),'My Dashboards')]");
    private By InteractivePageLink = By.xpath( "//a[contains(text(),'Interactive Map')]");
    private By canvas = By.xpath("//canvas[@width='1920']");
    private By summaryPageText = By.xpath("//span[contains(text(),'Summary')]");
    private By settingsIcon = By.xpath("//section[@class='settings-icon-container']");
    private By signOutLabel = By.xpath("//label[contains(text(),'Sign out')]");
    private By usernameInput = By.xpath("//input[@placeholder='Username']");
    private By dashboards = By.xpath("//table[contains(@class, 'dashboard-list-table')]//tr");
    private String dashboardsInList = "((//table[contains(@class, 'dashboard-list-table')]//tr)[%d])//td[1]";
    private By dashboardTag = By.xpath("(//span[@class='dropdown-item-title'])[1]");

    List<String> locations = Arrays.asList("Berlin", "Sidney", "Tokyo", "London");
    Random rnd = new Random();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/Users/mkowal/IdeaProjects/WeatherApplication/src/test/java/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        driver.get("https://dev.opsdashboard.ibm.com/");

        //wait = new WebDriverWait(driver,30);
        invalidUsername = TestData.generateData(12);
        invalidPassword = TestData.generateData(12);
        invalidLocation = TestData.generateData(8);
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity(locations,rnd);
        newDashboardText = By.xpath("//span[contains(text(),'"+validDashboardName+"')]");
    }

    public void logIn() throws InterruptedException {
        driver.findElement(loginInput).sendKeys("test11@test.com");
        driver.findElement(loginButton).click();
        Thread.sleep(5000);

        driver.findElement(loginInput).sendKeys("%6dS4jD3");
        driver.findElement(loginButton).click();
        Thread.sleep(5000);
    }

    @Test
    public void testResponseOnInvalidUsername() throws InterruptedException {
        driver.findElement(loginInput).sendKeys(invalidUsername);
        driver.findElement(loginButton).click();
        Thread.sleep(5000);

        boolean isErrorDisplayed = driver.findElement(loginError).isDisplayed();
        Assert.assertTrue(isErrorDisplayed);

    }

    @Test
    public void testResponseOnInvalidPassword() throws InterruptedException {
        driver.findElement(loginInput).sendKeys("test11@test.com");
        driver.findElement(loginButton).click();
        Thread.sleep(5000);

        driver.findElement(loginInput).sendKeys(invalidPassword);
        driver.findElement(loginButton).click();

        boolean isErrorDisplayed = driver.findElement(loginError).isDisplayed();
        Assert.assertTrue(isErrorDisplayed);

    }

    @Test
    public void testResponseOnValidPassword() throws InterruptedException {
        logIn();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(applicationLogo));
        boolean isLogoDisplayed= driver.findElement(applicationLogo).isDisplayed();
        Assert.assertTrue(isLogoDisplayed);
    }

    @Test
    public void testResponseOnEmptyLocation() throws InterruptedException {
        logIn();

        driver.findElement(dashboardDropdownButton).click();
        Thread.sleep(5000);

       driver.findElement(createNewDashboardOption).click();
       Thread.sleep(5000);

       driver.findElement(confirmButton).click();
       Thread.sleep(5000);

       boolean isNextButtonDisabled = driver.findElement(locationDisabledButton).isDisplayed();
       Assert.assertTrue(isNextButtonDisabled);
    }

    @Test
    public void testResponseOnInvalidLocation() throws InterruptedException {
        logIn();

        driver.findElement(dashboardDropdownButton).click();
        Thread.sleep(5000);

        driver.findElement(createNewDashboardOption).click();
        Thread.sleep(5000);

        driver.findElement(confirmButton).click();
        Thread.sleep(5000);

        driver.findElement(searchLocationInput).sendKeys(invalidLocation);

        boolean isNextButtonDisabled = driver.findElement(locationDisabledButton).isDisplayed();
        Assert.assertTrue(isNextButtonDisabled);
    }

    @Test
    public void testResponseOnValidLocation() throws InterruptedException {
        logIn();

        driver.findElement(dashboardDropdownButton).click();
        Thread.sleep(5000);

        driver.findElement(createNewDashboardOption).click();
        Thread.sleep(5000);

        driver.findElement(confirmButton).click();
        Thread.sleep(5000);

        driver.findElement(searchLocationInput).click();

        driver.findElement(searchLocationInput).sendKeys(city);
        driver.findElement(By.xpath(String.format(cityInList, city))).click();
        Thread.sleep(5000);
        driver.findElement(locationEnabledButton).click();
        Thread.sleep(5000);

        boolean isNameInputDisplayed = driver.findElement(nameInput).isDisplayed();
        Assert.assertTrue(isNameInputDisplayed);
    }

    @Test
    public void testResponseOnEmptyName() throws InterruptedException {
        logIn();

        driver.findElement(dashboardDropdownButton).click();
        Thread.sleep(5000);

        driver.findElement(createNewDashboardOption).click();
        Thread.sleep(5000);

        driver.findElement(confirmButton).click();
        Thread.sleep(5000);

        driver.findElement(searchLocationInput).sendKeys(city);
        driver.findElement(By.xpath(String.format(cityInList, city))).click();
        Thread.sleep(5000);
        driver.findElement(locationEnabledButton).click();
        Thread.sleep(5000);

        boolean isNextButtonDisabled = driver.findElement(nameDisabledButton).isDisplayed();
        Assert.assertTrue(isNextButtonDisabled);
    }

    @Test
    public void testResponseOnValidName() throws InterruptedException {
        logIn();

        driver.findElement(dashboardDropdownButton).click();
        Thread.sleep(5000);

        driver.findElement(createNewDashboardOption).click();
        Thread.sleep(5000);

        driver.findElement(confirmButton).click();
        Thread.sleep(5000);

        driver.findElement(searchLocationInput).sendKeys(city);
        driver.findElement(By.xpath(String.format(cityInList, city))).click();
        Thread.sleep(5000);
        driver.findElement(locationEnabledButton).click();
        Thread.sleep(5000);

        driver.findElement(nameInput).sendKeys(validDashboardName);
        driver.findElement(nameEnabledButton).click();
        Thread.sleep(5000);

        boolean isMonitorTagDisplayed = driver.findElement(monitorText).isDisplayed();
        Assert.assertTrue(isMonitorTagDisplayed);
    }

    @Test
    public void testResponseOnCreatingNewDashboard() throws InterruptedException {
        logIn();

        driver.findElement(dashboardDropdownButton).click();
        Thread.sleep(5000);

        driver.findElement(createNewDashboardOption).click();
        Thread.sleep(5000);

        driver.findElement(confirmButton).click();
        Thread.sleep(5000);

        driver.findElement(searchLocationInput).sendKeys(city);
        driver.findElement(By.xpath(String.format(cityInList, city))).click();
        Thread.sleep(5000);
        driver.findElement(locationEnabledButton).click();
        Thread.sleep(5000);

        driver.findElement(nameInput).sendKeys(validDashboardName);
        driver.findElement(nameEnabledButton).click();
        Thread.sleep(5000);

        driver.findElements(monitorButton).get(3).click();
        Thread.sleep(5000);

        driver.findElement(createButton).click();
        Thread.sleep(5000);

        boolean isNewDashboardDisplayed = driver.findElement(newDashboardText).isDisplayed();
        Assert.assertTrue(isNewDashboardDisplayed);
    }


    @Test
    public void testRedirectionToTheSpecificDashboardFromSummaryPage() throws InterruptedException {
        logIn();

        driver.findElement(summaryPageLink).click();
        Thread.sleep(5000);

        int numberOfDashboards = driver.findElements(dashboards).size();
        int dashboardNumber = rnd.nextInt(numberOfDashboards-1)+2;
        String dashboardText = driver.findElement(By.xpath(String.format(dashboardsInList,dashboardNumber))).getText();
        driver.findElement(By.xpath(String.format(dashboardsInList,dashboardNumber))).click();
        Thread.sleep(5000);

        String dashboardTagText = driver.findElement(dashboardTag).getText();
        Assert.assertEquals(dashboardText,dashboardTagText);
    }

    @Test
    public void testRedirectionBetweenSummaryPageAndDashboardPage() throws InterruptedException {
        logIn();

        driver.findElement(summaryPageLink).click();
        Thread.sleep(5000);

        driver.findElement(dashboardPageLink).click();
        Thread.sleep(5000);

        boolean isDashboardsPageDisplayed = driver.findElement(dashboardPageText).isDisplayed();
        Assert.assertTrue(isDashboardsPageDisplayed);
    }

    @Test
    public void testRedirectionBetweenSummaryPageAndInteractiveMapPage() throws InterruptedException {
        logIn();

        driver.findElement(summaryPageLink).click();
        Thread.sleep(5000);

        driver.findElement(InteractivePageLink).click();
        Thread.sleep(5000);

        boolean isInteractiveMapPageDisplayed = driver.findElement(canvas).isDisplayed();
        Assert.assertTrue(isInteractiveMapPageDisplayed);
    }

    @Test
    public void testRedirectionBetweenDashboardsPageAndInteractiveMapPage() throws InterruptedException {
        logIn();

        driver.findElement(InteractivePageLink).click();
        Thread.sleep(5000);

        boolean isInteractiveMapPageDisplayed = driver.findElement(canvas).isDisplayed();
        Assert.assertTrue(isInteractiveMapPageDisplayed);
    }

    @Test
    public void testRedirectionInteractiveMapPageAndSummaryPage() throws InterruptedException {
        logIn();

        driver.findElement(InteractivePageLink).click();
        Thread.sleep(5000);

        driver.findElement(summaryPageLink).click();
        Thread.sleep(5000);

        boolean isSummaryPageDisplayed = driver.findElement(summaryPageText).isDisplayed();
        Assert.assertTrue(isSummaryPageDisplayed);

    }

    @Test
    public void testRedirectionInteractiveMapPageAndDashboardPage() throws InterruptedException {
        logIn();

        driver.findElement(InteractivePageLink).click();
        Thread.sleep(5000);

        driver.findElement(dashboardPageLink).click();
        Thread.sleep(5000);

        boolean isDashboardsPageDisplayed = driver.findElement(dashboardPageText).isDisplayed();
        Assert.assertTrue(isDashboardsPageDisplayed);

    }

    @Test
    public void testResponseOnLogOut() throws InterruptedException {
        logIn();
        driver.findElement(settingsIcon).click();
        driver.findElement(signOutLabel).click();
        Thread.sleep(5000);

        boolean isUsernameInputDisplayed = driver.findElement(usernameInput).isDisplayed();
        Assert.assertTrue(isUsernameInputDisplayed);

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
