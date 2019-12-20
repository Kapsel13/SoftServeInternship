import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InteractiveMapPage;


public class InteractiveMapPageLayersTest extends TestPreparation {
    InteractiveMapPage interactiveMapPage;
    @BeforeMethod
    public void setUp(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testAddingNewLayerToInteractiveMapPage(){
        interactiveMapPage.addNewLayerToInteractiveMapPage(validAdminUsername,validAdminPassword);

    }
   @Test(retryAnalyzer = Retry.class)
   public void testSettingLightningIconTypeToInteractiveMapPage(){
       interactiveMapPage.setLightningIconTypeToInteractiveMapPage(validAdminUsername,validAdminPassword);
   }
    @Test(retryAnalyzer = Retry.class)
    public void testDeletingLayerFromInteractiveMapPage(){
        interactiveMapPage.deleteLayerFromInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @AfterMethod
    public void deleteLayer(){
        interactiveMapPage.logOut();
        interactiveMapPage.deleteLayerFromInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
}
