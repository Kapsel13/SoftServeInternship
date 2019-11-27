import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import usersPermissions.AdminUserPermission;


public class AdminUserAssigningPermissionsTest extends TestPreparation {

    private String city;
    private String validDashboardName;
    private By creatorCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'creator test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By editorUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'editor test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    @Before
    public void setUp(){
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
    }

    @Test
    public void testAssigningReadOnlyPermissionsToCreatorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"creator","",creatorCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validCreatorUsername,validCreatorPassword,validDashboardName,"creator","");
    }

    @Test
    public void testAssigningEditPermissionToCreatorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"creator","edit",creatorCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validCreatorUsername,validCreatorPassword,validDashboardName,"creator","edit");
    }

    @Test
    public void testAssigningReadOnlyPermissionsToEditorUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"editor","",editorUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"editor","");
    }

    @Test
    public void testAssigningEditPermissionsToEditUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"editor","edit",editorUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"editor","edit");
    }

    @Test
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        AdminUserPermission adminUserPermission = new AdminUserPermission(driver,wait);
        adminUserPermission.addPermissionForUserAndLogOut(validAdminUsername,validAdminPassword,"readOnly","",readOnlyUserCheckBox,city,validDashboardName);
        adminUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,validDashboardName,"readOnly","");
    }

}
