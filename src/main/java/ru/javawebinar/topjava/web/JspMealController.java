package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;

@Controller
public class JspMealController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    private final MealService service;

    public JspMealController(MealService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/meals")
    public String getMeals(Model model) {
        log.info("meals");
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", service.getAll(userId));
        return "meals";
    }

    @GetMapping("/meals")
    public String deleteMeal(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = SecurityUtil.authUserId();
        service.delete(id, userId);
        return "redirect:meals";
    }

    @GetMapping("/meals")
    public String filteredMeals(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        int userId = SecurityUtil.authUserId();
        request.setAttribute("meals", service.getBetweenInclusive(startDate, endDate, userId));
        return "redirect:meals";
    }

    @PostMapping("/meals")
    public String setMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            service.update(meal, getId(request));
        } else {
            service.create(meal, Objects.requireNonNull(meal.getUser().getId()));
        }
        return "redirect:meals";

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
