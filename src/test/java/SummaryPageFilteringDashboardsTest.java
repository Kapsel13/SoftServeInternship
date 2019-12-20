import org.testng.annotations.Test;
import pages.LogInPage;
import pages.SummaryPage;

public class SummaryPageFilteringDashboardsTest extends TestPreparation{
    @Test
    public void testResponseOnFilteringDashboardsByCriticalAlerts(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.filterDashboardsByCriticalAlerts();
    }
    @Test
    public void testResponseOnFilteringDashboardsByNumberOfAlerts(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.filterDashboardsByNumberOfAlerts();
    }
    @Test
    public void testResponseOnFilteringDashboardsByTypeOfAlert(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.filterDashboardByAlertType();
    }
}
