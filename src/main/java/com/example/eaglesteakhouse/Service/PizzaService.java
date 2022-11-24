package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Pizza;
import com.example.eaglesteakhouse.Repository.PizzaRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PizzaService implements IPizzaService{

    private PizzaRepo pizzaRepo;

    public PizzaService(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public Set<Pizza> findAll() {
        Set<Pizza> set = new HashSet<>();
        pizzaRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Pizza save(Pizza object) {
        return pizzaRepo.save(object);
    }

    @Override
    public void delete(Pizza object) {
        pizzaRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        pizzaRepo.deleteById(aLong);
    }

    @Override
    public Optional<Pizza> findById(Long aLong) {
        return pizzaRepo.findById(aLong);
    }
}
