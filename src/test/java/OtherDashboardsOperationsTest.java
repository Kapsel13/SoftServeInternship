import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

import java.util.HashMap;

public class OtherDashboardsOperationsTest extends TestPreparation {
    private String city;
    private String validDashboardName;
    protected By newDashboardText;
    DashboardPage dashboardPage;
    HashMap<String ,String> testArguments= new HashMap<>();
    @BeforeMethod
    public void setUp() {
        if (testArguments.isEmpty()) {
            validDashboardName = "!-auto_test-" + TestData.generateData(8);
            city = TestData.getRandomCity();
            newDashboardText = By.xpath("//span[contains(text(),'" + validDashboardName + "')]");
            dashboardPage = new DashboardPage(driver, wait);
            testArguments.put("dashboardName", validDashboardName);
            testArguments.put("city", city);
        }
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCLosingCreateDashboardWindow(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("city: "+testArguments.get("city"));
        System.out.println(city);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
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
