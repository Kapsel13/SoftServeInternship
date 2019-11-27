package panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;

import java.util.Arrays;
import java.util.Random;

public class CurrentWeatherConditonsPanel extends BasePanel {

    private By currentWeatherPanelOption = By.xpath("//div[@class='panel-title' and contains(., 'Current Weather')]");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By currentWeatherConditionTagText = By.xpath("(//span[contains(text(),'Current Conditions')])[1]");
    private By usUnitsSign = By.xpath("(//span[@class='units' and contains(text(),'F')])[1]");
    private By metricUnitsOption = By.xpath("//div[contains(text(),'Metric')]");
    private By metricUnitsSign = By.xpath("(//span[@class='units' and contains(text(),'C')])[1]");
    private By temperatureNumber = By.xpath("(//span[@class='primary-temp'])[1]");
    public CurrentWeatherConditonsPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }


    public void addNewCurrentWeatherConditionalPanel(String units){
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentWeatherPanelOption));
        driver.findElement(currentWeatherPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        if(units.equals("M")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(metricUnitsOption));
            driver.findElement(metricUnitsOption).click();
        }
        driver.findElement(addPanelButton).click();
        if(units.equals("M")){
            waitUntillAllElementsVisible(Arrays.asList(currentWeatherConditionTagText,metricUnitsSign,temperatureNumber));
        }
        else {
            waitUntillAllElementsVisible(Arrays.asList(currentWeatherConditionTagText,usUnitsSign,temperatureNumber));
        }
    }


}
