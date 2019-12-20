import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.CurrentWeatherConditonsPanel;

public class CurrentWeatherConditionsPanelTest extends TestPreparation {

    @Test(retryAnalyzer = Retry.class)
    public void testAddingCurrentWeatherConditionsPanelWithUsUnits(){
       DashboardPage dashboardPage = new DashboardPage(driver,wait);
       dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
       CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
       currentWeatherConditonsPanel.addNewCurrentWeatherConditionalPanel("U");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAddingCurrentWeatherConditionsPanelWithMetricsUnits(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
        currentWeatherConditonsPanel.addNewCurrentWeatherConditionalPanel("M");
    }
}
