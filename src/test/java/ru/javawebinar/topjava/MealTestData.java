package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;
    public static final int MEAL_3_ID = START_SEQ + 4;
    public static final int MEAL_4_ID = START_SEQ + 5;
    public static final int MEAL_5_ID = START_SEQ + 6;
    public static final int MEAL_6_ID = START_SEQ + 7;
    public static final int MEAL_7_ID = START_SEQ + 8;
    public static final int MEAL_8_ID = START_SEQ + 9;
    public static final int MEAL_9_ID = START_SEQ + 10;
    public static final int MEAL_10_ID = START_SEQ + 11;

    public static final Meal MEAL1 = new Meal(MEAL_1_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 9, 0), "Завтрак", 560);
    public static final Meal MEAL2 = new Meal(MEAL_2_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 14, 40), "Обед", 490);
    public static final Meal MEAL3 = new Meal(MEAL_3_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 18, 10), "Быстрый перекус", 120);
    public static final Meal MEAL4 = new Meal(MEAL_4_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 21, 0), "Ужин", 900);
    public static final Meal MEAL5 = new Meal(MEAL_5_ID, LocalDateTime.of(2023, Month.FEBRUARY, 22, 10, 0), "Завтрак", 400);
    public static final Meal MEAL6 = new Meal(MEAL_6_ID, LocalDateTime.of(2023, Month.FEBRUARY, 22, 15, 50), "Обед", 600);
    public static final Meal MEAL7 = new Meal(MEAL_7_ID, LocalDateTime.of(2023, Month.FEBRUARY, 22, 22, 0), "Ужин", 1100);
    public static final Meal MEAL8 = new Meal(MEAL_8_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 10, 0), "(админ) Завтрак", 400);
    public static final Meal MEAL9 = new Meal(MEAL_9_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 15, 50), "(админ) Обед", 600);
    public static final Meal MEAL10 = new Meal(MEAL_10_ID, LocalDateTime.of(2023, Month.FEBRUARY, 21, 22, 0), "(админ) Ужин", 1100);

    public static final List<Meal> USER_MEALS = new ArrayList<>(Arrays.asList(MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));
    public static final List<Meal> ADMIN_MEALS = new ArrayList<>(Arrays.asList(MEAL10, MEAL9, MEAL8));
    public static final LocalDate START_DATE = LocalDate.of(2023, Month.FEBRUARY, 21);
    public static final LocalDate END_DATE = LocalDate.of(2023, Month.FEBRUARY, 22);
    public static final LocalDateTime NEW_MEAL_DATETIME = LocalDateTime.of(2023, Month.FEBRUARY, 25, 12, 0);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
