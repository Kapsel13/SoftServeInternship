import java.util.List;
import java.util.Random;

public class TestData {

    public  static String generateData(int limit) {
        int dataLength = (int)(Math.random()*(limit-4) + 4);
        String data = "";
        for(int i = 0; i<dataLength; i++){
            int asciiCode = (int)(Math.random()*94 + 33);
            char dataElement = (char) asciiCode;
            data = data + dataElement;
        }
        return data;
    }

    public static String getRandomCity(List<String> locations, Random rnd) {
        return locations.get(rnd.nextInt(locations.size()));
    }

}
