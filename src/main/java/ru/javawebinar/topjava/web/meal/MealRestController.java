package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.service.MealService;

//@Controller
public class MealRestController extends AbstractMealController {

    protected MealRestController(MealService service) {
        super(service);
    }
}