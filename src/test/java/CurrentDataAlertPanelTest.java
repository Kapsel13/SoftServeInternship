import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.CurrentDataAlertPanel;

public class CurrentDataAlertPanelTest extends TestPreparation {
    @Test
    public void testAddingCurrentDataAlertPanel(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        CurrentDataAlertPanel currentDataAlertPanel = new CurrentDataAlertPanel(driver,wait);
        currentDataAlertPanel.addCurrentDataAlertPanel();
    }
}
