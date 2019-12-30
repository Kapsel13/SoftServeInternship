package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DashboardPage extends BasePage{


    protected By settingsIcon = By.xpath("//section[@class='settings-icon-container']");
    protected By signOutLabel = By.xpath("//label[contains(text(),'Sign out')]");
    protected By usernameInput = By.xpath("//input[@placeholder='Username']");
    protected By loginButton = By.xpath("//button[@class='login-button']");
    protected By dashboardDropdownButton = By.xpath("//button[@id='dashboard-dropdown']");
    protected By createNewDashboardOption = By.xpath("//span[contains(text(),'Create New Dashboard')]");
    protected By confirmButton = By.xpath("(//button[contains(@class,'button-common') and contains(text(),'Next')])[1]");
    protected By searchLocationInput = By.xpath("//input[@placeholder='Enter city, state, zipcode or lat/long']");
    protected String locationInList = "(//div[contains(@class, 'search-results')]//div[contains(@class, 'location') and contains(text(), '%s')])[1]";
    protected By locationEnabledButton = By.xpath("//div[contains(text(), 'What location would you like to see data for?')]/../../..//button[contains(text(), 'Next')]");
    protected By nameInput = By.xpath("//input[@placeholder='Enter name']");
    protected By monitorButton = By.xpath("//button[contains(text(), 'Next')]");
    protected By createButton = By.xpath("//button[contains(text(),'Create')]");
    protected By nameEnabledButton = By.xpath("//div[contains(text(), 'What do you want to call this dashboard?')]/../../..//button[contains(text(), 'Next')]");
    protected By inActiveButton = By.xpath("//span[contains(text(),'In-Active')]");
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
    protected By dashboardTag = By.xpath("(//span[contains(@class,'dropdown-item-title')])[1]");
    protected By summaryPageText = By.xpath("//span[contains(text(),'Summary')]");
    protected By activeDashboard = By.xpath("//div[contains(@class,'dropdown-menu')]//button[not(contains(., '(Inactive)'))]");
    protected String activeDashboardInList = "(//div[contains(@class,'dropdown-menu')]//button[not(contains(., '(Inactive)'))])[%d]";
    protected By addPanelOption = By.xpath("//span[contains(text(),'Add Panel')]");
    protected By panelMenu = By.xpath("//div[@class='header-menu-icon']");
    protected By specificPanelMenu = By.xpath("(//div[@class='header-menu-icon'])[1]");
    protected By deleteButton = By.xpath("//span[contains(text(),'Delete') and not(contains(text(),'Selected'))]");
    protected By confirmDeletingButton = By.xpath("//button[contains(text(),'YES')]");
    protected By okButton = By.xpath("//button[contains(text(),'OK')]");
    private String addedDashboard = "//span[contains(@class,'dropdown-item-title') and contains(text(),'%s')]";
    private By deleteOption = By.xpath("//span[contains(@class,'dropdown-item-title') and contains(text(),'Delete Selected')]");
    private By deleteConfirm = By.xpath("//button[contains(@class,'button-common') and contains(text(),'Yes')]");
    private By closeCreateDashboardWindowIcon = By.xpath("//mat-icon[contains(text(),'clear')]");
    private By closeCreateDashboardWindowButton = By.xpath("//button[contains(text(),'Yes')]");
    private String specificDashboard = "//span[contains(@class,'prevent-native-tooltip') and contains(text(),'%s')]";
    private By dashboard = By.xpath("//span[contains(@class,'prevent-native-tooltip')]");
    private String dashboardInAList = "(//span[contains(@class,'prevent-native-tooltip')])[%d]";
    private By closeDeleteDashboardWindowIcon = By.xpath("//mat-icon[contains(text(),'clear')]");
    private By activeDashboardsNumberInformation = By.xpath("//p[@class='limits-description-txt']");
    protected static Random rnd = new Random();
    int numberOfActiveDashboardsBefore;
    private String inActiveDashboardInAList = "(//div[contains(@class,'dropdown-menu')]//button[contains(., '(Inactive)')])[%d]";
    private By inActiveDashboard = By.xpath("//div[contains(@class,'dropdown-menu')]//button[contains(., '(Inactive)')]");
    private By editOption = By.xpath("//span[contains(@class,'dropdown-item-title') and contains(text(),'Edit Selected')]");
    private By activeButton = By.xpath("//div[@class='mat-button-toggle-label-content' and contains(text(),'Active')]");
    private By confirmEditButton = By.xpath("//button[contains(text(),'Save')]");
    private By editMonitoringButton = By.xpath("(//button[contains(text(), 'Next')])[2]");
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(pageLink).click();
        try{
            waitUntillAllElementsVisible(Arrays.asList(pageLink2,pageElement));
        }catch (TimeoutException e){
            driver.navigate().refresh();
            waitUntillAllElementsVisible(Arrays.asList(pageLink2,pageElement));
        }
        driver.findElement(pageLink2).click();
    }

    public void redirectToTheSpecificDashboard(Random rnd){
        waitUntillAllElementsVisible(Arrays.asList(dashboardDropdownButton, dashboardPageText));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(summaryPageLink, ""))));
        driver.findElement(By.xpath(String.format(summaryPageLink, ""))).click();
        try {
            waitUntillAllElementsVisible(Arrays.asList(By.xpath(String.format(summaryPageLink, "active")), summaryPageText));
        }
        catch (TimeoutException e) {
            driver.navigate().refresh();
            waitUntillAllElementsVisible(Arrays.asList(By.xpath(String.format(summaryPageLink, "active")), summaryPageText));
        }
        int numberOfDashboards = driver.findElements(dashboards).size();
        int dashboardNumber = rnd.nextInt(numberOfDashboards-1)+1;
        WebElement dashboardElement = scrollElementIntoView(By.xpath(String.format(dashboardsInList,dashboardNumber)));
        String dashboardText = dashboardElement.getText();
        dashboardElement.click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTag));
        }catch (TimeoutException e) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTag));
        }
        String dashboardTagText = driver.findElement(dashboardTag).getText();
        if(dashboardText.contains("(Inactive)")) {
            dashboardTagText = dashboardTagText + "  (Inactive)";
        }
        System.out.println(dashboardText+ "##########"+dashboardTagText);
        Assert.assertEquals(dashboardText,dashboardTagText);
    }

    public void chooseActiveDashboard(Random rnd){
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        int activeDashboardsNumber = driver.findElements(activeDashboard).size();
        int numberOfActiveDashboard = rnd.nextInt(activeDashboardsNumber-1)+1;;
        WebElement activeDashboardToClick = scrollElementIntoView(By.xpath(String.format(activeDashboardInList,numberOfActiveDashboard)));
        activeDashboardToClick.click();
    }

    public void chooseInActiveDashboard(Random rnd){
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        int inActiveDashboardsNumber = driver.findElements(inActiveDashboard).size();
        int numberOfInActiveDashboard = 0;
        if(inActiveDashboardsNumber == 1){
            numberOfInActiveDashboard = 1;
        }
        else {
            numberOfInActiveDashboard = rnd.nextInt(inActiveDashboardsNumber-1)+1;;
        }
        WebElement inActiveDashboardToClick = scrollElementIntoView(By.xpath(String.format(inActiveDashboardInAList,numberOfInActiveDashboard)));
        inActiveDashboardToClick.click();
    }
    public void beginAddingNewPanel(String validUsername,String validPassword) {
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validUsername, true);
        logInPage.providePassword(validPassword, true);
        chooseActiveDashboard(rnd);
        //deleteAllPanelsFromDashboard();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        }catch (TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        }
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

    public void deleteDashboard(String dashboardName){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }
        driver.findElement(dashboardDropdownButton).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(scrollElementIntoView(By.xpath(String.format(addedDashboard, dashboardName)))));
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile,new File("target/screenshots/screenshot.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }catch (NoSuchElementException e){
            wait.until(ExpectedConditions.visibilityOf(scrollElementIntoView(By.xpath(String.format(addedDashboard, dashboardName)))));
        }
        WebElement dashboardToCheck = scrollElementIntoView(By.xpath(String.format(addedDashboard,dashboardName)));
        dashboardToCheck.click();
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteOption));
        driver.findElement(deleteOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteConfirm));
        driver.findElement(deleteConfirm).click();
    }

    public void closeCreateDashboardWindow(String dashboardName){
        Boolean dashboardCreated  = true;
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeCreateDashboardWindowIcon));
        driver.findElement(closeCreateDashboardWindowIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeCreateDashboardWindowButton));
        driver.findElement(closeCreateDashboardWindowButton).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        try{
            scrollElementIntoView(By.xpath(String.format(specificDashboard,dashboardName)));
        }catch(NoSuchElementException e){
            dashboardCreated = false;
        }
        Assert.assertFalse(dashboardCreated);
    }
    public void closeDeleteDashboardOperationOnRandomDashboard(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        int numberOfDashboards = driver.findElements(dashboard).size();
        int indexOfDashboard = rnd.nextInt(numberOfDashboards-1)+1;
        WebElement randomDashboard = scrollElementIntoView(By.xpath(String.format(dashboardInAList,indexOfDashboard)));
        String dashboardName = randomDashboard.getText();
        randomDashboard.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteOption));
        driver.findElement(deleteOption).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(closeDeleteDashboardWindowIcon));
        driver.findElement(closeDeleteDashboardWindowIcon).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.visibilityOf(scrollElementIntoView(By.xpath(String.format(specificDashboard,dashboardName)))));
    }
    public void setInvalidCustomRangeTime(String data) {
        wait.until(ExpectedConditions.elementToBeClickable(customRangeButton));
        driver.findElement(customRangeButton).click();
        int startTimesNumber = rnd.nextInt(driver.findElements(startTimes).size()) + 1;
        int endTimesNumber = 1;
        if (startTimesNumber != 1) {
            endTimesNumber = rnd.nextInt(startTimesNumber - 1) + 1;
        }
        driver.findElement(startDateInput).sendKeys(data);
        driver.findElement(endDateInput).sendKeys(data);
        driver.findElement(startTimesArrow).click();
        WebElement startTimeElement = scrollElementIntoView(By.xpath(String.format(startTimeInList, startTimesNumber)));
        startTimeElement.click();
        driver.findElement(endTimesArrow).click();
        WebElement endTimeElement = scrollElementIntoView(By.xpath(String.format(endTimeInList, endTimesNumber)));
        endTimeElement.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(driver.findElements(monitorButton).get(3))));
    }
    public void getNumberOfActiveDashboardsBeforeAction(String validUsername,String validPassword){
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validUsername,true);
        logInPage.providePassword(validPassword,true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
        driver.findElement(settingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeDashboardsNumberInformation));
        WebElement activeDashboardsNumber = driver.findElement(activeDashboardsNumberInformation);
        numberOfActiveDashboardsBefore = Integer.parseInt(activeDashboardsNumber.getText().substring(0,2));
    }
    public void checkNumberOfActiveDashboardsAfterAction(int action){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
        driver.findElement(settingsIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeDashboardsNumberInformation));
        WebElement activeDashboardsNumber = driver.findElement(activeDashboardsNumberInformation);
        int numberOfActiveDashboardsAfter = Integer.parseInt(activeDashboardsNumber.getText().substring(0,2));
        if(action == 1) {
            Assert.assertEquals(numberOfActiveDashboardsBefore + 1, numberOfActiveDashboardsAfter);
        }
        if(action == 0){
            Assert.assertEquals(numberOfActiveDashboardsBefore,numberOfActiveDashboardsAfter);
        }
        if(action == -1){
            System.out.println(numberOfActiveDashboardsBefore);
            System.out.println(numberOfActiveDashboardsAfter);
            Assert.assertEquals(numberOfActiveDashboardsBefore - 1, numberOfActiveDashboardsAfter);
        }
    }
    public void deleteSpecificDashboard(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteOption));
        driver.findElement(deleteOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteConfirm));
        driver.findElement(deleteConfirm).click();
    }
    public void editSpecificDashboard(String transformTo,Random rnd,String startDate,String endDate){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        driver.findElement(editOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameEnabledButton));
        driver.findElement(nameEnabledButton).click();
        if(transformTo == "inActive"){
            wait.until(ExpectedConditions.visibilityOfElementLocated(inActiveButton));
            driver.findElement(inActiveButton).click();
        }
        if(transformTo == "Active"){
            wait.until(ExpectedConditions.visibilityOfElementLocated(activeButton));
            driver.findElement(activeButton).click();
        }
        if(transformTo == "Custom Range"){
            wait.until(ExpectedConditions.elementToBeClickable(customRangeButton));
            driver.findElement(customRangeButton).click();
            int startTimesNumber = rnd.nextInt(driver.findElements(startTimes).size())+1;
            int endTimesNumber = rnd.nextInt(driver.findElements(endTimes).size())+1;
            setCustomRangeDateAndTime(startTimesNumber,endTimesNumber,startDate,endDate);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(editMonitoringButton));
        driver.findElement(editMonitoringButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmEditButton));
        driver.findElement(confirmEditButton).click();
    }
}
