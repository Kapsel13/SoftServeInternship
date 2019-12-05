package usersPermissions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LogInPage;

public class AdminUserPermission extends BaseUserPermissions {

    private By editorUserPermissionDropdown = By.xpath("//div[@class='body-row' and contains(.,'editor test')]//svg-icon");
    private By createButton = By.xpath("//button[contains(text(),'Create')]");
    private By creatorUserPermissionDropdown = By.xpath("//div[@class='body-row' and contains(.,'creator test')]//svg-icon");
    private By editPermission = By.xpath("//div[@aria-labelledby='assignPeoplePermissionDropdown']//button[contains(text(),'Edit')]");
    private By dashboardDropdownButton = By.xpath("//button[contains(@class,'dropdown-toggle')]");
    private By editOption =By.xpath("//span[contains(@class,'dropdown-item-title') and contains(text(),'Edit Selected')]");
    private By deleteOption = By.xpath("//span[@class='dropdown-item-title' and contains(text(),'Delete Selected')]");
    private String addedDashboard = "//span[contains(@class,'dropdown-item-title') and contains(text(),'%s')]";
    public AdminUserPermission(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public void addPermissionForUserAndLogOut(String username, String password, String permissionTarget, String permissionType, By checkBox, String city, String validDashboardName){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginCreatingNewDashboard();
        dashboardPage.selectValidLocation(city,city);
        dashboardPage.selectValidName(validDashboardName);
        dashboardPage.setActiveMonitoring();
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
        driver.findElement(checkBox).click();
        if(permissionType.equals("edit")){
            if(permissionTarget.equals("editor")) {
                driver.findElement(editorUserPermissionDropdown).click();
            }
            if(permissionTarget.equals("creator")){
                driver.findElement(creatorUserPermissionDropdown).click();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(editPermission));
            driver.findElement(editPermission).click();
        }
        driver.findElement(createButton).click();
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){}
        dashboardPage.logOut();
    }

    public void checkAddedPermission(String username, String password, String validDashboardName, String permissionTarget, String permissionType) {
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(username, true);
        logInPage.providePassword(password, true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BaseUserPermissions baseUserPermissions = new BaseUserPermissions(driver,wait);
        WebElement dashboardToCheck = baseUserPermissions.scrollElementIntoView(By.xpath(String.format(addedDashboard,validDashboardName)));
        dashboardToCheck.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        if(permissionType.equals("edit")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
        }
        else{
            wait.until(ExpectedConditions.invisibilityOfElementLocated(editOption));
            if(permissionTarget.equals("creator")){
                wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteOption));
            }
        }
    }
}
