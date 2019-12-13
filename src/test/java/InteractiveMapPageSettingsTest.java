import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InteractiveMapPage;

public class InteractiveMapPageSettingsTest extends TestPreparation {
    InteractiveMapPage interactiveMapPage;
    @BeforeMethod
    public void setUp(){
        interactiveMapPage = new InteractiveMapPage(driver,wait);
    }
    @Test
    public void testSettingLocationPins(){
        interactiveMapPage.setLocationPinsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingRangeRings(){
        interactiveMapPage.setRangeRingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingMapType() {
        interactiveMapPage.setMapTypeSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingTemperatureUnit(){
        interactiveMapPage.setTempUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingDistanceUnits(){
        interactiveMapPage.setDistanceUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingHeightUnits(){
        interactiveMapPage.setHeightUnitSettingsToInteractiveMapPage(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingPressureUnits(){
        interactiveMapPage.setPressureSettingsToInteractiveMap(validAdminUsername,validAdminPassword);
    }
    @Test
    public void testSettingSpeedUnits(){
        interactiveMapPage.setSpeedSettingsToInteractiveMap(validAdminUsername,validAdminPassword);
    }
}
