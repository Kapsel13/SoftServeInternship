import org.testng.annotations.Test;
import pages.InteractiveMapPage;

public class InteractiveMapPageLayersTest extends TestPreparation {
    @Test(retryAnalyzer = Retry.class)
    public void testAddingNewLayerToInteractiveMapPage(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.addNewLayerToInteractiveMapPage(validAdminUsername,validAdminPassword);

    }
    @Test(retryAnalyzer = Retry.class)
    public void testDeletingLayerFromInteractiveMapPage(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.deleteLayerFromInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
}
