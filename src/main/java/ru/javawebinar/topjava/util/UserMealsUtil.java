package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
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
        Map<Integer, Integer> caloriesMap = new HashMap<>();
        for (UserMeal meal : meals) {
            caloriesMap.put(meal.getDateTime().getDayOfMonth(), 0);
        }
        for (UserMeal meal : meals) {
            int day = meal.getDateTime().getDayOfMonth();
            caloriesMap.put(day, Integer.sum(caloriesMap.get(day), meal.getCalories()));
        }
        for (UserMeal meal : meals) {
            boolean excess = caloriesMap.get(meal.getDateTime().getDayOfMonth()) <= caloriesPerDay;
            int hour = meal.getDateTime().getHour();
            if (hour >= startTime.getHour() && hour < endTime.getHour()) {
                listUserMealWithExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess));
            }
        }
        return listUserMealWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<Integer, Integer> caloriesMap = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().getDayOfMonth(), Collectors.summingInt(UserMeal::getCalories)));
        return meals
                .stream()
                .filter(meal -> {
                    int hour = meal.getDateTime().getHour();
                    return hour >= startTime.getHour() && hour < endTime.getHour();
                })
                .map(meal -> {
                    boolean excess = caloriesMap.get(meal.getDateTime().getDayOfMonth()) <= caloriesPerDay;
                    return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
                })
                .collect(Collectors.toList());
    }
}
