package com.example.eaglesteakhouse.Repository;

import com.example.eaglesteakhouse.Model.Drink;
import org.springframework.data.repository.CrudRepository;

public interface DrinkRepo extends CrudRepository<Drink, Long> {
}
