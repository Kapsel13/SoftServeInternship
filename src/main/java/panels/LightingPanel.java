package panels;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LightingPanel extends BasePanel {

    private By lightingPanelOption = By.xpath("//div[@class='panel-title']//span[contains(text(),'Lightning')]");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By confirmLightningRangeButton = By.xpath("//form[contains(.,'Units')]//button[contains(text(),'Next')]");
    private By confirmAlertMessageButton = By.xpath("//form[contains(.,'Add additional alert details')]//button[contains(text(),'Next')]");
    private By confirmSendNotificationButton = By.xpath("//form[contains(.,'Send notifications?')]//button[contains(text(),'Next')]");
    private By confirmMapSettingsButton = By.xpath("//form[contains(.,'Map Style')]//button[contains(text(),'Next')]");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By headerIcon = By.xpath("(//header[contains(@class,'lightning-header')]//svg-icon)[1]");
    private By clearStatus = By.xpath("(//div[@class='clear-status'])[1]");
    private By lightningRangeUnitsDropdownArrow = By.xpath("//div[contains(@class,'range-radius-units')]//svg-icon");
    private By lightningRangeUnits = By.xpath("//div[contains(@class,'range-radius-units')]//div//button");
    private String lightningRangeUnit = "(//div[contains(@class,'range-radius-units')]//div//button)[%d]";
    private By range1UnitsDropdownArrow = By.xpath("//button[contains(text(),'15')]//svg-icon");
    private By range1Units = By.xpath("//div[contains(@class,'w-85') and contains(@class,'show')]//button[contains(@class,'dropdown-item')]");
    private String range1Unit = "(//div[contains(@class,'w-85') and contains(@class,'show')]//button[contains(@class,'dropdown-item')])[%d]";
    private By lightningRangePriorityDropdownArrow = By.xpath("//div[contains(@class,'range-priority ')]//button/svg-icon[contains(@class,'expand-dropdown-icon')]");
    private By lightningRangePriorities = By.xpath("//button[contains(@class,'dropdown-item')]//svg-icon[contains(@class,'range-icon')]");
    private String lightningRangePriority = "(//button[contains(@class,'dropdown-item') and contains(text(),'Alert')])[%d]";
    private By allClearTimeDropdownArrow = By.xpath("//div[contains(@class,'all-clear-values')]//button//svg-icon");
    private By allClearTimes = By.xpath("//div[contains(@class,'dropdown-menu-allclear-time')]//perfect-scrollbar//div//div//button");
    private String allClearTime = "(//div[contains(@class,'dropdown-menu-allclear-time')]//perfect-scrollbar//div//div//button)[%d]";
    private By additionalAlertAcceptationButton = By.xpath("(//div[@class='mat-button-toggle-label-content' and contains(text(),'Yes')])[1]");
    private By additionalAlertTextArea = By.xpath("//textarea");
    private By sendNotificationRejectionButton = By.xpath("(//div[@class='mat-button-toggle-label-content' and contains(text(),'No')])[2]");
    private By mapStyleDropdownArrow = By.xpath("//div[contains(@class,'dashboard-dropdown-container')]//svg-icon[@class='expand-dropdown-icon']");
    private By mapStyles = By.xpath("//div[contains(@class,'w-300')]//span[@class='dropdown-item-title']");
    private String mapStyle = "(//div[contains(@class,'w-300')]//span[@class='dropdown-item-title'])[%d]";
    private By alertOption = By.xpath("//div[@class='mat-button-toggle-label-content' and contains(text(),'Alert')]");
    private By addRangeButton = By.xpath("//img[@class='add-range-icon']");
    private By range1DropdownArrow = By.xpath("//div[contains(@class,'range-radius-value')]//button//svg-icon");
    private By range2UnitContent = By.xpath("(//div[contains(.,'Range 2')]//div//div[contains(@class,'range-radius-value')]//button[contains(@class,'dropdown-toggle')])[2]");
    private By range2PriorityContent = By.xpath("(//div[@class='dropdown-range-icon-wrapper'])[2]");
    private By range = By.xpath("//div[@class='range-radius']");
    private By deleteRangeButton = By.xpath("//div[contains(@class,'remove-range-btn')]//svg-icon");
    protected static Random rnd = new Random();

    public LightingPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void addLightingPanel(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lightingPanelOption));
        driver.findElement(lightingPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(lightningRangeUnitsDropdownArrow));
        driver.findElement(lightningRangeUnitsDropdownArrow).click();
        int numberOfLightningRangeUnits = driver.findElements(lightningRangeUnits).size();
        int lightningRangeUnitIndex = rnd.nextInt(numberOfLightningRangeUnits-1)+1;
        WebElement lightningRangeUnitInAList = driver.findElement(By.xpath(String.format(lightningRangeUnit,lightningRangeUnitIndex)));
        System.out.println("lightningRangeUnit: "+lightningRangeUnitInAList.getText());
        lightningRangeUnitInAList.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(range1UnitsDropdownArrow));
        driver.findElement(range1UnitsDropdownArrow).click();
        int numberOfRange1Units = driver.findElements(range1Units).size();
        int range1UnitIndex = rnd.nextInt(numberOfRange1Units-1)+1;
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        WebElement range1UnitInAList = dashboardPage.scrollElementIntoView(By.xpath(String.format(range1Unit,range1UnitIndex)));
        System.out.println("range1Unit: "+range1UnitInAList.getText());
        range1UnitInAList.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(lightningRangePriorityDropdownArrow));
        driver.findElement(lightningRangePriorityDropdownArrow).click();
        int numberOfLightningRangePriorities = driver.findElements(lightningRangePriorities).size();
        int lightningRangePriorityIndex = rnd.nextInt(numberOfLightningRangePriorities-1)+1;
        WebElement lightningRangePriorityInAList = driver.findElement(By.xpath(String.format(lightningRangePriority,lightningRangePriorityIndex)));
        System.out.println("lightningRangePriority: "+lightningRangePriorityInAList.getText());
        lightningRangePriorityInAList.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(allClearTimeDropdownArrow));
        driver.findElement(allClearTimeDropdownArrow).click();
        int numberOfAllClearTimes = driver.findElements(allClearTimes).size();
        int allClearTimeIndex = rnd.nextInt(numberOfAllClearTimes-1)+1;
        WebElement allClearTimeInAList = dashboardPage.scrollElementIntoView(By.xpath(String.format(allClearTime,allClearTimeIndex)));
        System.out.println("allClearTime: "+allClearTimeInAList.getText());
        allClearTimeInAList.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmLightningRangeButton));
        driver.findElement(confirmLightningRangeButton).click();


        int additionalAlertOption = rnd.nextInt(2);
        if(additionalAlertOption == 1){
            wait.until(ExpectedConditions.visibilityOfElementLocated(additionalAlertAcceptationButton));
            WebElement chooseAlertOption = driver.findElement(additionalAlertAcceptationButton);
            System.out.println("additionalAlertOption: "+chooseAlertOption.getText());
            chooseAlertOption.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(additionalAlertTextArea));
            String message = generateAdditionalAlertMessage();
            driver.findElement(additionalAlertTextArea).sendKeys(message);
            System.out.println("additionalAlertMessage: "+message);
        }
        else {
            System.out.println("additionalAlertOption: No");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmAlertMessageButton));
        driver.findElement(confirmAlertMessageButton).click();


        int sendNotificationOption = rnd.nextInt(2);
        if(sendNotificationOption == 0){
            wait.until(ExpectedConditions.visibilityOfElementLocated(sendNotificationRejectionButton));
            WebElement rejectionOption = driver.findElement(sendNotificationRejectionButton);
            System.out.println("sendNotificationOption: "+rejectionOption.getText());
            rejectionOption.click();
        }
        else{
            System.out.println("sendNotificationOption: Yes");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmSendNotificationButton));
        driver.findElement(confirmSendNotificationButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(mapStyleDropdownArrow));
        driver.findElement(mapStyleDropdownArrow).click();
        int numberOfMapStyles = driver.findElements(mapStyles).size();
        int mapStyleIndex = rnd.nextInt(numberOfMapStyles-1)+1;
        WebElement chooseMapStyle = driver.findElement(By.xpath(String.format(mapStyle,mapStyleIndex)));
        System.out.println("mapStyle: "+chooseMapStyle.getText());
        chooseMapStyle.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMapSettingsButton));
        driver.findElement(confirmMapSettingsButton).click();


        int typeOfPanel = rnd.nextInt(2);
        if(typeOfPanel == 1){
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertOption));
            WebElement chooseAlertOption = driver.findElement(alertOption);
            System.out.println("typeOfPanel: "+chooseAlertOption.getText());
            chooseAlertOption.click();
        }
        else {
            System.out.println("typeOfPanel: Normal");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        waitUntillAllElementsVisible(Arrays.asList(headerIcon,clearStatus));
    }
    public void addRange(){
        List<String> rangeInfo = addNewRange();
        Assert.assertEquals(Integer.parseInt(rangeInfo.get(0))+1,Integer.parseInt(rangeInfo.get(1)));
        Assert.assertEquals(rangeInfo.get(2),rangeInfo.get(3));
    }

    public void deleteRange(){
        List<String> rangeInfo = addNewRange();
        int numberOfRanges = driver.findElements(range).size();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteRangeButton));
        driver.findElement(deleteRangeButton).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteRangeButton));
        int numberOfRangesAfterDelete = driver.findElements(range).size();
        Assert.assertEquals(numberOfRanges-1,numberOfRangesAfterDelete);
    }

    public List<String> addNewRange(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lightingPanelOption));
        driver.findElement(lightingPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(range1DropdownArrow));
        driver.findElement(range1DropdownArrow).click();
        int numberOfRange1Units = driver.findElements(range1Units).size();
        int range1UnitIndex = rnd.nextInt(numberOfRange1Units-1)+1;
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        WebElement range1UnitInAList = dashboardPage.scrollElementIntoView(By.xpath(String.format(range1Unit,range1UnitIndex)));
        String range1UnitContent = range1UnitInAList.getText();
        System.out.println("range1Unit: "+ range1UnitContent);
        range1UnitInAList.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(lightningRangePriorityDropdownArrow));
        driver.findElement(lightningRangePriorityDropdownArrow).click();
        int numberOfLightningRangePriorities = driver.findElements(lightningRangePriorities).size();
        int lightningRangePriorityIndex = rnd.nextInt(numberOfLightningRangePriorities-1)+1;
        WebElement lightningRangePriorityInAList = driver.findElement(By.xpath(String.format(lightningRangePriority,lightningRangePriorityIndex)));
        String range1PriorityContent = lightningRangePriorityInAList.getText();
        System.out.println("range1lightningRangePriority: "+range1PriorityContent);
        lightningRangePriorityInAList.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(addRangeButton));
        driver.findElement(addRangeButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(range2UnitContent));
        String range2UnitText = driver.findElement(range2UnitContent).getText();
        System.out.println("range2Unit: "+range2UnitText);

        wait.until(ExpectedConditions.visibilityOfElementLocated(range2PriorityContent));
        String range2PriorityText = driver.findElement(range2PriorityContent).getText();
        System.out.println("range2Priority: "+range2PriorityText);

        List<String> rangeInfo = Arrays.asList(range1UnitContent,range2UnitText,range1PriorityContent,range2PriorityText);
        return rangeInfo;
    }

}
