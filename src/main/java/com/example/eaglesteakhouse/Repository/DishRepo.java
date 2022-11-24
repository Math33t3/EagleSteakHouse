package com.example.eaglesteakhouse.Repository;

import com.example.eaglesteakhouse.Model.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepo extends CrudRepository<Dish, Long> {
}
