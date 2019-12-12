package usersPermissions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LogInPage;

import java.io.File;
import java.io.IOException;


public class EditorUserPermission extends BaseUserPermissions {
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By dashboardDropdownButton = By.xpath("//button[@id='dashboard-dropdown']");
    private By dashboards = By.xpath("//div[contains(@class,'dropdown-menu')]//button");
    private String specificDashboard = "(//div[contains(@class,'dropdown-menu')]//button)[%d]";
    private By editOption =By.xpath("//span[contains(@class,'dropdown-item-title') and contains(text(),'Edit Selected')]");
    private By confirmNameButton = By.xpath("(//button[contains(text(),'Next')])[1]");
    private By confirmMonitoringButton = By.xpath("(//button[contains(text(),'Next')])[2]");
    private By readOnlyPermission = By.xpath("//div[@aria-labelledby='assignPeoplePermissionDropdown']//button[contains(text(),'Read-Only')]");
    private By editorUserPermissionDropdown = By.xpath("//div[@class='body-row' and contains(.,'editor test')]//svg-icon");
    private By saveEditionButton = By.xpath("//button[contains(text(),'Save')]");
    private String dashboardName = "";
    private String editedDashboard = "//span[contains(@class,'dropdown-item-title') and contains(text(),'%s')]";
    public EditorUserPermission(WebDriver driver, WebDriverWait wait){super(driver, wait);}

    public void addPermissionForUserAndLogOut(String username,String password,String permissionUser){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        }
        driver.findElement(dashboardDropdownButton).click();
        int numberOfDashboards = driver.findElements(dashboards).size();
        int dashboardIndex = 1;
        boolean editableDashboard = false;
        System.out.println("Before the loop");
        while((dashboardIndex<=numberOfDashboards)&&(!editableDashboard)){
            WebElement dashboardInAList = scrollElementIntoView(By.xpath(String.format(specificDashboard,dashboardIndex)));
            dashboardName = dashboardInAList.getText();
            dashboardInAList.click();
            try{
                wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
            }catch(TimeoutException e){
                driver.navigate().refresh();
                wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
            }
            driver.findElement(dashboardDropdownButton).click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
                driver.findElement(editOption).click();
                editableDashboard = true;
            }
            catch(TimeoutException e){
                dashboardIndex++;
            }
            System.out.println("After loop iteration");
        }
        System.out.println("After the loop");
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNameButton));
        driver.findElement(confirmNameButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMonitoringButton));
        driver.findElement(confirmMonitoringButton).click();
        if(permissionUser.equals("editor")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(editorUserPermissionDropdown));
            driver.findElement(editorUserPermissionDropdown).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(readOnlyPermission));
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
        System.out.println("After first method");
    }

    public void checkAddedPermission(String username, String password,String permissionUser){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username, true);
        logInPage.providePassword(password, true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
        driver.findElement(dashboardDropdownButton).click();
        //String specificDashboardName="";
        if(permissionUser=="readOnly"){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*int numberOfDashboards = driver.findElements(dashboards).size();
            boolean dashboardFound = false;
            int dashboardIndex=1;
            while((dashboardIndex<=numberOfDashboards)&&(!dashboardFound)){
                WebElement dashboardInAList = scrollElementIntoView(By.xpath(String.format(specificDashboard,dashboardIndex)));
                specificDashboardName = dashboardInAList.getText();
                if(specificDashboardName==dashboardName){
                    dashboardFound=true;
                    dashboardInAList.click();
                }
                else{
                    dashboardIndex++;
                }
            }*/
            BaseUserPermissions baseUserPermissions = new BaseUserPermissions(driver,wait);
            WebElement dashboardToCheck = baseUserPermissions.scrollElementIntoView(By.xpath(String.format(editedDashboard,dashboardName)));
            dashboardToCheck.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdownButton));
            driver.findElement(dashboardDropdownButton).click();
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(editOption));
        System.out.println("After second method");
    }
}
