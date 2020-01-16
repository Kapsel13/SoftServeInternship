package panels;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LogInPage;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class CurrentWeatherConditonsPanel extends BasePanel {

    private By currentWeatherPanelOption = By.xpath("//div[@class='panel-title' and contains(., 'Current Weather')]");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By currentWeatherConditionTagText = By.xpath("(//span[contains(text(),'Current Conditions')])[1]");
    private By usUnitsSign = By.xpath("(//span[contains(@class,'ng-star-inserted') and contains(text(),'F')])[1]");
    private By metricUnitsOption = By.xpath("//div[contains(text(),'Metric')]");
    private By usUnitsOption = By.xpath("//div[contains(text(),'US')]");
    private By metricUnitsSign = By.xpath("(//span[contains(@class,'ng-star-inserted') and contains(text(),'C')])[1]");
    private By temperatureNumber = By.xpath("(//span[@class='primary-temp'])[1]");
    protected By addPanelOption = By.xpath("//span[contains(text(),'Add Panel')]");
    private By settingsIcon = By.xpath("(//div[contains(@class,'grid-stack-item-content-header') and contains(.,'Current Conditions')]//div//div[@class='header-menu-icon'])[1]");
    private By editOption = By.xpath("//button[contains(@class,'mat-menu-item') and contains(.,'Edit')]");
    private By metricActiveOption = By.xpath("//mat-button-toggle[contains(@class,' mat-button-toggle-checked') and contains(.,'Metric')]");
    private By usActiveOption = By.xpath("//mat-button-toggle[contains(@class,' mat-button-toggle-checked') and contains(.,'US')]");
    private By saveButton = By.xpath("//button[contains(text(),'Save')]");
    private By unitsTag = By.xpath("(//span[contains(@class,'ng-star-inserted') and contains(@class,'units')] )[1]");
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
    public void checkEditingCurrentWeatherConditionsPanel(String username,String password,Random rnd){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        String unitsBeforeEdit = "";
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.chooseActiveDashboard(rnd);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            dashboardPage.scrollElementIntoView(currentWeatherConditionTagText);
            WebElement unitsSign = dashboardPage.scrollElementIntoView(unitsTag);
            String unitsSignText = unitsSign.getText();
            if(unitsSignText.contains("C")){
                unitsBeforeEdit = "C";
            }
            else{
                unitsBeforeEdit = "F";
            }
        }catch(org.openqa.selenium.NoSuchElementException e){
            wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
            driver.findElement(addPanelOption).click();
            int option = rnd.nextInt(2);
            if(option == 1){
                unitsBeforeEdit = "C";
                addNewCurrentWeatherConditionalPanel("M");
            }
            else{
                unitsBeforeEdit = "F";
                addNewCurrentWeatherConditionalPanel("U");
            }
        }
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(settingsIcon)));
        driver.findElement(settingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        if(unitsBeforeEdit.equals("C")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(metricActiveOption));
        }
        else{
            wait.until(ExpectedConditions.visibilityOfElementLocated(usActiveOption));
        }
        String unitsAfterEdit = "";
        int option = rnd.nextInt(2);
        if(option == 1){
            unitsAfterEdit="C";
            if(unitsBeforeEdit.equals("F")){
                driver.findElement(metricUnitsOption).click();
            }
        }
        else{
            unitsAfterEdit="F";
            if(unitsBeforeEdit.equals("C")){
                driver.findElement(usUnitsOption).click();
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        driver.findElement(saveButton).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(unitsAfterEdit.equals("C")){
            wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(metricUnitsSign)));
        }
        else{
            wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(usUnitsSign)));
        }
    }
}
