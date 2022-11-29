package com.example.eaglesteakhouse.Repository;

import com.example.eaglesteakhouse.Model.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepo extends CrudRepository<Burger, Long> {
}
