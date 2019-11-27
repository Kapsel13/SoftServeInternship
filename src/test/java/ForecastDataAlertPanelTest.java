import org.junit.Test;
import pages.DashboardPage;
import panels.ForecastDataAlertPanel;

public class ForecastDataAlertPanelTest extends TestPreparation {

 @Test
    public void testAddingForecastDataAlertPanel(){
     DashboardPage dashboardPage = new DashboardPage(driver,wait);
     dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
     ForecastDataAlertPanel forecastDataAlertPanel = new ForecastDataAlertPanel(driver,wait);
     forecastDataAlertPanel.addForecastDataAlertPanel();
 }
}
