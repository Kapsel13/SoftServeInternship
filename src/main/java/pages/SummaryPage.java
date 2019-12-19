package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SummaryPage extends BasePage {
    private By summaryPageLink = By.xpath("//div[contains(@class,'')]/a[contains(text(),'Summary')]");
    private By sortingButton = By.xpath("//button[@class='sorting-btn']");
    private By dashboardLocation = By.xpath("//td[contains(@class,'table-cell-location ')]");
    private String specificDashboardLocation = "(//td[contains(@class,'table-cell-location ')])[%d]";
    private By reverseAlphabeticalOrderButton = By.xpath("//button[contains(text(),'Name (Z to A)')]");
    private By sortByNumberOfAlertsButton = By.xpath("//button[contains(text(),'Number of Total Alerts')]");
    private By sortByNumberOfCriticalAlertsButton = By.xpath("//button[contains(text(),'Number of Critical Alerts')]");
    private String specificDashboardTotalAlertsNumber = "(//div[contains(@class,'total-alerts-container')])[%d]";
    private String specificDashboardCriticalAlertsNumber = "(//div[contains(@class,'critical-alerts-container')])[%d]";
    public SummaryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement scrollElementIntoView(By by) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
        return webElement;
    }

    public void sortDashboardsAlphabetically(){
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
}
