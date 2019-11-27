package usersPermissions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LogInPage;


public class EditorUserPermission extends BaseUserPermissions {
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By dashboardDropdownButton = By.xpath("//button[contains(@class,'dropdown-toggle')]");
    private By dashboards = By.xpath("//div[contains(@class,'dropdown-menu')]//button");
    private String specificDashboard = "(//div[contains(@class,'dropdown-menu')]//button)[%d]";
    private By editOption =By.xpath("//span[@class='dropdown-item-title' and contains(text(),'Edit Selected')]");
    private By confirmNameButton = By.xpath("(//button[contains(text(),'Next')])[1]");
    private By confirmMonitoringButton = By.xpath("(//button[contains(text(),'Next')])[2]");
    private By readOnlyPermission = By.xpath("(//div[@aria-labelledby='assignPeoplePermissionDropdown']//button[contains(text(),'Read-Only')])[2]");
    private By editorUserPermissionDropdown = By.xpath("//div[@class='body-row' and contains(.,'editor test')]//svg-icon");
    private By saveEditionButton = By.xpath("//button[contains(text(),'Save')]");
    public EditorUserPermission(WebDriver driver, WebDriverWait wait){super(driver, wait);}

    public void addPermissionForUserAndLogOut(String username,String password,String permissionUser){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        int numberOfDashboards = driver.findElements(dashboards).size();
        int dashboardIndex = 1;
        boolean editableDashboard = false;
        while((dashboardIndex<=numberOfDashboards)&&(!editableDashboard)){
            WebElement dashboardInAList = scrollElementIntoView(By.xpath(String.format(specificDashboard,dashboardIndex)));
            dashboardInAList.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
            driver.findElement(dashboardDropdownButton).click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
                driver.findElement(editOption).click();
                editableDashboard = true;
            }
            catch(TimeoutException e){
                dashboardIndex++;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNameButton));
        driver.findElement(confirmNameButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMonitoringButton));
        driver.findElement(confirmMonitoringButton).click();
        if(permissionUser.equals("editor")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(editorUserPermissionDropdown));
            driver.findElement(editorUserPermissionDropdown).click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(readOnlyPermission));
            driver.findElement(readOnlyPermission).click();
        }
        else{
            wait.until(ExpectedConditions.visibilityOfElementLocated(readOnlyUserCheckBox));
            driver.findElement(readOnlyUserCheckBox).click();
        }
        driver.findElement(saveEditionButton).click();
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){}
        dashboardPage.logOut();
    }

    public void checkAddedPermission(String username, String password){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username, true);
        logInPage.providePassword(password, true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(editOption));
    }
}
