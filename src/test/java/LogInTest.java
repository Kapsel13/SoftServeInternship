import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.DashboardPage;
import pages.LogInPage;

public class LogInTest extends TestPreparation {


    private String invalidUsername;
    private String invalidPassword;

    @Before
    public void setUp() {
        invalidUsername = TestData.generateData(12);
        invalidPassword = TestData.generateData(12);
    }

    @Test
    public void testResponseOnInvalidAdminUsername(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(invalidUsername,false);
    }

    /*@Test
    public void testResponseOnInvalidAdminPassword() {
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(invalidPassword,false);
    }

    @Test
    public void testResponseOnValidAdminPasswordAndLogOut() {
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.logOut();
    }

    @Test
    public void testResponseOnInvalidCreatorPassword(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validCreatorUsername,true);
        logInPage.providePassword(invalidPassword,false);
    }

    @Test
    public void testResponseOnValidCreatorPasswordAndLogOut(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validCreatorUsername,true);
        logInPage.providePassword(validCreatorPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.logOut();
    }

    @Test
    public void testResponseOnInvalidEditorPassword(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validEditorUsername,true);
        logInPage.providePassword(invalidPassword,false);
    }

    @Test
    public void testResponseOnValidEditorPasswordAndLogOut(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validEditorUsername,true);
        logInPage.providePassword(validEditorPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.logOut();
    }
    @Test
    public void testResponseOnInvalidReadOnlyPassword(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validReadOnlyUsername,true);
        logInPage.providePassword(invalidPassword,false);
    }
    @Test
    public void testResponseOnValidReadOnlyPasswordAndLogOut(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validReadOnlyUsername,true);
        logInPage.providePassword(validReadOnlyPassword,true);
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.logOut();
    }*/
}
