package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_ID_1 = START_SEQ+3;
    public static final int MEAL_ID_2 = START_SEQ+4;
    public static final int MEAL_ID_3 = START_SEQ+5;
    public static final int NOT_FOUND = 10;
    public static final LocalDate START_DATE = LocalDate.of(2023,4,18);
    public static final LocalDate END_DATE = LocalDate.of(2023,4,19);

    public static final Meal meal1 = new Meal(MEAL_ID_1,
            LocalDateTime.of(2023, 4, 18, 22, 17,0),
            "Овсянка на молоке",
            100);
    public static final Meal meal2 = new Meal(MEAL_ID_2,
            LocalDateTime.of(2023, 4, 18, 22, 19,0),
            "Картофельное пюре",
            400);
    public static final Meal meal3 = new Meal(MEAL_ID_3,
            LocalDateTime.of(2023, 4, 20, 22, 20,0),
            "Куриная котлета",
            500);

    public static Meal getNew() {
        return new Meal(null,
                LocalDateTime.of(2023, 1, 1, 1, 0,0),
                "New",
                1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
        updated.setDateTime(LocalDateTime.of(2023, 2, 1, 1, 0,0));
        updated.setDescription("Updated meal");
        updated.setCalories(2000);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
