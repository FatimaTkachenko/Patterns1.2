package ru.netology.delivery;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataGenerator {

    public static String generateDate(int shift) {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.now().plusDays(shift).format(formatter);
    }
}
