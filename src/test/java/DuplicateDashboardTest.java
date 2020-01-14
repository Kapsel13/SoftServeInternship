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
    @Test(retryAnalyzer = Retry.class)
    public void testDuplicateDashboardStartingPoint(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.checkDuplicateDashboardStartingPoint(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testDuplicateDashboardLocation(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.checkDuplicateDashboardLocation(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testDuplicateDashboardContent(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.checkDuplicateDashboardContent(validAdminUsername,validAdminPassword,rnd);
    }
}
