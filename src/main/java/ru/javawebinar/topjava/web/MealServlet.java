package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.stream.Collectors;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    ConfigurableApplicationContext springContext;
    private MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = springContext.getBean("mealRestController", MealRestController.class);

    }

    @Override
    public void destroy() {
        super.destroy();
        springContext.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                SecurityUtil.authUserId(),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (meal.isNew()) {
            log.info("Create {}", meal);
            controller.create(meal);
        } else {
            log.info("Update {}", meal);
            controller.update(meal, meal.getId());
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);

        switch (action == null ? "all" : action) {
            case "filter":
                LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
                LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
                LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
                LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));

                log.info("getFiltered date({} - {}) time({} {})", startDate, endDate, startTime, endTime);
                if (startTime != null && startDate != null) {
                    request.setAttribute("meals",
                            MealsUtil.getTos(controller.getAll(), SecurityUtil.authUserCaloriesPerDay()).stream()
                                    .filter(m -> DateTimeUtil.isBetween(m.getDateTime(), startDate, startTime, endDate, endTime))
                                    .collect(Collectors.toList()));
                } else if (startTime == null && startDate != null) {
                    request.setAttribute("meals", MealsUtil.getTos(controller.getAll(), SecurityUtil.authUserCaloriesPerDay()).stream()
                            .filter(m -> DateTimeUtil.isBetweenDate(m.getDateTime().toLocalDate(), startDate, endDate))
                            .collect(Collectors.toList()));
                } else if (startDate == null && startTime != null) {
                    request.setAttribute("meals", MealsUtil.getTos(controller.getAll(), SecurityUtil.authUserCaloriesPerDay()).stream()
                            .filter(m -> DateTimeUtil.isBetweenTime(m.getDateTime().toLocalTime(), startTime, endTime))
                            .collect(Collectors.toList()));
                } else {
                    request.setAttribute("meals",
                            MealsUtil.getTos(controller.getAll(), SecurityUtil.authUserCaloriesPerDay()));
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                }
                System.out.println();
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(SecurityUtil.authUserId(), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        MealsUtil.getTos(controller.getAll(), SecurityUtil.authUserCaloriesPerDay()));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
