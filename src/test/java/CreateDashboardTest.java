import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

import java.util.HashMap;

public class CreateDashboardTest extends TestPreparation{

    private String city;
    private String zipCode;
    private String cityOfZipCode;
    private String cityOfIcaoCode;
    private String startDate;
    private String endDate;
    private String gpsCoords;
    private String icaoCode;
    private String validDashboardName;
    protected By newDashboardText;
    DashboardPage dashboardPage;
    HashMap<String ,String> testArguments= new HashMap<>();
    @BeforeMethod
    public void setUp() {
        if (testArguments.isEmpty()) {
            validDashboardName = "!-auto_test-" + TestData.generateData(8);
            city = TestData.getRandomCity();
            zipCode = TestData.getRandomZipCode();
            icaoCode = TestData.getRandomIcaoCodes();
            cityOfZipCode = TestData.cities.get(TestData.zipCodes.indexOf(zipCode));
            cityOfIcaoCode = TestData.cities.get(TestData.icaoCodes.indexOf(icaoCode));
            startDate = TestData.getRandomStartDate();
            endDate = TestData.getRandomEndDate();
            gpsCoords = TestData.getRandomGpsCoords();
            newDashboardText = By.xpath("//span[contains(text(),'" + validDashboardName + "')]");
            dashboardPage = new DashboardPage(driver, wait);
            testArguments.put("dashboardName", validDashboardName);
            testArguments.put("city", city);
            testArguments.put("startDate", startDate);
            testArguments.put("endDate", endDate);
            testArguments.put("zipCode",zipCode);
            testArguments.put("icaoCode",icaoCode);
            testArguments.put("gpsCoords",gpsCoords);
            testArguments.put("cityOfZipCode",cityOfZipCode);
            testArguments.put("cityOfIcaoCode",cityOfIcaoCode);
        }
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("city: "+testArguments.get("city"));
        dashboardPage.selectValidLocation(city,city);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("zipCode: "+testArguments.get("zipCode"));
        System.out.println("cityOfZipCode: "+testArguments.get("cityOfZipCode"));
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("gpsCoords: "+testArguments.get("gpsCoords"));
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("icaoCode: "+testArguments.get("icaoCode"));
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("city: "+testArguments.get("city"));
        dashboardPage.selectValidLocation(city,city);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("zipCode: "+testArguments.get("zipCode"));
        System.out.println("cityOfZipCode: "+testArguments.get("cityOfZipCode"));
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("icaoCode: "+testArguments.get("icaoCode"));
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("gpsCoords: "+testArguments.get("gpsCoords"));
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("city: "+testArguments.get("city"));
        dashboardPage.selectValidLocation(city,city);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("startDate: "+testArguments.get("startDate"));
        System.out.println("endDate: "+testArguments.get("endDate"));
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("zipCode: "+testArguments.get("zipCode"));
        System.out.println("cityOfZipCode: "+testArguments.get("cityOfZipCode"));
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("startDate: "+testArguments.get("startDate"));
        System.out.println("endDate: "+testArguments.get("endDate"));
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("icaoCode: "+testArguments.get("icaoCode"));
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("startDate: "+testArguments.get("startDate"));
        System.out.println("endDate: "+testArguments.get("endDate"));
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        System.out.println("gpsCoords: "+testArguments.get("gpsCoords"));
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        System.out.println("dashboard name: "+testArguments.get("dashboardName"));
        dashboardPage.selectValidName(validDashboardName);
        System.out.println("startDate: "+testArguments.get("startDate"));
        System.out.println("endDate: "+testArguments.get("endDate"));
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @AfterMethod
    public void deleteAddedDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validAdminUsername, true);
        logInPage.providePassword(validAdminPassword, true);
        dashboardPage.deleteDashboard(validDashboardName);
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("deleteAddedDashboard");
    }
}
