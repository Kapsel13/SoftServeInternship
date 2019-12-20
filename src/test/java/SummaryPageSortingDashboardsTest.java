import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.SummaryPage;

public class SummaryPageSortingDashboardsTest extends TestPreparation {
    SummaryPage summaryPage;
    @BeforeMethod
    public void setUp(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
         summaryPage = new SummaryPage(driver,wait);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsAlphabetically(){
        summaryPage.sortDashboardsAlphabetically();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsAlphabeticallyInReverseOrder(){
        summaryPage.sortDashboardInReverseAlphabeticalOrder();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsByTotalAlertsNumber(){
        summaryPage.sortDashboardByNumberOfAlerts();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsByCriticalAlertsNumber(){
        summaryPage.sortDashboardsByNumberOfCriticalAlerts();
    }
}
