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

}
