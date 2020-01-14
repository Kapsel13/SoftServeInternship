package panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class BasePanel {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Random rnd = new Random();

    public BasePanel(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }


    protected void waitUntillAllElementsVisible(List<By> elements) {
        for (By element : elements) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
    }


    protected static String generateAdditionalAlertMessage() {
        int dataLength = (int)(Math.random()*(50) );
        String data = "";
        for(int i = 0; i<dataLength; i++){
            int asciiCode = (int)(Math.random()*94 + 33);
            char dataElement = (char) asciiCode;
            data = data + dataElement;
        }
        return data;
    }

    protected String chooseRandomOption(By elements,String  element,String message,int returnOption){
        int numberOfElements = driver.findElements(elements).size();
        int elementIndex = 0;
        if(numberOfElements == 1){
            elementIndex = 1;
        }
        else{
            elementIndex = rnd.nextInt(numberOfElements-1)+1;
        }
        WebElement webElement = driver.findElement(By.xpath(String.format(element,elementIndex)));
        String webElementText = webElement.getText();
        System.out.println(message+webElementText);
        if(returnOption == 1){
            return webElementText;
        }
        else{
            return "";
        }
    }
}
