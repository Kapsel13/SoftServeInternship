import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.SummaryPage;

public class SummaryPageSortingDashboardsTest extends TestPreparation {
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsAlphabetically(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardsAlphabetically();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsAlphabeticallyInReverseOrder(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardInReverseAlphabeticalOrder();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsByTotalAlertsNumber(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardByNumberOfAlerts();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSortingDashboardsByCriticalAlertsNumber(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.sortDashboardsByNumberOfCriticalAlerts();
    }
}
