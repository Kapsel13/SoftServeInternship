import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.interactions.Interactive;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TestExample {
    protected static Random rnd = new Random();
    SoftAssert softAssert = new SoftAssert();
    SoftAssert softAssert2 = new SoftAssert();
    @Test(enabled = false)
    public void test1(){
        System.out.println("Yes");
        Assert.assertEquals(true,true);
        System.out.println("enter username");
        System.out.println("enter password");
        softAssert.assertEquals(true,false,"enter password failed");
        System.out.println("click on the button");
        softAssert.assertAll();
    }
    @Test
    public void test2(){
        System.out.println("click on the settings icon");
        softAssert2.assertEquals(true,false,"settings icon is disabled");
        System.out.println("Log out");
        softAssert2.assertAll();
    }
}
