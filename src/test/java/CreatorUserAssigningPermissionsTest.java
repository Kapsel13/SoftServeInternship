import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import usersPermissions.CreatorUserPermission;

public class CreatorUserAssigningPermissionsTest extends TestPreparation {
    private String city;
    private String validDashboardName;
    private By editorUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'editor test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    private By readOnlyUserCheckBox = By.xpath("(//div[@class='body-row' and contains(.,'readonly test')]//div[contains(@class,'mat-checkbox-inner-container')])[1]");
    @BeforeMethod
    public void setUp(){
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
    }
    @Test(priority = 1)
    public void testAssigningReadOnlyPermissionToEditorUser(){
        System.out.println(city);
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"",editorUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"");
    }
    @Test(priority = 2)
    public void testAssigningEditPermissionToEditorUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"edit",editorUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"edit");
    }
    @Test(priority = 3)
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"",readOnlyUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,validDashboardName,"");
    }
}
