import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

import java.util.HashMap;
import java.util.Random;

public class ActiveDashboardsNumberTest extends TestPreparation {
    private String city;

    private String startDate;
    private String endDate;
    private String startDateBeforeCurrentDate;
    private String endDateAfterCurrentDate;
    private String validDashboardName;
    protected By newDashboardText;
    DashboardPage dashboardPage;
    HashMap<String ,String> testArguments;
    Random rnd = new Random();
    @BeforeMethod
    public void setUp() {
        if (testArguments.isEmpty()) {
            testArguments = new HashMap<>();
            validDashboardName = "!-auto_test-" + TestData.generateData(8);
            city = TestData.getRandomCity();
            startDate = TestData.getRandomStartDate();
            endDate = TestData.getRandomEndDate();
            startDateBeforeCurrentDate = TestData.getRandomStartDateBeforeCurrentDate();
            endDateAfterCurrentDate = TestData.getRandomEndDateAfterCurrentDate();
            newDashboardText = By.xpath("//span[contains(text(),'" + validDashboardName + "')]");
            dashboardPage = new DashboardPage(driver, wait);
            testArguments.put("dashboardName", validDashboardName);
            testArguments.put("city", city);
            testArguments.put("startDate", startDate);
            testArguments.put("endDate", endDate);
            testArguments.put("startDateBeforeCurrentDate", startDateBeforeCurrentDate);
            testArguments.put("endDateAfterCurrentDate", endDateAfterCurrentDate);
        }
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterAddingActiveDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        System.out.println("dashboardLocation: "+testArguments.get("city"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(1);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterAddingInActiveDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        System.out.println("dashboardLocation: "+testArguments.get("city"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(0);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterDeletingActiveDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        dashboardPage.chooseActiveDashboard(rnd);
        dashboardPage.deleteSpecificDashboard();
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(-1);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterDeletingInActiveDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        dashboardPage.chooseInActiveDashboard(rnd);
        dashboardPage.deleteSpecificDashboard();
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(0);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterChangingActiveDashboardIntoInActive(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        dashboardPage.chooseActiveDashboard(rnd);
        dashboardPage.editSpecificDashboard("inActive",rnd,startDate,endDate);
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(-1);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterChangingInActiveDashboardIntoActive(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        dashboardPage.chooseInActiveDashboard(rnd);
        dashboardPage.editSpecificDashboard("Active",rnd,startDate,endDate);
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(1);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterAddingDashboardWithCustomRangeContainsCurrentData(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        System.out.println("dashboardLocation: "+testArguments.get("city"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        dashboardPage.setCustomRangeMonitoring(rnd,startDateBeforeCurrentDate,endDateAfterCurrentDate);
        System.out.println("startTimeBeforeCurrentDate: "+testArguments.get("startDateBeforeCurrentDate"));
        System.out.println("endTimeAfterCurrentDate: "+testArguments.get("endDateAfterCurrentDate"));
        dashboardPage.createNewDashboard(newDashboardText);
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(1);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testNumberOfActiveDashboardsAfterChangingInActiveDashboardIntoCustomRange(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.getNumberOfActiveDashboardsBeforeAction(validAdminUsername,validAdminPassword);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        dashboardPage.chooseInActiveDashboard(rnd);
        dashboardPage.editSpecificDashboard("Custom Range",rnd,startDate,endDate);
        System.out.println("startTime: "+testArguments.get("startDate"));
        System.out.println("end time: "+testArguments.get("endDate"));
        dashboardPage.checkNumberOfActiveDashboardsAfterAction(1);
    }

}
