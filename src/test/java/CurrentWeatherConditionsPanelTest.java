import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.CurrentDataAlertPanel;
import panels.CurrentWeatherConditonsPanel;

public class CurrentWeatherConditionsPanelTest extends TestPreparation {

    @Test
    public void testAddingCurrentWeatherConditionsPanelWithUsUnits(){
       DashboardPage dashboardPage = new DashboardPage(driver,wait);
       dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
       CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
       currentWeatherConditonsPanel.addNewCurrentWeatherConditionalPanel("U");
    }

    @Test
    public void testAddingCurrentWeatherConditionsPanelWithMetricsUnits(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
        currentWeatherConditonsPanel.addNewCurrentWeatherConditionalPanel("M");
    }
}
