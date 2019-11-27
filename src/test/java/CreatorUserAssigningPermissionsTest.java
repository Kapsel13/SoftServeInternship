import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import usersPermissions.CreatorUserPermission;

public class CreatorUserAssigningPermissionsTest extends TestPreparation {
    private String city;
    private String validDashboardName;
    private By editorUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'editor test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    @Before
    public void setUp(){
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
    }
    @Test
    public void testAssigningReadOnlyPermissionToEditorUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"",editorUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"");
    }
    @Test
    public void testAssigningEditPermissionToEditorUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"edit",editorUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"edit");
    }
    @Test
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"",readOnlyUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,validDashboardName,"");
    }
}
