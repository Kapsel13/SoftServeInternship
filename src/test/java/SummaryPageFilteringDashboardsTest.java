import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.SummaryPage;

public class SummaryPageFilteringDashboardsTest extends TestPreparation{
    SummaryPage summaryPage;
    @BeforeMethod
    public void setUp(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        summaryPage = new SummaryPage(driver,wait);
    }
    @Test
    public void testResponseOnFilteringDashboardsByCriticalAlerts(){
        summaryPage.filterDashboardsByCriticalAlerts();
    }
    @Test
    public void testResponseOnFilteringDashboardsByNumberOfAlerts(){
        summaryPage.filterDashboardsByNumberOfAlerts();
    }
    @Test
    public void testResponseOnFilteringDashboardsByTypeOfAlert(){
        summaryPage.filterDashboardByAlertType();
    }
}
