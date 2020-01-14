import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InteractiveMapPage;

public class InteractiveMapPageSettingsTest extends TestPreparation {
    @Test(retryAnalyzer = Retry.class)
    public void testSettingLocationPins(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setLocationPinsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingRangeRings(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setRangeRingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingMapType() {
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setMapTypeSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingTemperatureUnit(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setTempUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingDistanceUnits(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setDistanceUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingHeightUnits(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setHeightUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingPressureUnits(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setPressureSettingsToInteractiveMap(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testSettingSpeedUnits(){
        InteractiveMapPage interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setSpeedSettingsToInteractiveMap(validAdminUsername,validAdminPassword);
    }
}
