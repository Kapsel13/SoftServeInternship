package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait){
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
}
