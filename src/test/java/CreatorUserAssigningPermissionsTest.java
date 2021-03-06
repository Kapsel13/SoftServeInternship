import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;
import usersPermissions.CreatorUserPermission;

import java.util.HashMap;

public class CreatorUserAssigningPermissionsTest extends TestPreparation {
    private String city;
    private String validDashboardName;
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
    public void testAssigningReadOnlyPermissionToEditorUser(){
        System.out.println(city);
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"",editorUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"");
    }
    @Test(retryAnalyzer = Retry.class)
    public void testAssigningEditPermissionToEditorUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"edit",editorUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validEditorUsername,validEditorPassword,validDashboardName,"edit");
    }
    @Test(retryAnalyzer = Retry.class)
    public void testAssigningReadOnlyPermissionToReadOnlyUser(){
        CreatorUserPermission creatorUserPermission = new CreatorUserPermission(driver,wait);
        System.out.println("dashboardName: "+testArguments.get("dashboardName"));
        System.out.println("city: "+testArguments.get("city"));
        creatorUserPermission.addPermissionForUserAndLogOut(validCreatorUsername,validCreatorPassword,"",readOnlyUserCheckBox,city,validDashboardName);
        creatorUserPermission.checkAddedPermission(validReadOnlyUsername,validReadOnlyPassword,validDashboardName,"");
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
