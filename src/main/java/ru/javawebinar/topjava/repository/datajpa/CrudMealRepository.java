package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    int deleteByIdAndUserId(Integer id, Integer userId);

    Meal findByIdAndUserId(Integer id, Integer userId);

    List<Meal> findAllByUserId(Integer userId);

    List<Meal> findAllByDateTimeBetweenAndUserId(LocalDate startDate, LocalDate endDate, int userId);
}
