package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> listUserMealWithExcess = new ArrayList<>();
        int day = meals.get(0).getDateTime().getDayOfMonth();
        int calories = caloriesPerDay;
        for (UserMeal meal : meals) {
            if (meal.getDateTime().getDayOfMonth() == day) {
                calories -= meal.getCalories();
            } else {
                day = meal.getDateTime().getDayOfMonth();
                calories = caloriesPerDay - meal.getCalories();
            }
            int hour = meal.getDateTime().getHour();
            if (hour >= startTime.getHour() && hour < endTime.getHour()) {
                listUserMealWithExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), calories >= 0));
            }
        }
        return listUserMealWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final int[] day = {meals.get(0).getDateTime().getDayOfMonth()};
        final int[] calories = {caloriesPerDay};
        return meals
                .stream()
                .filter(meal -> {
                    if (meal.getDateTime().getDayOfMonth() == day[0]) {
                        calories[0] -= meal.getCalories();
                    } else {
                        day[0] = meal.getDateTime().getDayOfMonth();
                        calories[0] = caloriesPerDay - meal.getCalories();
                    }
                    int hour = meal.getDateTime().getHour();
                    return hour >= startTime.getHour() && hour < endTime.getHour();
                })
                .map(meal -> new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), calories[0] >= 0))
                .collect(Collectors.toList());
    }
}
