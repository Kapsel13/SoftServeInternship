import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.CurrentDataAlertPanel;
import panels.CurrentWeatherConditonsPanel;

public class CurrentWeatherConditionsPanelTest extends TestPreparation {

    @Test(priority = 1)
    public void testAddingCurrentWeatherConditionsPanelWithUsUnits(){
       DashboardPage dashboardPage = new DashboardPage(driver,wait);
       dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
       CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
       currentWeatherConditonsPanel.addNewCurrentWeatherConditionalPanel("U");
    }

    @Test(priority = 2)
    public void testAddingCurrentWeatherConditionsPanelWithMetricsUnits(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
        currentWeatherConditonsPanel.addNewCurrentWeatherConditionalPanel("M");
    }
}
