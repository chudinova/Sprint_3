import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OrderGenerator {

    public Order getRandom(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String metroStation = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomAlphanumeric(11);
        int rentTime = Integer.parseInt(RandomStringUtils.randomNumeric(3));
        String deliveryDate = "2022-03-20";
        String comment = RandomStringUtils.randomAlphabetic(10);

/*        String firstName = "Приориориориро";
        String lastName = "Приориориориро";
        String address = "Приориориориро";
        String metroStation = "Приориориориро";
        String phone = "Приориориориро";
        int rentTime = 1;
        String deliveryDate = "2022-03-20";
        String comment = "Приориориориро";*/

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
    }

}