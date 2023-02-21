package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            MealRestController mealRestController = appCtx.getBean("mealRestController", MealRestController.class);

            // get
            System.out.println(mealRestController.getAll());
            System.out.println(mealRestController.getAll().size());

            Meal meal = new Meal(1, LocalDateTime.now(), "новое описание", 1214);
            // create
            System.out.println(mealRestController.create(meal));

            System.out.println("----------------------------------------------------------------------");

            // get
            System.out.println(mealRestController.getAll());
            System.out.println(mealRestController.getAll().size());

            // update
            Meal updatedMeal = new Meal(1, LocalDateTime.now(), "еще одно новое описание", 1214);
            mealRestController.update(updatedMeal, 8);

            System.out.println("----------------------------------------------------------------------");

            // get
            System.out.println(mealRestController.getAll());
            System.out.println(mealRestController.getAll().size());
        }
    }
}
