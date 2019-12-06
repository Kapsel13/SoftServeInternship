
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import usersPermissions.EditorUserPermission;

public class EditorUserAssigningPermissionTest extends TestPreparation {
    private String city;
    private String validDashboardName;


    @BeforeMethod
    public void setUp(){
        validDashboardName = "!-auto_test-"+TestData.generateData(8);
        city = TestData.getRandomCity();
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningReadOnlyPermissionToEditorUser(){
        EditorUserPermission editorUserPermission = new EditorUserPermission(driver,wait);
        editorUserPermission.addPermissionForUserAndLogOut(validEditorUsername,validEditorPassword,"editor");
        editorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,"editor");
    }

    @Test(retryAnalyzer = Retry.class)
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        EditorUserPermission editorUserPermission = new EditorUserPermission(driver,wait);
        editorUserPermission.addPermissionForUserAndLogOut(validEditorUsername,validEditorPassword,"read-only");
        editorUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,"readOnly");
    }
}
