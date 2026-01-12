import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class GeneratorForTest {
    private GeneratorForTest() {
    }

    public static String generrateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities = new String[]{"Пермь", "Киров", "Кострома", "Владимир", "Мурманск", "Ярославль", "Вологда", "Казань", "Уфа", "Ижевск"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String local) {
        var faker = new Faker(new Locale(local));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String local) {
        var faker = new Faker(new Locale(local));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registation {
        private Registation() {
        }

        public static UserInfo generateUser(String locate) {
            return new UserInfo(generateCity(), generateName(locate), generatePhone(locate));
        }

        @Value
        public static class UserInfo {
            String city;
            String name;
            String phone;
        }
    }
}
