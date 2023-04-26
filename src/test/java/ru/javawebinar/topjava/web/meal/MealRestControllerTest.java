package ru.javawebinar.topjava.web.meal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.web.user.AdminRestController;

import static org.junit.Assert.*;

@ContextConfiguration("classpath:spring-app-test.xml")
@RunWith(SpringRunner.class)
@Ignore
public class MealRestControllerTest {

    @Autowired
    private MealRestController controller;

    @Autowired
    private InMemoryMealRepository repository;

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getBetween() {
    }
}