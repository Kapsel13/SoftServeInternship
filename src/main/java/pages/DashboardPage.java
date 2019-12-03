package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Random;

public class DashboardPage extends BasePage{


    protected By settingsIcon = By.xpath("//section[@class='settings-icon-container']");
    protected By signOutLabel = By.xpath("//label[contains(text(),'Sign out')]");
    protected By usernameInput = By.xpath("//input[@placeholder='Username']");
    protected By loginButton = By.xpath("//button[@class='login-button']");
    protected By dashboardDropdownButton = By.xpath("//button[@id='dashboard-dropdown']");
    protected By createNewDashboardOption = By.xpath( "//button[contains(@class, 'dropdown-item')]/span[contains(text(), 'Create New Dashboard')]");
    protected By confirmButton = By.xpath("//button[@class='button-highlight']");
    protected By searchLocationInput = By.xpath("//input[@placeholder='Search by City, Zip or Lat, Lon']");
    protected String locationInList = "(//div[contains(@class, 'search-results')]//div[contains(@class, 'location') and contains(text(), '%s')])[1]";
    protected By locationEnabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[contains(text(), 'Next')]");
    protected By nameInput = By.xpath("//input[@placeholder='Enter name']");
    protected By monitorButton = By.xpath("//button[contains(text(), 'Next')]");
    protected By createButton = By.xpath("//button[contains(text(),'Create')]");
    protected By nameEnabledButton = By.xpath("//div[contains(text(), 'What do you want to call this dashboard?')]/../../..//button[contains(text(), 'Next')]");
    protected By inActiveButton = By.xpath("//div[contains(text(),'In-Active')]");
    protected By customRangeButton = By.xpath("//div[contains(text(),'Custom Range')]");
    protected By startDateInput = By.xpath("//input[@placeholder='Select start date']");
    protected By endDateInput = By.xpath("//input[@placeholder='Select end date']");
    protected String startTimeInList = "(//div[@formgroupname='start']//button[contains(@class,'dropdown-item')])[%d]";
    protected String endTimeInList = "(//div[@formgroupname='end']//button[contains(@class,'dropdown-item')])[%d]";
    protected By startTimesArrow = By.xpath("(//mat-icon[contains(text(),'keyboard_arrow_down')])[1]");
    protected By endTimesArrow = By.xpath("(//mat-icon[contains(text(),'keyboard_arrow_down')])[2]");
    protected By startTimes = By.xpath("//div[@formgroupname='start']//button[contains(@class,'dropdown-item')]");
    protected By endTimes = By.xpath("//div[@formgroupname='end']//button[contains(@class,'dropdown-item')]");
    protected By locationDisabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[@disabled]");
    protected String summaryPageLink = "//div[contains(@class, '%s')]/a[contains(text(),'Summary')]";
    protected By dashboardPageText = By.xpath("//span[contains(text(),'My Dashboards')]");
    protected By dashboards = By.xpath("((//table[contains(@class, 'dashboard-list-table')]//tr))//td[1]");
    protected String dashboardsInList = "((//table[contains(@class, 'dashboard-list-table')]//tr)[%d])//td[1]";
    protected By dashboardTag = By.xpath("(//span[@class='dropdown-item-title'])[1]");
    protected By summaryPageText = By.xpath("//span[contains(text(),'Summary')]");
    protected By activeDashboard = By.xpath("//div[contains(@class,'dropdown-menu')]//button[not(contains(., '(Inactive)'))]");
    protected String activeDashboardInList = "(//div[contains(@class,'dropdown-menu')]//button[not(contains(., '(Inactive)'))])[%d]";
    protected By addPanelOption = By.xpath("//span[contains(text(),'Add Panel')]");
    protected By panelMenu = By.xpath("//div[@class='header-menu-icon']");
    protected By specificPanelMenu = By.xpath("(//div[@class='header-menu-icon'])[1]");
    protected By deleteButton = By.xpath("//span[contains(text(),'Delete') and not(contains(text(),'Selected'))]");
    protected By confirmDeletingButton = By.xpath("//button[contains(text(),'YES')]");
    protected By okButton = By.xpath("//button[contains(text(),'OK')]");
    protected static Random rnd = new Random();

    public DashboardPage(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public void logOut(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
        driver.findElement(settingsIcon).click();
        driver.findElement(signOutLabel).click();
        waitUntillAllElementsVisible(Arrays.asList(usernameInput,loginButton));
    }


    public void beginCreatingNewDashboard(){
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewDashboardOption));

        driver.findElement(createNewDashboardOption).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));

        driver.findElement(confirmButton).click();
    }

    public void selectValidLocation(String typeOfLocation, String cityOfTypeOfLocation){
        driver.findElement(searchLocationInput).sendKeys(typeOfLocation);
        driver.findElement(By.xpath(String.format(locationInList, cityOfTypeOfLocation))).click();
        wait.until(ExpectedConditions.elementToBeClickable(locationEnabledButton));
        driver.findElement(locationEnabledButton).click();
    }

    public void provideInValidLocation(String invalidLocation){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchLocationInput));
        driver.findElement(searchLocationInput).sendKeys(invalidLocation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationDisabledButton));
    }

    public void selectValidName(String validDashboardName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(validDashboardName);
        driver.findElement(nameEnabledButton).click();
    }

    public void createNewDashboard(By newDashboardText){
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        driver.findElement(createButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(newDashboardText));
    }

    public void setCustomRangeDateAndTime(int startTimesNumber,int endTimesNumber, String startDate, String endDate){
        driver.findElement(startDateInput).sendKeys(startDate);
        driver.findElement(endDateInput).sendKeys(endDate);
        driver.findElement(startTimesArrow).click();

        WebElement startTimeElement = scrollElementIntoView(By.xpath(String.format(startTimeInList,startTimesNumber)));
        startTimeElement.click();

        driver.findElement(endTimesArrow).click();

        WebElement endTimeElement = scrollElementIntoView(By.xpath(String.format(endTimeInList,endTimesNumber)));
        System.out.println(String.format(endTimeInList,endTimesNumber));
        endTimeElement.click();
    }

    public void selectValidLocationWithCoordinates(String gpsCoords){
        driver.findElement(searchLocationInput).sendKeys(gpsCoords);
        waitUntillAllElementsVisible(Arrays.asList(By.xpath("//div[contains(@class, 'search-results')]//div[contains(text(), 'Recent Locations')]")));
        driver.findElement(searchLocationInput).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(locationEnabledButton));
        driver.findElement(locationEnabledButton).click();
    }

    public WebElement scrollElementIntoView(By by) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
        return webElement;
    }

    public void setInActiveMonitoring(){
        wait.until(ExpectedConditions.elementToBeClickable(inActiveButton));
        driver.findElement(inActiveButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(monitorButton).get(3)));
        driver.findElements(monitorButton).get(3).click();
    }

    public void setCustomRangeMonitoring(Random rnd, String startDate, String endDate){
        wait.until(ExpectedConditions.elementToBeClickable(customRangeButton));
        driver.findElement(customRangeButton).click();
        int startTimesNumber = rnd.nextInt(driver.findElements(startTimes).size())+1;
        int endTimesNumber = rnd.nextInt(driver.findElements(endTimes).size())+1;
        setCustomRangeDateAndTime(startTimesNumber,endTimesNumber,startDate,endDate);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(monitorButton).get(3)));
        driver.findElements(monitorButton).get(3).click();
    }

    public void setActiveMonitoring(){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(monitorButton).get(3)));
        driver.findElements(monitorButton).get(3).click();
    }

    public void beginCreateDashboardTest(String validUsername, String validPassword){
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validUsername,true);
        logInPage.providePassword(validPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreatingNewDashboard();
    }

    public void redirectBetweenTwoPages(By pageLink, By pageLink2, By pageElement){
        driver.findElement(pageLink).click();
        waitUntillAllElementsVisible(Arrays.asList(pageLink,pageElement));

        driver.findElement(pageLink2).click();
    }

    public void redirectToTheSpecificDashboard(Random rnd){
        waitUntillAllElementsVisible(Arrays.asList(dashboardDropdownButton, dashboardPageText));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(summaryPageLink, ""))));
        driver.findElement(By.xpath(String.format(summaryPageLink, ""))).click();
        waitUntillAllElementsVisible(Arrays.asList(By.xpath(String.format(summaryPageLink, "active")),summaryPageText));


        int numberOfDashboards = driver.findElements(dashboards).size();
        int dashboardNumber = rnd.nextInt(numberOfDashboards-1)+1;
        String dashboardText = driver.findElement(By.xpath(String.format(dashboardsInList,dashboardNumber))).getText();
        driver.findElement(By.xpath(String.format(dashboardsInList,dashboardNumber))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTag));

        String dashboardTagText = driver.findElement(dashboardTag).getText();
        if(dashboardText.contains("(Inactive)")) {
            dashboardTagText = dashboardTagText + "  (Inactive)";
        }
        System.out.println(dashboardText+ "##########"+dashboardTagText);
        Assert.assertEquals(dashboardText,dashboardTagText);
    }

    public void chooseActiveDashboard(Random rnd){
        driver.findElement(dashboardDropdownButton).click();
        int activeDashboardsNumber = driver.findElements(activeDashboard).size();
        int numberOfActiveDashboard = /*rnd.nextInt(activeDashboardsNumber-1)+1;*/8;
        WebElement activeDashboardToClick = scrollElementIntoView(By.xpath(String.format(activeDashboardInList,numberOfActiveDashboard)));
        activeDashboardToClick.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
    }

    public void beginAddingNewPanel(String validUsername,String validPassword) {
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validUsername, true);
        logInPage.providePassword(validPassword, true);
        chooseActiveDashboard(rnd);
        //deleteAllPanelsFromDashboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        driver.findElement(addPanelOption).click();
    }

    public void deleteAllPanelsFromDashboard(){
        try{
            Thread.sleep(2000);

        }catch (InterruptedException e){}
        int panelsNumber = driver.findElements(panelMenu).size();
        while(panelsNumber!=0){
            wait.until(ExpectedConditions.visibilityOfElementLocated(specificPanelMenu));
            driver.findElement(specificPanelMenu).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
            driver.findElement(deleteButton).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDeletingButton));
            driver.findElement(confirmDeletingButton).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(okButton));
            driver.findElement(okButton).click();
            panelsNumber--;
            try{
                Thread.sleep(2000);

            }catch (InterruptedException e){}
        }
    }


}
