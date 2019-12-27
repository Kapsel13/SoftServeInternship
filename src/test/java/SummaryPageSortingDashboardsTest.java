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
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsAlphabetically(){
        summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardsAlphabetically();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsAlphabeticallyInReverseOrder(){
        summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardInReverseAlphabeticalOrder();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsByTotalAlertsNumber(){
        summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardByNumberOfAlerts();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsByCriticalAlertsNumber(){
        summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardsByNumberOfCriticalAlerts();
    }
}
