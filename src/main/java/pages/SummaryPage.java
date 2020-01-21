package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SummaryPage extends BasePage {
    private Random rnd = new Random();
    private By summaryPageLink = By.xpath("//div[contains(@class,'')]/a[contains(text(),'Summary')]");
    private By sortingButton = By.xpath("//button[@class='sorting-btn']");
    private By filteringButton = By.xpath("//button[@class='filtering-btn']");
    private By dashboardLocation = By.xpath("//td[contains(@class,'table-cell-location ')]");
    private String specificDashboardLocation = "(//td[contains(@class,'table-cell-location ')])[%d]";
    private By reverseAlphabeticalOrderButton = By.xpath("//button[contains(text(),'Name (Z to A)')]");
    private By sortByNumberOfAlertsButton = By.xpath("//button[contains(text(),'Number of Total Alerts')]");
    private By sortByNumberOfCriticalAlertsButton = By.xpath("//button[contains(text(),'Number of Critical Alerts')]");
    private String specificDashboardTotalAlertsNumber = "(//div[contains(@class,'total-alerts-container')])[%d]";
    private String specificDashboardCriticalAlertsNumber = "(//div[contains(@class,'critical-alerts-container')])[%d]";
    private By filterByCriticalAlertsButton = By.xpath("(//div[@class='mat-checkbox-inner-container'])[1]");
    private By numberOfAlertsDropdown = By.xpath("//div[@class='single-menu-wrapper']");
    private By filterByNumberOfAlertsOption = By.xpath("//div[contains(@class,'dropdown-menu')]//button[contains(@class,'dropdown-item')]");
    private String specificFilterByNumberOfAlertsOption = "(//div[contains(@class,'dropdown-menu')]//button[contains(@class,'dropdown-item')])[%d]";
    private By alertTypeOption = By.xpath("//button[@id='multiple-menu']");
    private By filterAlertTypeOption = By.xpath("//div[@class='ps-content']//mat-checkbox");
    private String specificAlertTypeOption = "(//div[@class='ps-content']//mat-checkbox)[%d]";
    private String numberOfAlertsInDashboard = "(//div[@class='summary-icons-container'])[%d]//div[@class='icon-container']";
    private String specificAlertInDashboardFirstPart = "((//div[@class='summary-icons-container'])[%d]";
    private String specificAlertInDashboardSecondPart = "//div[@class='icon-container'])[%d]";
    private By alertTypeToolTip = By.xpath("//p[contains(@class,'icon-parameter')]");
    private By nowOption = By.xpath("//span[contains(text(),'Now')]");
    private By todayOption = By.xpath("//span[contains(text(),'Today')]");
    private By tomorrowOption = By.xpath("//span[contains(text(),'Tomorrow')]");
    private By allOption = By.xpath("//span[contains(text(),'All')]");
    private By alertContainer = By.xpath("//div[@class='icon-container']");
    private String specificAlertContainer = "(//div[@class='icon-container'])[%d]";
    private By validNowToolTip = By.xpath("//span[contains(text(),'Valid Now')]");
    private By validTodayToolTip = By.xpath("//span[contains(text(),'Valid Today')]");
    private By validTomorrowToolTip = By.xpath("//span[contains(text(),'Valid Tomorrow')]");
    private By dashboardOnSummaryPage = By.xpath("//div[@class='ps-content']//tr");
    private String dashboardPageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Dashboards')]";
    protected By dashboardDropdownButton = By.xpath("//button[@id='dashboard-dropdown']");
    private By dashboardOnDashboardsPage = By.xpath("//div[@class='ps-content']//button");
    private By firstDashboardOnSummaryPage = By.xpath("//tr[2]");
    private By totalActiveAlertsField = By.xpath("//div[contains(@class,'total-alerts-container active')]");
    private String specificTotalActiveAlertsField = "(//div[contains(@class,'total-alerts-container active')])[%d]";
    private By dashboardTitle = By.xpath("(//span[contains(@class,'dropdown-item-title')])[1]");
    public SummaryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement scrollElementIntoView(By by) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
        return webElement;
    }

    public void redirectToSummaryPage(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryPageLink));
        driver.findElement(summaryPageLink).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void sortDashboardsAlphabetically(){
        redirectToSummaryPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingButton));
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        int index = 2;
        boolean sortedAlphabetically = true;
        WebElement prevLocation = driver.findElement(By.xpath(String.format(specificDashboardLocation,1)));
        String prevLocationText = prevLocation.getText();
        String actualLocationText = "";
        while((index<=numberOfDashboards)&&(sortedAlphabetically)){
            WebElement actualLocation  = scrollElementIntoView(By.xpath(String.format(specificDashboardLocation,index)));
            actualLocationText = actualLocation.getText();
            if(prevLocationText.compareToIgnoreCase(actualLocationText) > 0){
                sortedAlphabetically = false;
            }
            prevLocationText = actualLocationText;
            index++;
        }
        Assert.assertTrue(sortedAlphabetically);
    }
    public void sortDashboardInReverseAlphabeticalOrder(){
        redirectToSummaryPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingButton));
        driver.findElement(sortingButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(reverseAlphabeticalOrderButton));
        driver.findElement(reverseAlphabeticalOrderButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        int index = 2;
        boolean sortedReverseAlphabetically = true;
        WebElement prevLocation = driver.findElement(By.xpath(String.format(specificDashboardLocation,1)));
        String prevLocationText = prevLocation.getText();
        String actualLocationText = "";
        while((index<=numberOfDashboards)&&(sortedReverseAlphabetically)){
            WebElement actualLocation  = scrollElementIntoView(By.xpath(String.format(specificDashboardLocation,index)));
            actualLocationText = actualLocation.getText();
            if(prevLocationText.compareToIgnoreCase(actualLocationText) < 0){
                sortedReverseAlphabetically = false;
            }
            prevLocationText = actualLocationText;
            index++;
        }
        Assert.assertTrue(sortedReverseAlphabetically);
    }
    public void sortDashboardByNumberOfAlerts(){
        redirectToSummaryPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingButton));
        driver.findElement(sortingButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortByNumberOfAlertsButton));
        driver.findElement(sortByNumberOfAlertsButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        int index = 2;
        boolean sortedByNumberOfAlerts = true;
        WebElement prevTotalAlerts = scrollElementIntoView(By.xpath(String.format(specificDashboardTotalAlertsNumber,1)));
        int prevTotalAlertsNumber = Integer.parseInt(prevTotalAlerts.getText());
        int actualTotalAlertsNumber = 0;
        while((index<=numberOfDashboards)&&(sortedByNumberOfAlerts)){
            WebElement actualTotalAlerts = scrollElementIntoView(By.xpath(String.format(specificDashboardTotalAlertsNumber,index)));
            actualTotalAlertsNumber = Integer.parseInt(actualTotalAlerts.getText());
            if(prevTotalAlertsNumber<actualTotalAlertsNumber){
                sortedByNumberOfAlerts = false;
            }
            index++;
            prevTotalAlertsNumber = actualTotalAlertsNumber;
        }
        Assert.assertTrue(sortedByNumberOfAlerts);
    }
    public void sortDashboardsByNumberOfCriticalAlerts(){
       redirectToSummaryPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingButton));
        driver.findElement(sortingButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortByNumberOfCriticalAlertsButton));
        driver.findElement(sortByNumberOfCriticalAlertsButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        int index = 2;
        boolean sortByNumberOfCriticalAlerts = true;
        WebElement prevCriticalAlerts = scrollElementIntoView(By.xpath(String.format(specificDashboardCriticalAlertsNumber,1)));
        int prevCriticalAlertsNumber = Integer.parseInt(prevCriticalAlerts.getText());
        int actualCriticalAlertsNumber = 0;
        while((index<=numberOfDashboards)&&(sortByNumberOfCriticalAlerts)){
            WebElement actualCriticalAlerts = scrollElementIntoView(By.xpath(String.format(specificDashboardCriticalAlertsNumber,index)));
            actualCriticalAlertsNumber = Integer.parseInt(actualCriticalAlerts.getText());
            if(prevCriticalAlertsNumber<actualCriticalAlertsNumber){
                sortByNumberOfCriticalAlerts = false;
            }
            index++;
            prevCriticalAlertsNumber = actualCriticalAlertsNumber;
        }
        Assert.assertTrue(sortByNumberOfCriticalAlerts);
    }
    public void filterDashboardsByCriticalAlerts(){
        redirectToSummaryPage();
        //driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filteringButton));
        driver.findElement(filteringButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterByCriticalAlertsButton));
        driver.findElement(filterByCriticalAlertsButton).click();
        //driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        int index = 1;
        int criticalAlertsNumber = 0;
        boolean containCriticalAlert = true;
        while((index<=numberOfDashboards)&&(containCriticalAlert)){
            WebElement criticalAlert = scrollElementIntoView(By.xpath(String.format(specificDashboardCriticalAlertsNumber,index)));
            criticalAlertsNumber = Integer.parseInt(criticalAlert.getText());
            if(criticalAlertsNumber == 0){
                containCriticalAlert = false;
            }
            index++;
        }
        Assert.assertTrue(containCriticalAlert);
    }
    public void filterDashboardsByNumberOfAlerts(){
        redirectToSummaryPage();
        //driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filteringButton));
        driver.findElement(filteringButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfAlertsDropdown));
        driver.findElement(numberOfAlertsDropdown).click();
        int numberOfOptions = driver.findElements(filterByNumberOfAlertsOption).size();
        int randomIndex = rnd.nextInt(numberOfOptions-1)+2;
        WebElement specificOption = scrollElementIntoView(By.xpath(String.format(specificFilterByNumberOfAlertsOption,randomIndex)));
        int specificNumberOfAlerts = Integer.parseInt(specificOption.getText().substring(0,1));
        specificOption.click();
        int index = 1;
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        boolean containSpecificNumberOfAlerts = true;
        int numberOfAlerts = 0;
        while((index<=numberOfDashboards)&&(containSpecificNumberOfAlerts)){
            WebElement alert = driver.findElement(By.xpath(String.format(specificDashboardTotalAlertsNumber,index)));
            numberOfAlerts = Integer.parseInt(alert.getText());
            if(numberOfAlerts<specificNumberOfAlerts){
                containSpecificNumberOfAlerts = false;
            }
            index++;
        }
        Assert.assertTrue(containSpecificNumberOfAlerts);
    }
    public void filterDashboardByAlertType(){
        redirectToSummaryPage();
        //driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filteringButton));
        driver.findElement(filteringButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertTypeOption));
        driver.findElement(alertTypeOption).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfOptions = driver.findElements(filterAlertTypeOption).size();
        int randomIndex = rnd.nextInt(numberOfOptions-1)+2;
        WebElement chosenOption = scrollElementIntoView(By.xpath(String.format(specificAlertTypeOption,randomIndex)));
        String alertTypeText = chosenOption.getText();
        chosenOption.click();
        driver.navigate().refresh();
        int index = 1;
        int numberOfDashboards = driver.findElements(dashboardLocation).size();
        boolean containsAlertType = true;
        while ((index<=numberOfDashboards)&&(containsAlertType)){
            int numberOfAlerts = driver.findElements(By.xpath(String.format(numberOfAlertsInDashboard,index))).size();
            int indexOfAlerts = 1;
            boolean foundAlertType = false;
            while((indexOfAlerts<=numberOfAlerts)&&(!foundAlertType)){
                Actions hover = new Actions(driver);
                WebElement alertType = driver.findElement(By.xpath(String.format(specificAlertInDashboardFirstPart,index)+String.format(specificAlertInDashboardSecondPart,indexOfAlerts)));
                hover.moveToElement(alertType).perform();
                wait.until(ExpectedConditions.visibilityOfElementLocated(alertTypeToolTip));
                WebElement toolTip = driver.findElement(alertTypeToolTip);
                String text = toolTip.getText();
                if(text.contains(alertTypeText)){
                    foundAlertType = true;
                }
                indexOfAlerts++;
            }
            if(!foundAlertType){
                containsAlertType = false;
            }
            index++;
        }
        Assert.assertTrue(containsAlertType);
    }
    public void checkSettingSlider(String time){
        redirectToSummaryPage();
        if(time == "all"){
            wait.until(ExpectedConditions.visibilityOfElementLocated(allOption));
            driver.findElement(allOption).click();
            int numberOnSummaryPage = driver.findElements(dashboardOnSummaryPage).size();
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstDashboardOnSummaryPage));
            driver.findElement(firstDashboardOnSummaryPage).click();
            try{
                wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
            }catch(TimeoutException e){
                driver.navigate().refresh();
                /*File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(srcFile,new File("target/screenshots/screenshot.png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }*/
                wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
            }
            driver.findElement(dashboardDropdownButton).click();
            int numberOnDashboardPage = driver.findElements(dashboardOnDashboardsPage).size();
            Assert.assertEquals(numberOnSummaryPage,numberOnDashboardPage);
        }
        else {
            if (time == "now") {
                wait.until(ExpectedConditions.visibilityOfElementLocated(nowOption));
                driver.findElement(nowOption).click();
            }
            if (time == "today") {
                wait.until(ExpectedConditions.visibilityOfElementLocated(todayOption));
                driver.findElement(todayOption).click();
            }
            if (time == "tomorrow") {
                wait.until(ExpectedConditions.visibilityOfElementLocated(tomorrowOption));
                driver.findElement(tomorrowOption).click();
            }
            int numberOfAlertContainers = driver.findElements(alertContainer).size();
            int index = 1;
            boolean expressionNotFound = false;
            while ((index <= numberOfAlertContainers) && (!expressionNotFound)) {
                Actions action = new Actions(driver);
                WebElement alert = scrollElementIntoView(By.xpath(String.format(specificAlertContainer, index)));
                action.moveToElement(alert).perform();
                try {
                    if (time == "now") {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(validNowToolTip));
                    }
                    if (time == "today") {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(validTodayToolTip));
                    }
                    if (time == "tomorrow") {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(validTomorrowToolTip));
                    }
                } catch (TimeoutException e) {
                    expressionNotFound = true;
                }
                index++;
            }
            Assert.assertFalse(expressionNotFound);
        }
    }
    public void checkTotalAlertsFieldAfterDeletingAllPanelsFromDashboard(){
        redirectToSummaryPage();
        driver.navigate().refresh();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("target/screenshots/screenshot2.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int numberOfDashboardWithActiveAlertBefore = driver.findElements(totalActiveAlertsField).size();
        int index = rnd.nextInt(numberOfDashboardWithActiveAlertBefore -1)+1;
        wait.until(ExpectedConditions.visibilityOf(scrollElementIntoView(By.xpath(String.format(specificTotalActiveAlertsField,index)))));
        WebElement specificDashboard = scrollElementIntoView(By.xpath(String.format(specificTotalActiveAlertsField,index)));
        specificDashboard.click();
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.deleteAllPanelsFromDashboard();
        redirectToSummaryPage();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfDashboardWithActiveAlertAfter = driver.findElements(totalActiveAlertsField).size();
        Assert.assertEquals(numberOfDashboardWithActiveAlertBefore-1,numberOfDashboardWithActiveAlertAfter);
    }
}
