package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;


public class InteractiveMapPage extends BasePage {
    protected static Random rnd = new Random();
    private String interactivePageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Interactive Map')]";
    private By layerOption = By.xpath("//mat-icon[contains(@class,'options-toggle-icon-off')]");
    private By filter = By.xpath("//mat-chip");
    private String specificFilter = "(//mat-chip)[%d]";
    private By layer = By.xpath("//div[@class='left']//div[contains(@class,'select-layer-button')]");
    private String specificLayer = "(//div[@class='left']//div[contains(@class,'select-layer-button')])[%d]";
    private By layerIconContainer = By.xpath("//div[contains(@class,'layer-icon')]");
    private String specificLayerIconContainer = "(//div[contains(@class,'layer-icon')])[%d]";
    private String layerIcon = "//mat-icon[@title='%s']";
    private By deleteLayerIcon = By.xpath("//mat-icon[contains(@class,'trash-icon')]");
    private String layerName = "";
    private By layerToDeleteName = By.xpath("//div[@class='headerContent']//span[@class='title']");
    private By map = By.xpath("//canvas");
    private By mapSettingsIcon = By.xpath("//div[@class='map-settings']");
    private By viewLocationPinsOption = By.xpath("(//span[@class='toggle']//mat-icon[contains(@class,'ng-star-inserted')])[2]");
    private By rangeRingsOpiton = By.xpath("(//span[@class='toggle']//mat-icon[contains(@class,'ng-star-inserted')])[1]");
    private By mapTypeDropdownIcon = By.xpath("//mat-icon[contains(text(),'keyboard_arrow_down')]");
    private By mapTypeOption = By.xpath("//div[contains(@class,'dropdown-menu')]//button[contains(@class,'dropdown-item')]");
    private String specificMapTypeOption = "(//div[contains(@class,'dropdown-menu')]//button[contains(@class,'dropdown-item')])[%d]";
    private By editOption = By.xpath("//span[contains(text(),'EDIT')]");
    private By temperatureUnits = By.xpath("(//div[@class='switch'])[1]/button");
    private String specificTemperatureUnit = "((//div[@class='switch'])[1]/button)[%d]";
    private By doneButton = By.xpath("//button[@class='done']");
    private By distanceUnits = By.xpath("(//div[@class='switch'])[2]/button");
    private String specificDistanceUnit = "((//div[@class='switch'])[2]/button)[%d]";
    private By heightUnits = By.xpath("(//div[@class='switch'])[4]/button");
    private String specificHeightUnit = "((//div[@class='switch'])[4]/button)[%d]";
    private By pressureUnits = By.xpath("(//div[@class='switch'])[5]/button");
    private String specificPressureUnit = "((//div[@class='switch'])[5]/button)[%d]";
    private By speedUnits = By.xpath("(//div[@class='switch'])[6]/button");
    private String specificSpeedUnit = "((//div[@class='switch'])[6]/button)[%d]";
    public InteractiveMapPage(WebDriver driver, WebDriverWait wait){super(driver,wait);}
    public WebElement scrollElementIntoView(By by) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
        return webElement;
    }
    public void beginTestInteractiveMapPage(String validUsername,String validPassword){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validUsername,true);
        logInPage.providePassword(validPassword,true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(interactivePageLink,""))));
        driver.findElement(By.xpath(String.format(interactivePageLink,""))).click();
    }
    public void addNewLayerToInteractiveMapPage(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(layerOption));
        driver.findElement(layerOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(filter));
        int filterIndex = rnd.nextInt(3)+1;
        driver.findElement(By.xpath(String.format(specificFilter,filterIndex))).click();
        int numberOfLayers = driver.findElements(layer).size();
        int layerIndex = rnd.nextInt(numberOfLayers-1)+1;
        WebElement layer = driver.findElement(By.xpath(String.format(specificLayer,layerIndex)));
        layerName = layer.getText();
        layer.click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(layerIcon,layerName))));
    }
    public void deleteLayerFromInteractiveMapPage(String validUsername,String validPassword){
        boolean delete = true;
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(layerIconContainer));
        }catch(TimeoutException e){
            delete = false;
        }
        if(delete){
            int indexOfLayerToDelete = 0;
            int numberOfLayersIcons = driver.findElements(layerIconContainer).size();
            if(numberOfLayersIcons==1){
                indexOfLayerToDelete = 1;
            }
            else{
                indexOfLayerToDelete = rnd.nextInt(numberOfLayersIcons-1)+1;
            }
            Actions action = new Actions(driver);
            WebElement layerToDelete = driver.findElement(By.xpath(String.format(specificLayerIconContainer,indexOfLayerToDelete)));
            action.moveToElement(layerToDelete).perform();
            waitUntillAllElementsVisible(Arrays.asList(deleteLayerIcon,layerToDeleteName));
            WebElement layerToDeleteHeader = driver.findElement(layerToDeleteName);
            layerName = layerToDeleteHeader.getText();
            driver.findElement(deleteLayerIcon).click();
            driver.navigate().refresh();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(layerIcon,layerName))));
        }
    }
    public void setLocationPinsToInteractiveMapPage(String validUsername,String validPassword){
        driver.navigate().refresh();
        beginTestInteractiveMapPage(validUsername,validPassword);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("target/screenshots/screenshot.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(map));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        }
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String locationPinsSettingsBefore = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].isLocationPinsOn",element).toString();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewLocationPinsOption));
        driver.findElement(viewLocationPinsOption).click();
        String locationPinsSettingsAfter = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].isLocationPinsOn",element).toString();
        if(locationPinsSettingsBefore == "false")
        {
            Assert.assertEquals(locationPinsSettingsAfter,"true");
        }
        else{
            Assert.assertEquals(locationPinsSettingsAfter,"false");
        }
    }
    public void setRangeRingsToInteractiveMapPage(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String rangeRingsSettingsBefore = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].areRangeRingsOn",element).toString();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rangeRingsOpiton));
        driver.findElement(rangeRingsOpiton).click();
        String rangeRingsSettingsAfter = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].areRangeRingsOn",element).toString();
        if(rangeRingsSettingsBefore == "false")
        {
            Assert.assertEquals(rangeRingsSettingsAfter,"true");
        }
        else{
            Assert.assertEquals(rangeRingsSettingsAfter,"false");
        }
    }
    public void setMapTypeSettingsToInteractiveMapPage(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapTypeDropdownIcon));
        driver.findElement(mapTypeDropdownIcon).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mapTypeOption));
        int numberOfMapTypeOptions = driver.findElements(mapTypeOption).size();
        int indexOfMapTypeOption = rnd.nextInt(numberOfMapTypeOptions-1)+1;
        WebElement mapType = driver.findElement(By.xpath(String.format(specificMapTypeOption,indexOfMapTypeOption)));
        String mapTypeText = mapType.getText();
        mapType.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String mapTypeSetting= ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].mapTypeId",element).toString();
        Assert.assertEquals(mapTypeSetting,mapTypeText.toUpperCase());
    }
    public void setTempUnitSettingsToInteractiveMapPage(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(temperatureUnits));
        int numberOfTemperatureUnits = driver.findElements(temperatureUnits).size();
        int indexOfTemperatureUnits = rnd.nextInt(numberOfTemperatureUnits-1)+1;
        WebElement temperatureUnit = driver.findElement(By.xpath(String.format(specificTemperatureUnit,indexOfTemperatureUnits)));
        String temperatureUnitText = temperatureUnit.getText().substring(1,2);
        temperatureUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(doneButton));
        driver.findElement(doneButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String temperatureUnitSetting = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].tempUnit",element).toString();
        Assert.assertEquals(temperatureUnitText,temperatureUnitSetting);
    }
    public void setDistanceUnitSettingsToInteractiveMapPage(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(distanceUnits));
        int numberOfDistanceUnits = driver.findElements(distanceUnits).size();
        int indexOfDistanceUnits = rnd.nextInt(numberOfDistanceUnits-1)+1;
        WebElement distanceUnit = driver.findElement(By.xpath(String.format(specificDistanceUnit,indexOfDistanceUnits)));
        String distanceUnitText = distanceUnit.getText();
        distanceUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(doneButton));
        driver.findElement(doneButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String distanceUnitSetting = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].distanceUnit",element).toString();
        if (distanceUnitText == "MILES") {
            Assert.assertEquals(distanceUnitSetting,"mi");
        }
        if(distanceUnitText == "KM"){
            Assert.assertEquals(distanceUnitSetting,"km");
        }
        if(distanceUnitText == "NMI"){
            Assert.assertEquals(distanceUnitSetting,"nmi");
        }
    }
    public void setHeightUnitSettingsToInteractiveMapPage(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(heightUnits));
        int numberOfHeightUnits = driver.findElements(heightUnits).size();
        int indexOfHeightUnits = rnd.nextInt(numberOfHeightUnits-1)+1;
        WebElement heightUnit = driver.findElement(By.xpath(String.format(specificHeightUnit,indexOfHeightUnits)));
        String heightUnitText = heightUnit.getText();
        heightUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(doneButton));
        driver.findElement(doneButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String heightUnitSetting = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].heightUnit",element).toString();
        if (heightUnitText == "FEET") {
            Assert.assertEquals(heightUnitSetting,"feet");
        }
        if(heightUnitText == "METERS"){
            Assert.assertEquals(heightUnitSetting,"m");
        }
    }
    public void setPressureSettingsToInteractiveMap(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pressureUnits));
        int numberOfPressureUnits = driver.findElements(heightUnits).size();
        int indexOfPressureUnits = rnd.nextInt(numberOfPressureUnits-1)+1;
        WebElement pressureUnit = scrollElementIntoView(By.xpath(String.format(specificPressureUnit,indexOfPressureUnits)));
        String pressureUnitText = pressureUnit.getText();
        pressureUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(doneButton));
        driver.findElement(doneButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String pressureUnitSetting = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].pressureUnit",element).toString();
        if (pressureUnitText == "INCHES") {
            Assert.assertEquals(pressureUnitSetting,"inhg");
        }
        if(pressureUnitText == "MILLIBARS"){
            Assert.assertEquals(pressureUnitSetting,"mbar");
        }
    }
    public void setSpeedSettingsToInteractiveMap(String validUsername,String validPassword){
        beginTestInteractiveMapPage(validUsername,validPassword);
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));}
        catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsIcon));
        }
        driver.findElement(mapSettingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(speedUnits));
        int numberOfSpeedUnits = driver.findElements(speedUnits).size();
        int indexOfSpeedUnits = rnd.nextInt(numberOfSpeedUnits-1)+1;
        WebElement speedUnit = scrollElementIntoView(By.xpath(String.format(specificSpeedUnit,indexOfSpeedUnits)));
        String speedUnitText = speedUnit.getText();
        speedUnit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(doneButton));
        driver.findElement(doneButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(map));
        WebElement element = driver.findElement(map);
        driver.manage().window().fullscreen();
        String speedUnitSetting = ((JavascriptExecutor) driver).executeScript("return this.map[\"opsdashboard-imap\"].speedUnit",element).toString();
        System.out.println(speedUnitText);
        if (speedUnitText == "MPH") {
            Assert.assertEquals(speedUnitSetting,"mph");
        }
        if(speedUnitText == "KM/H"){
            Assert.assertEquals(speedUnitSetting,"kmh");
        }
        if(speedUnitText == "KNOTS"){
            Assert.assertEquals(speedUnitSetting,"knots");
        }
    }
}
