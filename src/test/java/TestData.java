import java.util.*;

public class TestData {

     protected static List<String> cities = Arrays.asList("Berlin", "Sydney", "New York", "London");
     protected static List<String> zipCodes = Arrays.asList("10785","2000","10013","EC3N 4");
     protected static List<String> gpsCoords = Arrays.asList("52.5170365,13.3888599","-33.8548157,151.2164539","40.7127281,-74.0060152","51.5073219,-0.1276474");
     protected static List<String> icaoCodes = Arrays.asList("EDDB","YSBK","JFK","EGLL");
     protected static List<String> startDate = Arrays.asList("11/11/2014","05/22/2015","07/13/2015","04/26/2016");
     protected static List<String> endDate = Arrays.asList("02/13/2017","08/21/2017","10/28/2018","06/19/2017");
     protected static List<String> startDateBeforeCurrentDate = Arrays.asList("11/11/2019","09/24/2019","12/20/2019");
     protected static List<String> endDateAfterCurrentDate = Arrays.asList("12/29/2019","01/24/2020","03/05/2020");
     protected static Random rnd = new Random();

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

    public static String getRandomCity() {
        return cities.get(rnd.nextInt(cities.size()));
    }
    public static String getRandomZipCode(){
        return zipCodes.get(rnd.nextInt(zipCodes.size()));
    }
    public static String getRandomStartDate(){
        return startDate.get(rnd.nextInt(startDate.size()));
    }
    public static String getRandomEndDate(){
        return endDate.get(rnd.nextInt(endDate.size()));
    }
    public static String getRandomStartDateBeforeCurrentDate(){
        return startDateBeforeCurrentDate.get(rnd.nextInt(startDateBeforeCurrentDate.size()));
    }
    public static String getRandomEndDateAfterCurrentDate(){
        return endDateAfterCurrentDate.get(rnd.nextInt(endDateAfterCurrentDate.size()));
    }
    public static String getRandomGpsCoords(){
        return gpsCoords.get(rnd.nextInt(gpsCoords.size()));
    }
    public static String getRandomIcaoCodes() {
        return icaoCodes.get(rnd.nextInt(icaoCodes.size()));
    }

}
