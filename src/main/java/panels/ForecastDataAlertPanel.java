package panels;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ForecastDataAlertPanel extends BasePanel {

    protected static Random rnd = new Random();
    private By forecastDataAlertPanelOption = By.xpath("//div[@class='panel-title']//span[contains(text(),'Forecast Data')]");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By selectWeatherTypeButton = By.xpath("//div[@class='phoenix-typeahead__input__arrow']");
    private By weatherTypes = By.xpath("//input[@class='phoenix-typeahead__list__item ng-star-inserted']");
    private String weatherTypeInAList = "(//input[@class='phoenix-typeahead__list__item ng-star-inserted'])[%d]";
    private By dataConfirmButton = By.xpath("//form[not(contains(@class,'ng-untouched'))]//button[contains(text(),'Next')]");
    private By triggerTypeConfirmButton = By.xpath("//form[contains(.,'Preview: Alert Mode')]//button[contains(text(),'Next')]");
    private By alertMessageConfirmButton = By.xpath("//form[contains(.,'Preview: Default alert message')]//button[contains(text(),'Next')]");
    private By notificationConfirmButton = By.xpath("//form[contains(.,'Preview: Email Notification')]//button[contains(text(),'Next')]");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By timeOfAlert = By.xpath("(//time[contains(@class,'preview-update-time')])[1]");
    private By titleOfChart = By.xpath("(//span[@class='highcharts-legend-item'])[1]");
    private By panelHeader = By.xpath("(//span[@class='forecast-header-parameter'])[1]");
    private By forecastRangeTimeUnitsDropdown = By.xpath("//button[contains(@class,'w-114')]//svg-icon");
    private By forecastRangeTimeUnits = By.xpath("//div[contains(@class,'w-114')]//button//span");
    private String forecastRangeTimeUnit = "(//div[contains(@class,'w-114')]//button//span)[%d]";
    private By forecastRangeMeasureUnitDropdown = By.xpath("//div[contains(@class,'w-83')]//button//svg-icon");
    private By forecastRangeMeasureUnits = By.xpath("//div[contains(@class,'w-83')]//button//span");
    private String forecastRangeMeasureUnit = "(//div[contains(@class,'w-83')]//button//span)[%d]";
    private By unitsDropdownArrow = By.xpath("//button[contains(@class,'w-127')]//svg-icon");
    private By units = By.xpath("//button[contains(@class,'dropdown-item') and contains(@class,'w-127')]//span");
    private String unit = "(//button[contains(@class,'dropdown-item') and contains(@class,'w-127')]//span)[%d]";
    private By unitNumberInput = By.xpath("//input[contains(@class,'stepper-input')]");
    private By alertTypeDropdownArrow = By.xpath("//div[@class='pt-22']//svg-icon[contains(@class,'expand-dropdown-icon')]");
    private By alertTypes = By.xpath("//button[contains(@class,'w-300')]//span[@class='dropdown-item-title']");
    private String alertType = "(//button[contains(@class,'w-300')]//span[@class='dropdown-item-title'])[%d]";
    private By acceptAlertMessageOption = By.xpath("(//div[contains(text(),'Yes')])[1]");
    private By messageArea = By.xpath("//textarea");
    private By rejectNotificationSending = By.xpath("(//div[@class='mat-button-toggle-label-content' and contains(text(),'No')])[2]");
    private By alertPreview = By.xpath("//div[@class='mat-button-toggle-label-content' and contains(text(),'Alert')]");
    private By lessThanButton = By.xpath("//div[contains(text(),'Less Than')]");
    public ForecastDataAlertPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void addForecastDataAlertPanel(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(forecastDataAlertPanelOption));
        driver.findElement(forecastDataAlertPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(selectWeatherTypeButton));
        driver.findElement(selectWeatherTypeButton).click();
        int numberOfWeatherTypes = driver.findElements(weatherTypes).size();
        int weatherTypesIndex = rnd.nextInt(numberOfWeatherTypes-1)+1;
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        WebElement weatherType = dashboardPage.scrollElementIntoView(By.xpath(String.format(weatherTypeInAList,weatherTypesIndex)));
        String weatherTypeText = weatherType.getAttribute("value");
        String jsonWeatherTypeText = weatherTypeText;
        System.out.println("weatherType: "+weatherTypeText);
        if(weatherTypeText.contains("Temp ")){
            weatherTypeText=weatherTypeText.replace("Temp ","Temperature ");
        }
        weatherType.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(forecastRangeTimeUnitsDropdown));
        driver.findElement(forecastRangeTimeUnitsDropdown).click();
        int numberOfForecastRangeTimeUnits = driver.findElements(forecastRangeTimeUnits).size();
        int forecastRangeTimeUnitIndex = 0;
        if(numberOfForecastRangeTimeUnits == 1){
            forecastRangeTimeUnitIndex = 1;
        }
        else {
            forecastRangeTimeUnitIndex = rnd.nextInt(numberOfForecastRangeTimeUnits-1)+1;
        }
        WebElement chooseForecastTimeUnit = driver.findElement(By.xpath(String.format(forecastRangeTimeUnit,forecastRangeTimeUnitIndex)));
        String chooseForecastTimeUnitText = chooseForecastTimeUnit.getText();
        System.out.println("rangeTimeUnit: "+chooseForecastTimeUnitText);
        chooseForecastTimeUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(forecastRangeMeasureUnitDropdown));
        driver.findElement(forecastRangeMeasureUnitDropdown).click();
        int numberOfForecastRangeMeasureUnits = driver.findElements(forecastRangeMeasureUnits).size();
        int forecastRangeMeasureUnitIndex = rnd.nextInt(numberOfForecastRangeMeasureUnits-1)+1;
        WebElement chooseForecastRangeMeasureUnit = dashboardPage.scrollElementIntoView(By.xpath(String.format(forecastRangeMeasureUnit,forecastRangeMeasureUnitIndex)));
        String chooseForecastRangeMeasureUnitText = chooseForecastRangeMeasureUnit.getText();
        System.out.println("rangeMeasureUnit: "+chooseForecastRangeMeasureUnitText);
        chooseForecastRangeMeasureUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dataConfirmButton));
        driver.findElement(dataConfirmButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(unitsDropdownArrow));
        driver.findElement(unitsDropdownArrow).click();
        int unitIndex = 0;
        int numberOfUnits = driver.findElements(units).size();
        if(numberOfUnits == 1){
            unitIndex = 1;
        }
        else{
            unitIndex = rnd.nextInt(numberOfUnits-1)+1;
        }
        WebElement unitInAList = driver.findElement(By.xpath(String.format(unit,unitIndex)));
        String unitInAListText = unitInAList.getText();
        System.out.println("units: "+unitInAListText);
        unitInAList.click();
        try {
            if(!jsonWeatherTypeText.contains("Normal")) {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/Users/mkowal/IdeaProjects/WeatherApplication/src/main/java/panels/params-ranges.json"));
                JSONObject currentDataPanelJson = (JSONObject) jsonObject.get("FDP");
                JSONObject weatherTypeJson = (JSONObject) currentDataPanelJson.get(jsonWeatherTypeText);
                JSONObject unitJson = (JSONObject) weatherTypeJson.get(unitInAListText);
                int max = Integer.parseInt(String.valueOf(unitJson.get("max"))) - 1;
                int min = Integer.parseInt(String.valueOf(unitJson.get("min"))) + 1;
                int unitNumber = rnd.nextInt(max - min) + min;
                System.out.println("unitNumber: " + unitNumber);
                driver.findElement(unitNumberInput).sendKeys(String.valueOf(unitNumber));
            }
        } catch (
                FileNotFoundException e) {
        } catch (IOException | ParseException e) {
        }
        int comparisionOption = rnd.nextInt(2);
        if(comparisionOption == 1){
            WebElement chooseComparisionOption = driver.findElement(lessThanButton);
            String chooseComparisionOptionText = chooseComparisionOption.getText();
            System.out.println("comparisionOption: "+chooseComparisionOptionText);
            chooseComparisionOption.click();
        }
        else{
            System.out.println("comparisionOption: Greater than");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertTypeDropdownArrow));
        driver.findElement(alertTypeDropdownArrow).click();
        int numberOfAlertTypes = driver.findElements(alertTypes).size();
        int alertTypeIndex = rnd.nextInt(numberOfAlertTypes-1)+1;
        WebElement chooseAlertType = driver.findElement(By.xpath(String.format(alertType,alertTypeIndex)));
        String chooseAlertTypeText = chooseAlertType.getText();
        System.out.println("alertType: "+chooseAlertTypeText);
        chooseAlertType.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(triggerTypeConfirmButton));
        driver.findElement(triggerTypeConfirmButton).click();


        int alertMessageOption = rnd.nextInt(2);
        if(alertMessageOption == 1){
            WebElement chooseAlertMessageOption = driver.findElement(acceptAlertMessageOption);
            String chooseAlertMessageOptionText = chooseAlertMessageOption.getText();
            System.out.println("alertMessageOption: "+chooseAlertMessageOptionText);
            chooseAlertMessageOption.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(messageArea));
            String text = generateAdditionalAlertMessage();
            driver.findElement(messageArea).sendKeys(text);
            System.out.println("alert message: "+text);
        }
        else{
            System.out.println("alertMessageOption: No");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessageConfirmButton));
        driver.findElement(alertMessageConfirmButton).click();


        int sendNotificationOption = rnd.nextInt(2);
        if(sendNotificationOption == 0){
            WebElement chooseNotificationOption = driver.findElement(rejectNotificationSending);
            String chooseNotificationOptionText = chooseNotificationOption.getText();
            System.out.println("sendNotification: "+chooseNotificationOptionText);
            chooseNotificationOption.click();
        }
        else{
            System.out.println("sendNotification: Yes");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationConfirmButton));
        driver.findElement(notificationConfirmButton).click();


        int previewOption = rnd.nextInt(2);
        if(previewOption == 0){
            WebElement choosePreviewOption = driver.findElement(alertPreview);
            String choosePreviewOptionText = choosePreviewOption.getText();
            System.out.println("previewOption: "+choosePreviewOptionText);
            choosePreviewOption.click();
        }
        else{
            System.out.println("previewOption: Normal");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        waitUntillAllElementsVisible(Arrays.asList(timeOfAlert,titleOfChart));
        String panelHeaderText = driver.findElement(panelHeader).getText();
        Assert.assertEquals(weatherTypeText,panelHeaderText);
    }
}
