import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import panels.LiveVideoStreamPanel;

public class LiveVideoStreamPanelTest extends TestPreparation {
    String liveVideoStreamName = "";
    String liveStreamSourceUrl = "";
    @BeforeMethod
    public void setUp(){
        liveVideoStreamName = TestData.generateData(12);
        liveStreamSourceUrl = TestData.getVideoStream();
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.beginAddingNewPanel(validAdminUsername,validAdminPassword);
    }
    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnAddingLiveVideoStreamPanel(){
        LiveVideoStreamPanel liveVideoStreamPanel = new LiveVideoStreamPanel(driver,wait);
        liveVideoStreamPanel.checkAddingLiveVideoStreamPanel(liveVideoStreamName,liveStreamSourceUrl);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testResponseOnStopAndStartVideo(){
        LiveVideoStreamPanel liveVideoStreamPanel = new LiveVideoStreamPanel(driver,wait);
        liveVideoStreamPanel.checkStopAndStartVideo(validAdminUsername,validAdminPassword);
    }
}
