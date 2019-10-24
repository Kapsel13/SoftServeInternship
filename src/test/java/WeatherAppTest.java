import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeatherAppTest {

    WebDriver driver;
    WebDriverWait wait;
    String invalidUsername;
    String invalidPassword;
    String invalidLocation;
    String validDashboardName;
    String city;
    String validUsername;
    String validPassword;

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
    private String summaryPageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Summary')]";
    private String dashboardPageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Dashboards')]";
    private By dashboardPageText = By.xpath("//span[contains(text(),'My Dashboards')]");
    private String interactivePageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Interactive Map')]";
    private By canvas = By.xpath("//canvas[@width='1920']");
    private By summaryPageText = By.xpath("//span[contains(text(),'Summary')]");
    private By settingsIcon = By.xpath("//section[@class='settings-icon-container']");
    private By signOutLabel = By.xpath("//label[contains(text(),'Sign out')]");
    private By usernameInput = By.xpath("//input[@placeholder='Username']");
    private By dashboards = By.xpath("((//table[contains(@class, 'dashboard-list-table')]//tr))//td[1]");
    private String dashboardsInList = "((//table[contains(@class, 'dashboard-list-table')]//tr)[%d])//td[1]";
    private By dashboardTag = By.xpath("(//span[@class='dropdown-item-title'])[1]");


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/Users/mkowal/IdeaProjects/WeatherApplication/src/test/java/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        driver.get("https://dev.opsdashboard.ibm.com/");

        invalidUsername = TestData.generateData(12);
        invalidPassword = TestData.generateData(12);
        invalidLocation = TestData.generateData(8);
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        validUsername = "test11@test.com";
        validPassword = "%6dS4jD3";
        city = TestData.getRandomCity();
        newDashboardText = By.xpath("//span[contains(text(),'"+validDashboardName+"')]");
        wait = new WebDriverWait(driver, 5);
    }

    public void logIn() throws InterruptedException {
        driver.findElement(loginInput).sendKeys(validUsername);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));

        driver.findElement(loginInput).sendKeys(validPassword);
        driver.findElement(loginButton).click();
        waitUntillAllElementsVisible(Arrays.asList(dashboardDropdownButton, dashboardPageText));
    }

    public void beginCreatingNewDashboard() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewDashboardOption));

        driver.findElement(createNewDashboardOption).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));

        driver.findElement(confirmButton).click();
    }
    public void redirectBetweenTwoPages(By pageLink, By pageLink2, By pageElement) throws InterruptedException{
        driver.findElement(pageLink).click();
        waitUntillAllElementsVisible(Arrays.asList(pageLink,pageElement));

        driver.findElement(pageLink2).click();
    }
    public void selectValidLocation() throws InterruptedException {
        driver.findElement(searchLocationInput).sendKeys(city);
        driver.findElement(By.xpath(String.format(cityInList, city))).click();
        wait.until(ExpectedConditions.elementToBeClickable(locationEnabledButton));
        driver.findElement(locationEnabledButton).click();
    }
    private void waitUntillAllElementsVisible(List<By> elements) {
        for (By element : elements) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
    }
    @Test
    public void testResponseOnInvalidUsername() throws InterruptedException {
        driver.findElement(loginInput).sendKeys(invalidUsername);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginError));

        /*boolean isErrorDisplayed = driver.findElement(loginError).isDisplayed();
        Assert.assertTrue(isErrorDisplayed);*/

    }

    @Test
    public void testResponseOnInvalidPassword() throws InterruptedException {
        driver.findElement(loginInput).sendKeys(validUsername);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));

        driver.findElement(loginInput).sendKeys(invalidPassword);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginError));
        /*boolean isErrorDisplayed = driver.findElement(loginError).isDisplayed();
        Assert.assertTrue(isErrorDisplayed);*/

    }

    @Test
    public void testResponseOnValidPassword() throws InterruptedException {
        logIn();
        /*boolean isLogoDisplayed= driver.findElement(applicationLogo).isDisplayed();
        Assert.assertTrue(isLogoDisplayed);*/
    }

    @Test
    public void testResponseOnEmptyLocation() throws InterruptedException {
        logIn();
        beginCreatingNewDashboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationDisabledButton));
        /*boolean isNextButtonDisabled = driver.findElement(locationDisabledButton).isDisplayed();
        Assert.assertTrue(isNextButtonDisabled);*/
    }

    @Test
    public void testResponseOnInvalidLocation() throws InterruptedException {
        logIn();
        beginCreatingNewDashboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchLocationInput));
        driver.findElement(searchLocationInput).sendKeys(invalidLocation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationDisabledButton));
        /*boolean isNextButtonDisabled = driver.findElement(locationDisabledButton).isDisplayed();
        Assert.assertTrue(isNextButtonDisabled);*/
    }

    @Test
    public void testResponseOnValidLocation() throws InterruptedException {
        logIn();
        beginCreatingNewDashboard();
        wait.until(ExpectedConditions.elementToBeClickable(searchLocationInput));
        driver.findElement(searchLocationInput).click();
        selectValidLocation();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));

        /*boolean isNameInputDisplayed = driver.findElement(nameInput).isDisplayed();
        Assert.assertTrue(isNameInputDisplayed);*/
    }

    @Test
    public void testResponseOnEmptyName() throws InterruptedException {
        logIn();
        beginCreatingNewDashboard();
        selectValidLocation();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameDisabledButton));

        /*boolean isNextButtonDisabled = driver.findElement(nameDisabledButton).isDisplayed();
        Assert.assertTrue(isNextButtonDisabled);*/
    }

    @Test
    public void testResponseOnValidName() throws InterruptedException {
        logIn();
        beginCreatingNewDashboard();
        selectValidLocation();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));

        driver.findElement(nameInput).sendKeys(validDashboardName);
        driver.findElement(nameEnabledButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(monitorText));

        /*boolean isMonitorTagDisplayed = driver.findElement(monitorText).isDisplayed();
        Assert.assertTrue(isMonitorTagDisplayed);*/
    }

    @Test
    public void testResponseOnCreatingNewDashboard() throws InterruptedException {
        logIn();
        beginCreatingNewDashboard();
        selectValidLocation();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));

        driver.findElement(nameInput).sendKeys(validDashboardName);
        driver.findElement(nameEnabledButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(monitorButton).get(3)));

        driver.findElements(monitorButton).get(3).click();
        wait.until(ExpectedConditions.elementToBeClickable(createButton));

        driver.findElement(createButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(newDashboardText));

        /*boolean isNewDashboardDisplayed = driver.findElement(newDashboardText).isDisplayed();
        Assert.assertTrue(isNewDashboardDisplayed);*/
    }


    @Test
    public void testRedirectionToTheSpecificDashboardFromSummaryPage() throws InterruptedException {
        logIn();
        waitUntillAllElementsVisible(Arrays.asList(dashboardDropdownButton, dashboardPageText));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(summaryPageLink, ""))));
        driver.findElement(By.xpath(String.format(summaryPageLink, ""))).click();
        waitUntillAllElementsVisible(Arrays.asList(By.xpath(String.format(summaryPageLink, "active")),summaryPageText));


        int numberOfDashboards = driver.findElements(dashboards).size();
        int dashboardNumber = TestData.rnd.nextInt(numberOfDashboards-1)+1;
        String dashboardText = driver.findElement(By.xpath(String.format(dashboardsInList,dashboardNumber))).getText();
        driver.findElement(By.xpath(String.format(dashboardsInList,dashboardNumber))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTag));

        String dashboardTagText = driver.findElement(dashboardTag).getText();
        Assert.assertEquals(dashboardText,dashboardTagText);
    }

    @Test
    public void testRedirectionBetweenSummaryPageAndDashboardPage() throws InterruptedException {
        logIn();
        redirectBetweenTwoPages(By.xpath(String.format(summaryPageLink, "")),By.xpath(String.format(dashboardPageLink, "")),summaryPageText);
        waitUntillAllElementsVisible(Arrays.asList(dashboardDropdownButton, dashboardPageText));
        /*boolean isDashboardsPageDisplayed = driver.findElement(dashboardPageText).isDisplayed();
        Assert.assertTrue(isDashboardsPageDisplayed);*/
    }

    @Test
    public void testRedirectionBetweenSummaryPageAndInteractiveMapPage() throws InterruptedException {
        logIn();
        redirectBetweenTwoPages(By.xpath(String.format(summaryPageLink, "")),By.xpath(String.format(interactivePageLink, "")),summaryPageText);
        waitUntillAllElementsVisible(Arrays.asList(canvas,By.xpath(String.format(interactivePageLink, "active"))));
        /*boolean isInteractiveMapPageDisplayed = driver.findElement(canvas).isDisplayed();
        Assert.assertTrue(isInteractiveMapPageDisplayed);*/
    }

    @Test
    public void testRedirectionBetweenDashboardsPageAndInteractiveMapPage() throws InterruptedException {
        logIn();
        redirectBetweenTwoPages(By.xpath(String.format(dashboardPageLink, "")),By.xpath(String.format(interactivePageLink, "")),dashboardPageText);
        waitUntillAllElementsVisible(Arrays.asList(canvas,By.xpath(String.format(interactivePageLink, "active"))));
        /*boolean isInteractiveMapPageDisplayed = driver.findElement(canvas).isDisplayed();
        Assert.assertTrue(isInteractiveMapPageDisplayed);*/
    }

    @Test
    public void testRedirectionInteractiveMapPageAndSummaryPage() throws InterruptedException {
        logIn();
        redirectBetweenTwoPages(By.xpath(String.format(interactivePageLink, "")),By.xpath(String.format(summaryPageLink, "")),canvas);
        waitUntillAllElementsVisible(Arrays.asList(By.xpath(String.format(summaryPageLink, "active")),summaryPageText));
        /*boolean isSummaryPageDisplayed = driver.findElement(summaryPageText).isDisplayed();
        Assert.assertTrue(isSummaryPageDisplayed);*/

    }

    @Test
    public void testRedirectionInteractiveMapPageAndDashboardPage() throws InterruptedException {
        logIn();
        redirectBetweenTwoPages(By.xpath(String.format(interactivePageLink, "")),By.xpath(String.format(dashboardPageLink, "")),canvas);
        waitUntillAllElementsVisible(Arrays.asList(dashboardDropdownButton, dashboardPageText));
        /*boolean isDashboardsPageDisplayed = driver.findElement(dashboardPageText).isDisplayed();
        Assert.assertTrue(isDashboardsPageDisplayed);*/

    }

    @Test
    public void testResponseOnLogOut() throws InterruptedException {
        logIn();
        driver.findElement(settingsIcon).click();
        driver.findElement(signOutLabel).click();
        waitUntillAllElementsVisible(Arrays.asList(usernameInput,loginButton));

        /*boolean isUsernameInputDisplayed = driver.findElement(usernameInput).isDisplayed();
        Assert.assertTrue(isUsernameInputDisplayed);*/

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
