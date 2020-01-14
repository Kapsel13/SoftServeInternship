import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;

import java.util.Random;

public class DuplicateDashboardTest extends TestPreparation {
    Random rnd;
    @BeforeMethod
    public void setUp(){
        rnd = new Random();
    }
    @Test
    public void testDuplicateDashboardStartingPoint(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.checkDuplicateDashboardStartingPoint(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testDuplicateDashboardLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.checkDuplicateDashboardLocation(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testDuplicateDashboardContent(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.checkDuplicateDashboardContent(validAdminUsername,validAdminPassword,rnd);
    }
}
