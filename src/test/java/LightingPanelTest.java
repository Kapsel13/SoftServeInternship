import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.LightingPanel;

public class LightingPanelTest extends TestPreparation {
    @Test
    public void testAddingLightingPanelTest(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        LightingPanel lightingPanel = new LightingPanel(driver,wait);
        lightingPanel.addLightingPanel();
    }
    @Test
    public void testAddingNewRangeToLightingPanel(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        LightingPanel lightingPanel = new LightingPanel(driver,wait);
        lightingPanel.addRange();
    }
    @Test
    public void testDeletingRangeFromLightingPanel(){
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        LightingPanel lightingPanel = new LightingPanel(driver,wait);
        lightingPanel.deleteRange();
    }

}
