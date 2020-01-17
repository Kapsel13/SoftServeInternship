package panels;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LogInPage;

import java.beans.Expression;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;


public class CurrentDataAlertPanel extends BasePanel {

    protected static Random rnd = new Random();
    private By currentDataAlertPanelOption = By.xpath("//div[@class='panel-title' and contains(.,'Current Data')]");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By weatherTypes = By.xpath("//input[@class='phoenix-typeahead-list-item ng-star-inserted']");
    private String weatherTypeInAList = "(//input[@class='phoenix-typeahead-list-item ng-star-inserted'])[%d]";
    private By dataConfirmButton = By.xpath("//form[not(contains(@class,'ng-untouched'))]//button[contains(text(),'Next')]");
    private By triggerTypeConfirmButton = By.xpath("//form[contains(.,'Preview: Alert Mode')]//button[contains(text(),'Next')]");
    private By alertMessageConfirmButton = By.xpath("//form[contains(.,'Preview: Default alert message')]//button[contains(text(),'Next')]");
    private By notificationConfirmButton = By.xpath("//form[contains(.,'Preview: Mobile notification')]//button[contains(text(),'Next')]");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By selectWeatherTypeButton = By.xpath("//div[@class='phoenix-typeahead-input-arrow']");
    private By timeOfAlert = By.xpath("(//mat-sidenav-content//time[contains(@class,'data-update-time')])[1]");
    private By unitsOfAlert = By.xpath("(//div[contains(@class,'data-value')])[1]");
    private By panelHeaders = By.xpath("//p[@class='header-title']");
    private By panelHeader1 = By.xpath("(//p[@class='header-title'])[1]");
    private By panelHeader2 = By.xpath("(//p[@class='header-title'])[2]");
    private By unitsDropdownArrow = By.xpath("//button[contains(@class,'w-127')]//svg-icon");
    private By units = By.xpath("//button[contains(@class,'dropdown-item') and contains(@class,'w-127')]//span");
    private String unit = "(//button[contains(@class,'dropdown-item') and contains(@class,'w-127')]//span)[%d]";
    private By unitNumberInput = By.xpath("//input[contains(@class,'stepper-input')]");
    private By lessThanButton = By.xpath("//div[contains(text(),'Less Than')]");
    private By alertTypeDropdownArrow = By.xpath("//div[@class='pt-22']//svg-icon[contains(@class,'expand-dropdown-icon')]");
    private By alertTypes = By.xpath("//button[contains(@class,'w-300')]//span[@class='dropdown-item-title']");
    private String alertType = "(//button[contains(@class,'w-300')]//span[@class='dropdown-item-title'])[%d]";
    private By acceptAlertMessageOption = By.xpath("(//div[contains(text(),'Yes')])[1]");
    private By messageArea = By.xpath("//textarea");
    private By rejectNotificationSending = By.xpath("(//div[@class='mat-button-toggle-label-content' and contains(text(),'No')])[2]");
    private By alertPreview = By.xpath("//div[@class='mat-button-toggle-label-content' and contains(text(),'Alert')]");
    private By selectWeatherInput = By.xpath("//input[@placeholder='Select weather option']");
    private By  disabledConfirmPanelTypeButton = By.xpath("//form[not(contains(@class,'ng-touched'))]//button[contains(text(),'Next')]");
    //private By currentDataAlertPanel = By.xpath("//header[contains(@class,'value-panel-header')]");
    protected By addPanelOption = By.xpath("//span[contains(text(),'Add Panel')]");
    private By settingsIcon = By.xpath("//div[@class='header-menu-icon']");
    private By editOption = By.xpath("//button[contains(@class,'mat-menu-item') and contains(.,'Edit')]");
    private boolean noUnitsDropdown = false;
    private By panelHeader = By.xpath("(//div[contains(@class,'header-text')])[1]");
    private By dataUnits = By.xpath("//span[@class='data-units']");
    private By dataNumber = By.xpath("//div[contains(@class,'data-value-text')]");
    private By saveEditionButton = By.xpath("//button[contains(text(),'Save')]");
    public CurrentDataAlertPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void addCurrentDataAlertPanel(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentDataAlertPanelOption));
        driver.findElement(currentDataAlertPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(selectWeatherTypeButton));
        driver.findElement(selectWeatherTypeButton).click();
        int numberOfWeatherTypes = driver.findElements(weatherTypes).size();
        int weatherTypesIndex = rnd.nextInt(numberOfWeatherTypes-1)+1;
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        WebElement weatherType = dashboardPage.scrollElementIntoView(By.xpath(String.format(weatherTypeInAList,weatherTypesIndex)));
        String weatherTypeText = weatherType.getAttribute("value");
        String jsonWeatherText = weatherTypeText;
        System.out.println("weatherType: "+weatherTypeText);
        if((weatherTypeText.contains("Humidity"))||(weatherTypeText.contains("Pressure"))){
            noUnitsDropdown = true;
        }
        if(weatherTypeText.contains("-")){
            weatherTypeText=weatherTypeText.replace(" -","");
        }
        if(weatherTypeText.contains("Hr")){
            weatherTypeText = weatherTypeText.replace("Hr"," Hr");
        }
        weatherType.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dataConfirmButton));
        driver.findElement(dataConfirmButton).click();

        String unitInAListText = "";
        if(weatherTypeText.contains("Humidity")){
            unitInAListText = "%";
        }
        if(weatherTypeText.contains("Pressure")){
            unitInAListText = "mb";
        }
        if(!noUnitsDropdown) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(unitsDropdownArrow));
            driver.findElement(unitsDropdownArrow).click();
            int unitIndex = 0;
            int numberOfUnits = driver.findElements(units).size();
            if (numberOfUnits == 1) {
                unitIndex = 1;
            } else {
                unitIndex = rnd.nextInt(numberOfUnits - 1) + 1;
            }
            WebElement unitInAList = driver.findElement(By.xpath(String.format(unit, unitIndex)));
            unitInAListText = unitInAList.getText();
            System.out.println("units: " + unitInAListText);
            unitInAList.click();
        }
        try {
            if(!jsonWeatherText.contains("Departure")) {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/Users/mkowal/IdeaProjects/WeatherApplication/src/main/java/panels/params-ranges.json"));
                JSONObject currentDataPanelJson = (JSONObject) jsonObject.get("CDP");
                JSONObject weatherTypeJson = (JSONObject) currentDataPanelJson.get(jsonWeatherText);
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
        try{
            waitUntillAllElementsVisible(Arrays.asList(timeOfAlert,unitsOfAlert));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            waitUntillAllElementsVisible(Arrays.asList(timeOfAlert,unitsOfAlert));
        }
        waitUntillAllElementsVisible(Arrays.asList(timeOfAlert,unitsOfAlert));
        int panelHeadersNumber = driver.findElements(panelHeaders).size();
        String panelHeaderText = "";
        if(panelHeadersNumber == 1){
            panelHeaderText = driver.findElement(panelHeader1).getText();
        }
        else{
            panelHeaderText = driver.findElement(panelHeader2).getText();
        }
        Assert.assertEquals(weatherTypeText,panelHeaderText);
    }
    public void provideInvalidPanelType(String invalidWeatherOption){
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentDataAlertPanelOption));
        driver.findElement(currentDataAlertPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectWeatherInput));
        driver.findElement(selectWeatherInput).sendKeys(invalidWeatherOption);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(disabledConfirmPanelTypeButton)));
    }
    public void checkEditingCurrentDataAlertPanel(String username,String password){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("jhbfirjo hjfklsnlhndjsghudejgjgkdhdksghjdskhgkljsdhgklshjshgsjhdsjkgdjshgklshgjsgljkshlskd");
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.chooseActiveDashboard(rnd);
        dashboardPage.deleteAllPanelsFromDashboard();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile,new File("target/screenshots/screenshot2.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        }
        driver.findElement(addPanelOption).click();
        addCurrentDataAlertPanel();
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
        driver.findElement(settingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(panelHeader));
        WebElement paneHeaderContent = driver.findElement(panelHeader);
        String panelHeaderContentText = paneHeaderContent.getText();
        String unitSymbol = "";
        if(panelHeaderContentText.contains("Humidity")){
            unitSymbol = "%";
        }
        if(panelHeaderContentText.contains("Pressure")){
            unitSymbol = "mb";
        }
        if(!noUnitsDropdown) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(unitsDropdownArrow));
            driver.findElement(unitsDropdownArrow).click();
            int unitIndex = 0;
            int numberOfUnits = driver.findElements(units).size();
            if (numberOfUnits == 1) {
                unitIndex = 1;
            } else {
                unitIndex = rnd.nextInt(numberOfUnits - 1) + 1;
            }
            WebElement unitInAList = driver.findElement(By.xpath(String.format(unit, unitIndex)));
            unitSymbol = unitInAList.getText();
            System.out.println("units: " + unitSymbol);
            unitInAList.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(triggerTypeConfirmButton));
        driver.findElement(triggerTypeConfirmButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessageConfirmButton));
        driver.findElement(alertMessageConfirmButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationConfirmButton));
        driver.findElement(notificationConfirmButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveEditionButton));
        driver.findElement(saveEditionButton).click();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(dataUnits));
        WebElement dataUnitsContent = driver.findElement(dataUnits);
        String dataUnitsContentText = dataUnitsContent.getText();
        Assert.assertEquals(unitSymbol,dataUnitsContentText);
    }
}
