package panels;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class InteractiveMapPanel extends BasePanel {

    protected static Random rnd = new Random();
    private By interactiveMapPanelOption = By.xpath("//div[@class='panel-title' and contains(., 'Interactive Map')]");
    private By interactiveMap = By.xpath("//canvas");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By mapStyleDropdownArrow = By.xpath("//svg-icon[@class='expand-dropdown-icon']");
    private By mapStyle = By.xpath("//div[contains(@class,'w-157')]//button//span[@class='dropdown-item-title']");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By interactiveMapZoom = By.xpath("(//div[contains(@class,'zoomControlElement')])[1]");
    private By interactiveMapLayerButton = By.xpath("(//div[contains(@class,'layer-button')])[1]");
    private String specificMapStyle = "(//div[contains(@class,'w-157')]//button//span[@class='dropdown-item-title'])[%d]";
    private By mapIcon = By.xpath("//svg-icon[contains(@class,'imap-settings-icon')]");
    private By mapSettingsButton = By.xpath("//span[contains(text(),'Settings')]");
    private By mapTheme = By.xpath("//div[contains(@class,'active-display')]");
    public InteractiveMapPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }


    public void addNewInteractiveMapPanel() throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("target/screenshots/screenshot.png"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interactiveMapPanelOption));
        driver.findElement(interactiveMapPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapStyleDropdownArrow));
        driver.findElement(mapStyleDropdownArrow).click();
        String chooseMapStyle = chooseRandomOption(mapStyle,specificMapStyle,"mapStyle: ",1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        waitUntillAllElementsVisible(Arrays.asList(interactiveMap,interactiveMapZoom,interactiveMapLayerButton));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(mapIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapSettingsButton));
        driver.findElement(mapSettingsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapTheme));
        String actualMapTheme = driver.findElement(mapTheme).getText();
        Assert.assertEquals(chooseMapStyle,actualMapTheme);
    }
}
