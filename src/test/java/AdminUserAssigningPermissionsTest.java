import org.openqa.selenium.By;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import usersPermissions.AdminUserPermission;


public class AdminUserAssigningPermissionsTest extends TestPreparation {

    private String city;
    private String validDashboardName;
    private By creatorCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'creator test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By editorUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'editor test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    @BeforeMethod
    public void setUp(){
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
    }

    @Test(priority = 1)
    public void testAssigningReadOnlyPermissionsToCreatorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"creator","",creatorCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validCreatorUsername,validCreatorPassword,validDashboardName,"creator","");
    }

    @Test(priority = 2)
    public void testAssigningEditPermissionToCreatorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"creator","edit",creatorCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validCreatorUsername,validCreatorPassword,validDashboardName,"creator","edit");
    }

    @Test(priority = 3)
    public void testAssigningReadOnlyPermissionsToEditorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"editor","",editorUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"editor","");
    }

    @Test(priority = 4)
    public void testAssigningEditPermissionsToEditUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"editor","edit",editorUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"editor","edit");
    }

    @Test(priority = 5)
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"readOnly","",readOnlyUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,validDashboardName,"readOnly","");
    }

}
