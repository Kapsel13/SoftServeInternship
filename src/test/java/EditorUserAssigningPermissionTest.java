
import org.testng.annotations.Test;
import usersPermissions.EditorUserPermission;


public class EditorUserAssigningPermissionTest extends TestPreparation {

    @Test
    public void testAssigningReadOnlyPermissionToEditorUser(){
        EditorUserPermission editorUserPermission = new EditorUserPermission(driver,wait);
        editorUserPermission.addPermissionForUserAndLogOut(validEditorUsername,validEditorPassword,"editor");
        editorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,"editor");
    }

    @Test
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        EditorUserPermission editorUserPermission = new EditorUserPermission(driver,wait);
        editorUserPermission.addPermissionForUserAndLogOut(validEditorUsername,validEditorPassword,"read-only");
        editorUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,"readOnly");
    }
}
