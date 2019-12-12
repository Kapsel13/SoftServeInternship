package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
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
    public InteractiveMapPage(WebDriver driver, WebDriverWait wait){super(driver,wait);}
    public void addNewLayerToInteractiveMapPage(String validUsername,String validPassword){
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
}
