package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class LogInPage extends BasePage {


    protected By loginError = By.xpath("//div[@class='error']");

    protected By loginInput = By.xpath("//input[@class='text-line']");
    protected By dashboardDropdownButton = By.xpath("//button[@id='dashboard-dropdown']");
    protected By loginButton = By.xpath("//button[@class='login-button']");
    protected By dashboardPageText = By.xpath("//span[contains(text(),'My Dashboards')]");
    protected By pageTitle = By.xpath("//div[@class='phoenix-product-name' and contains(.,'IBM')]");
    protected By settingsIcon = By.xpath("//section[@class='settings-icon-container']");

    public LogInPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void  provideUsername(String username,boolean valid){
        driver.findElement(loginInput).sendKeys(username);
        driver.findElement(loginButton).click();
        if(valid){
         wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));
        }
        else{
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginError));
        }
    }

    public void providePassword(String password, boolean valid){
        driver.findElement(loginInput).sendKeys(password);
        driver.findElement(loginButton).click();
        if(valid){
            waitUntillAllElementsVisible(Arrays.asList(pageTitle,settingsIcon));
        }
        else{
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginError));
        }
    }

}
