package panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LogInPage;

import java.util.Arrays;

public class HourDailyForecastPanel extends BasePanel {

    private By confirmChoosingPanelButton = By.xpath("//input[@value='Ok']");
    private By addPanelButton = By.xpath("//button[contains(text(),'Add Panel')]");
    private By hoursAndDailyForecastPanelOption = By.xpath("//div[@class='panel-title' and contains(., 'Hourly & Daily')]");
    private By hoursAndDailyForecastTagText = By.xpath("(//p[contains(text(),'Hourly')])[1]");
    private By hoursAndDailyForecastTime = By.xpath("(//h3[@class='time'])[1]");
    private By hoursAndDailyForecastMenuHourOption = By.xpath("(//div[contains(@class,'controls') and contains(.,'Hourly')])[1]");
    private By dailyButton = By.xpath("//button[contains(text(),'Daily')]");
    private By dayButton = By.xpath("//button[contains(text(),'Day')]");
    private By nightButton = By.xpath("//button[contains(text(),'Night')]");
    private By dropdownArrow = By.xpath("(//div[contains(@class,'expand-icon')])[1]");
    private By todayHeader = By.xpath("//h3[contains(text(),'Today')]");
    private By sunriseInfo = By.xpath("//div[contains(text(),'Sunrise')]");
    private By sunsetInfo = By.xpath("//div[contains(text(),'Sunset')]");
    private By uvIndex = By.xpath("//div[contains(text(),'UV')]");
    private By tonightHeader = By.xpath("//h3[contains(text(),'Tonight')]");
    private By moonRise = By.xpath("//div[contains(text(),'Moonrise')]");
    private By moonSet = By.xpath("//div[contains(text(),'Moonset')]");
    private By moonPhase = By.xpath("//div[contains(text(),'Phase')]");
    protected By addPanelOption = By.xpath("//span[contains(text(),'Add Panel')]");
    public HourDailyForecastPanel(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }


    public void addNewHourDailyForecastPanel(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoursAndDailyForecastPanelOption));
        driver.findElement(hoursAndDailyForecastPanelOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmChoosingPanelButton));
        driver.findElement(confirmChoosingPanelButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelButton));
        driver.findElement(addPanelButton).click();
        waitUntillAllElementsVisible(Arrays.asList(hoursAndDailyForecastTagText,hoursAndDailyForecastTime,hoursAndDailyForecastMenuHourOption));
    }
    public void checkDisplayingContentOfDayTag(String username,String password){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.chooseActiveDashboard(rnd);
        dashboardPage.deleteAllPanelsFromDashboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        driver.findElement(addPanelOption).click();
        addNewHourDailyForecastPanel();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dailyButton));
        driver.findElement(dailyButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownArrow));
        driver.findElement(dropdownArrow).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dayButton));
        driver.findElement(dayButton).click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(todayHeader)));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(sunriseInfo)));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(sunsetInfo)));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(uvIndex)));
    }
    public void checkDisplayContentOfNightTag(String username,String password){
        LogInPage logInPage = new LogInPage(driver,wait);
        logInPage.provideUsername(username,true);
        logInPage.providePassword(password,true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DashboardPage dashboardPage = new DashboardPage(driver,wait);
        dashboardPage.chooseActiveDashboard(rnd);
        dashboardPage.deleteAllPanelsFromDashboard();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPanelOption));
        driver.findElement(addPanelOption).click();
        addNewHourDailyForecastPanel();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dailyButton));
        driver.findElement(dailyButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownArrow));
        driver.findElement(dropdownArrow).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nightButton));
        driver.findElement(nightButton).click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(tonightHeader)));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(moonRise)));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(moonSet)));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.scrollElementIntoView(moonPhase)));

    }
}
