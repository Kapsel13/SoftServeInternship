package panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;

public class LiveVideoStreamPanel extends BasePanel{

    private By liveVideoStreamPanelOption = By.xpath("//span[contains(text(),'Video Stream')]");
    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By liveStreamNameInput = By.xpath("(//input)[1]");
    private By liveStreamSourceUrl = By.xpath("(//input)[2]");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By video = By.xpath("//video");
    private By videoHeader = By.xpath("//p[@class='header-title']");
    private By videoArea = By.xpath("//div[contains(@class,'video-js')]");
    private By videoPaused = By.xpath("//div[contains(@class,'video-js')]");
    private By videoStarted = By.xpath("//div[contains(@class,'vjs-playing')]");
    public LiveVideoStreamPanel(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkAddingLiveVideoStreamPanel(String videoName,String videoSource){
        wait.until(ExpectedConditions.visibilityOfElementLocated(liveVideoStreamPanelOption));
        driver.findElement(liveVideoStreamPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(liveStreamNameInput));
        driver.findElement(liveStreamNameInput).clear();
        driver.findElement(liveStreamNameInput).sendKeys(videoName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(liveStreamSourceUrl));
        driver.findElement(liveStreamSourceUrl).sendKeys(videoSource);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        waitUntillAllElementsVisible(Arrays.asList(video,videoHeader));
        WebElement videoHeaderContent = driver.findElement(videoHeader);
        String videoHeaderText = videoHeaderContent.getText();
        System.out.println(videoHeaderText);
        System.out.println(videoName);
        Assert.assertEquals(videoHeaderText,"Live Stream - "+videoName);
    }
    public void checkStopAndStartVideo(String videoName,String videoSource){
        wait.until(ExpectedConditions.visibilityOfElementLocated(liveVideoStreamPanelOption));
        driver.findElement(liveVideoStreamPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(liveStreamNameInput));
        driver.findElement(liveStreamNameInput).clear();
        driver.findElement(liveStreamNameInput).sendKeys(videoName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(liveStreamSourceUrl));
        driver.findElement(liveStreamSourceUrl).sendKeys(videoSource);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoArea));
        driver.findElement(videoArea).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoPaused));
        driver.findElement(videoArea).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoStarted));
    }
}
