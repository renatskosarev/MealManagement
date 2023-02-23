package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1_ID, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test
    public void AdminGet() {
        Meal meal = service.get(MEAL_10_ID, ADMIN_ID);
        assertMatch(meal, MEAL10);
    }

    @Test
    public void delete() {
        service.delete(MEAL_4_ID, USER_ID);
        List<Meal> meals = new ArrayList<>(USER_MEALS);
        meals.remove(MEAL4);
        assertMatch(service.getAll(USER_ID), meals);
    }

    @Test
    public void getBetweenDates() {
        assertMatch(service.getBetweenDates(START_DATE, END_DATE, USER_ID), USER_MEALS);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, USER_MEALS);
    }

    @Test
    public void AdminGetAll() {
        List<Meal> meals = service.getAll(ADMIN_ID);
        assertMatch(meals, ADMIN_MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL_1_ID, MEAL1.getDateTime(), MEAL1.getDescription(), MEAL1.getCalories());
        updated.setCalories(30);
        updated.setDescription("new description");
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_1_ID, USER_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(NEW_MEAL_DATETIME, "new meal description", 12);
        int id = service.create(newMeal, USER_ID).getId();
        newMeal.setId(id);
        assertMatch(service.get(id, USER_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(MEAL_10_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(MEAL10, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(MEAL_10_ID, USER_ID);
    }
}