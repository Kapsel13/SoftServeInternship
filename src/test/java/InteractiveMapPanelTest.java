import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.InteractiveMapPanel;

public class InteractiveMapPanelTest extends TestPreparation {


    @Test
    public void testAddingInteractiveMapPanel(){
       DashboardPage dashboardPage = new DashboardPage(driver,wait);
       dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
        InteractiveMapPanel interactiveMapPanel = new InteractiveMapPanel(driver,wait);
        interactiveMapPanel.addNewInteractiveMapPanel();
    }

}
