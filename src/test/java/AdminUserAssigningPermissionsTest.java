import org.openqa.selenium.By;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;
import usersPermissions.AdminUserPermission;

import java.util.HashMap;


public class AdminUserAssigningPermissionsTest extends TestPreparation {

    private String city;
    private String validDashboardName;
    private By creatorCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'creator test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By editorUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'editor test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    HashMap<String ,String> testArguments= new HashMap<>();
    @BeforeMethod
    public void setUp(){
        if (testArguments.isEmpty()) {
            validDashboardName = "!-auto_test-" + TestData.generateData(8);
            city = TestData.getRandomCity();
            testArguments.put("dashboardName", validDashboardName);
            testArguments.put("city", city);
        }
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningReadOnlyPermissionsToCreatorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"creator","",creatorCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validCreatorUsername,validCreatorPassword,validDashboardName,"creator","");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningEditPermissionToCreatorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"creator","edit",creatorCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validCreatorUsername,validCreatorPassword,validDashboardName,"creator","edit");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningReadOnlyPermissionsToEditorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"editor","",editorUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"editor","");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningEditPermissionsToEditUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"editor","edit",editorUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"editor","edit");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"readOnly","",readOnlyUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,validDashboardName,"readOnly","");
    }
    @AfterMethod
    public void deleteAddedDashboard(){
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.logOut();
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.provideUsername(validAdminUsername, true);
        logInPage.providePassword(validAdminPassword, true);
        dashboardPage.deleteDashboard(validDashboardName);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
