import org.junit.internal.matchers.SubstringMatcher;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.SummaryPage;

public class SummaryPageSliderTest extends TestPreparation {
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSettingSliderOnNow(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.checkSettingSlider("now");
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSettingSliderToday(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.checkSettingSlider("today");
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSettingSliderTomorrow(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.checkSettingSlider("tomorrow");
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnSettingSliderAll(){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(validAdminUsername,true);
        logInPage.providePassword(validAdminPassword,true);
        SummaryPage summaryPage = new SummaryPage(driver,wait);
        summaryPage.checkSettingSlider("all");
    }
}
