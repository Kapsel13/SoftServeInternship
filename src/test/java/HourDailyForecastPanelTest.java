import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.HourDailyForecastPanel;

public class HourDailyForecastPanelTest extends TestPreparation{

    @Test(retryAnalyzer = Retry.class)
    public void testAddingHourAndDailyForecastPanel(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        HourDailyForecastPanel hourDailyForecastPanel = new HourDailyForecastPanel(driver,wait);
        hourDailyForecastPanel.addNewHourDailyForecastPanel();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testDisplayingDayTag(){
        HourDailyForecastPanel hourDailyForecastPanel = new HourDailyForecastPanel(driver,wait);
        hourDailyForecastPanel.checkDisplayingContentOfDayTag(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testDisplayNightTag(){
        HourDailyForecastPanel hourDailyForecastPanel = new HourDailyForecastPanel(driver,wait);
        hourDailyForecastPanel.checkDisplayContentOfNightTag(validAdminUsername,validAdminPassword);
    }
}
