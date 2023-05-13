import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderGenerator {
    // Генератор даты
    static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    static String date = formatter.format(new Date());

    public static Order getRandomFullData(){
        final String firstName = RandomStringUtils.randomAlphabetic(5);
        final String lastName = RandomStringUtils.randomAlphabetic(7);
        final String address = RandomStringUtils.randomAlphabetic(10);
        final String metroStation = "Краснопресненская";
        final String phone = "+7" + RandomStringUtils.randomNumeric(10);
        final String rentTime = RandomStringUtils.randomNumeric(1);
        final String deliveryDate = date.toString();
        final String comment = RandomStringUtils.randomAlphabetic(10);
        final List<String> color = Arrays.asList("BLACK");
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public static Order getRandomFullDataWithSeveralColours(){
        final String firstName = RandomStringUtils.randomAlphabetic(5);
        final String lastName = RandomStringUtils.randomAlphabetic(7);
        final String address = RandomStringUtils.randomAlphabetic(10);
        final String metroStation = "Саларьево";
        final String phone = "+7" + RandomStringUtils.randomNumeric(10);
        final String rentTime = RandomStringUtils.randomNumeric(1);
        final String deliveryDate = date.toString();
        final String comment = RandomStringUtils.randomAlphabetic(10);
        final List<String> color = Arrays.asList("BLACK", "GREY");
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public static Order getRandomDataWithoutColour(){
        final String firstName = RandomStringUtils.randomAlphabetic(5);
        final String lastName = RandomStringUtils.randomAlphabetic(7);
        final String address = RandomStringUtils.randomAlphabetic(10);
        final String metroStation = "Киевская";
        final String phone = "+7" + RandomStringUtils.randomNumeric(10);
        final String rentTime = RandomStringUtils.randomNumeric(1);
        final String deliveryDate = date.toString();
        final String comment = RandomStringUtils.randomAlphabetic(10);
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, null);
    }
}
