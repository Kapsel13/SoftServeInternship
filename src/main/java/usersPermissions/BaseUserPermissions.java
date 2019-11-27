package usersPermissions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class BaseUserPermissions {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Random rnd = new Random();

    public BaseUserPermissions(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    protected void waitUntillAllElementsVisible(List<By> elements) {
        for (By element : elements) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
    }

    protected void waitUntillAllElementsClickable(List<By> elements) {
        for (By element : elements) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public WebElement scrollElementIntoView(By by) {
        org.openqa.selenium.WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
        return webElement;
    }
}
