package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;

class MealRestControllerTest  extends AbstractControllerTest {

    private static final String MEAL_URL = MealRestController.MEAL_URL + '/';

    @Autowired
    private MealService mealService;

}