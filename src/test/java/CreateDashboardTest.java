import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import pages.DashboardPage;

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

    @Before
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

    }

    @Test
    public void testResponseOnCreatingActiveDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingActiveDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingActiveDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingActiveDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingInActiveDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingInActiveDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test
    public void testResponseOnCreatingInActiveDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test
    public void testResponseOnCreatingInActiveDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setInActiveMonitoring();
        dashboardPage.createNewDashboard(newDashboardText);
    }

    @Test
    public void testResponseOnCreatingCustomRangeDashboardWithCityAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingCustomRangeDashboardWithZipCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(zipCode,cityOfZipCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingCustomRangeDashboardWithIcaoCodeAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocation(icaoCode,icaoCode);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);

    }

    @Test
    public void testResponseOnCreatingCustomRangeDashboardWithGpsCoordsAsLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreateDashboardTest(validAdminUsername,validAdminPassword);
        dashboardPage.selectValidLocationWithCoordinates(gpsCoords);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setCustomRangeMonitoring(TestData.rnd,startDate,endDate);
        dashboardPage.createNewDashboard(newDashboardText);

    }
}
