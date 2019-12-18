import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

//TODO
//test create dashboard cancel on last step
//test delete dashboard cancel on last step
public class WeatherAppNegativeTests extends TestPreparation {
    private By locationDisabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[@disabled]");
    private By nameDisabledButton = By.xpath("//div[contains(text(), 'What do you want to call this dashboard?')]/../../..//button[@disabled]");
    private String invalidLocation;
    private String city;
    private String validDashboardName;
    @BeforeMethod
    public void setUp() {
        invalidLocation = TestData.generateData(8);
        city = TestData.getRandomCity();
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnEmptyLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationDisabledButton));
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnInvalidLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.provideInValidLocation(invalidLocation);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnEmptyName(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameDisabledButton));
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCLosingCreateDashboardWindow(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.closeCreateDashboardWindow(validDashboardName);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnClosingDeleteDashboardWindow(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.closeDeleteDashboardOperationOnRandomDashboard();
    }
}
