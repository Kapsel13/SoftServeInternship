package panels;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.interactions.Interactive;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Test {
    protected static Random rnd = new Random();
    @org.testng.annotations.Test
    public void test() {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/Users/mkowal/IdeaProjects/WeatherApplication/src/main/java/panels/params-ranges.json"));
            JSONObject currentDataPanelJson = (JSONObject) jsonObject.get("CDP");
            JSONObject weatherTypeJson = (JSONObject) currentDataPanelJson.get("Daily Precipitation");
            JSONObject unitJson = (JSONObject) weatherTypeJson.get("in");
            int max = Integer.parseInt(String.valueOf(unitJson.get("max")))-1;
            int min = Integer.parseInt(String.valueOf(unitJson.get("min")))+1;
            int unitNumber = rnd.nextInt(max-min)+min;
        } catch (
                FileNotFoundException e) {
        } catch (IOException | ParseException e) {
        }
    }
    @org.testng.annotations.Test
    public void three(){
        int filterIndex = rnd.nextInt(3)+1;
        System.out.println(filterIndex);
    }
}
