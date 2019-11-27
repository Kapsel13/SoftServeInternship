import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.DashboardPage;
import pages.LogInPage;


public class WeatherAppTest extends TestPreparation {


    private String invalidAdminUsername;
    private String invalidAdminPassword;
    private String invalidLocation;
    private String validDashboardName;
    private String city;

    private By locationDisabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[@disabled]");
    private By nameDisabledButton = By.xpath("//div[contains(text(), 'What do you want to call this dashboard?')]/../../..//button[@disabled]");
    private By newDashboardText;
    private String summaryPageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Summary')]";
    private String dashboardPageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Dashboards')]";
    private String interactivePageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Interactive Map')]";
    private By canvas = By.xpath("//canvas[@width='1920']");
    private By summaryPageText = By.xpath("//span[contains(text(),'Summary')]");
    private By dashboardPageText = By.xpath("//span[contains(text(),'My Dashboards')]");
    private By dashboards = By.xpath("((//table[contains(@class, 'dashboard-list-table')]//tr))//td[1]");
    private String dashboardsInList = "((//table[contains(@class, 'dashboard-list-table')]//tr)[%d])//td[1]";
    private By dashboardTag = By.xpath("(//span[@class='dropdown-item-title'])[1]");


    @Before
    public void setUp() {
        invalidAdminUsername = TestData.generateData(12);
        invalidAdminPassword = TestData.generateData(12);
        invalidLocation = TestData.generateData(8);
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
        newDashboardText = By.xpath("//span[contains(text(),'"+validDashboardName+"')]");
    }

    @Test
    public void testResponseOnEmptyLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationDisabledButton));
    }

    @Test
    public void testResponseOnInvalidLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.provideInValidLocation(invalidLocation);
    }

    @Test
    public void testResponseOnEmptyName(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameDisabledButton));
    }

    @Test
    public void testRedirectionToTheSpecificDashboardFromSummaryPage(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.redirectToTheSpecificDashboard(TestData.rnd);
    }

    @Test
    public void testRedirectionBetweenSummaryPageAndDashboardPage(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.redirectBetweenTwoPages(By.xpath(String.format(summaryPageLink, "")),By.xpath(String.format(dashboardPageLink, "")),summaryPageText);

    }

    @Test
    public void testRedirectionBetweenSummaryPageAndInteractiveMapPage(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.redirectBetweenTwoPages(By.xpath(String.format(summaryPageLink, "")),By.xpath(String.format(interactivePageLink, "")),summaryPageText);
    }

    @Test
    public void testRedirectionBetweenDashboardsPageAndInteractiveMapPage(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.redirectBetweenTwoPages(By.xpath(String.format(dashboardPageLink, "")),By.xpath(String.format(interactivePageLink, "")),dashboardPageText);
    }

    @Test
    public void testRedirectionInteractiveMapPageAndSummaryPage(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.redirectBetweenTwoPages(By.xpath(String.format(interactivePageLink, "")),By.xpath(String.format(summaryPageLink, "")),canvas);

    }

    @Test
    public void testRedirectionInteractiveMapPageAndDashboardPage(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.redirectBetweenTwoPages(By.xpath(String.format(interactivePageLink, "")),By.xpath(String.format(dashboardPageLink, "")),canvas);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
