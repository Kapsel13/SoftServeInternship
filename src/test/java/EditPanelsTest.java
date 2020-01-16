import org.junit.Before;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import panels.CurrentDataAlertPanel;
import panels.CurrentWeatherConditonsPanel;

import java.util.Random;

public class EditPanelsTest extends TestPreparation
{
    Random rnd;
    @BeforeMethod
    public void setUp(){
        rnd = new Random();
    }
    @Test(retryAnalyzer = Retry.class)
    public void testEditingCurrentWeatherConditionsPanel(){
        CurrentWeatherConditonsPanel currentWeatherConditonsPanel = new CurrentWeatherConditonsPanel(driver,wait);
        currentWeatherConditonsPanel.checkEditingCurrentWeatherConditionsPanel(validAdminUsername,validAdminPassword,rnd);
    }
    /*@Test
    public void testEditingCurrentDataAlertPanel(){
        CurrentDataAlertPanel currentDataAlertPanel = new CurrentDataAlertPanel(driver,wait);
        currentDataAlertPanel.checkEditingCurrentDataAlertPanel(validAdminUsername,validAdminPassword);
    }*/
}
