package panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class HourDailyForecastPanel extends BasePanel {

    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By hoursAndDailyForecastPanelOption = By.xpath("//div[@class='panel-title' and contains(., 'Hourly & Daily')]");
    private By hoursAndDailyForecastTagText = By.xpath("//p[contains(text(),'Hourly Forecast')]");
    private By hoursAndDailyForecastTime = By.xpath("(//h3[@class='time'])[1]");
    private By hoursAndDailyForecastMenuHourOption = By.xpath("(//div[contains(@class,'controls') and contains(.,'Hourly')])[1]");

    public HourDailyForecastPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }


    public void addNewHourDailyForecastPanel(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoursAndDailyForecastPanelOption));
        driver.findElement(hoursAndDailyForecastPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        waitUntillAllElementsVisible(Arrays.asList(hoursAndDailyForecastTagText,hoursAndDailyForecastTime,hoursAndDailyForecastMenuHourOption));
    }
}
