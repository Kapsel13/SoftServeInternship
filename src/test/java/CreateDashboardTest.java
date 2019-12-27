import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

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
    @BeforeMethod
    public void setUp() {
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
        zipCode = TestData.getRandomZipCode();
        icaoCode = TestData.getRandomIcaoCodes();
        cityOfZipCode = TestData.cities.get(TestData.zipCodes.indexOf(zipCode));
        cityOfIcaoCode = TestData.cities.get(TestData.icaoCodes.indexOf(icaoCode));
        startDate = TestData.getRandomStartDate();
        endDate = TestData.getRandomEndDate();
        gpsCoords = TestData.getRandomGpsCoords();
        newDashboardText = By.xpath("//span[contains(text(),'"+validDashboardName+"')]");
        dashboardPage = new DashboardPage(driver,wait);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingActiveDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingInActiveDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCreatingCustomRangeDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnCLosingCreateDashboardWindow(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
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

    @AfterMethod
    public void deleteAddedDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validAdminUsername, true);
        logInPage.providePassword(validAdminPassword, true);
        dashboardPage.deleteDashboard(validDashboardName);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
