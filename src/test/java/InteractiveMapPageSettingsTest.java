import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InteractiveMapPage;

public class InteractiveMapPageSettingsTest extends TestPreparation {
    InteractiveMapPage interactiveMapPage;
    @Test
    public void testSettingLocationPins(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setLocationPinsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingRangeRings(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setRangeRingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingMapType() {
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setMapTypeSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingTemperatureUnit(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setTempUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingDistanceUnits(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setDistanceUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingHeightUnits(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setHeightUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingPressureUnits(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setPressureSettingsToInteractiveMap(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingSpeedUnits(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
        interactiveMapPage.setSpeedSettingsToInteractiveMap(validAdminUsername,validAdminPassword);
    }
}
