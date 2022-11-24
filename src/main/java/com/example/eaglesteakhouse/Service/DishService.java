package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Dish;
import com.example.eaglesteakhouse.Repository.DishRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DishService implements IDishService{

    private DishRepo dishRepo;

    public DishService(DishRepo dishRepo) {
        this.dishRepo = dishRepo;
    }

    @Override
    public Set<Dish> findAll() {
        Set<Dish> set = new HashSet<>();
        dishRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Dish save(Dish object) {
        return dishRepo.save(object);
    }

    @Override
    public void delete(Dish object) {
        dishRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        dishRepo.deleteById(aLong);
    }

    @Override
    public Optional<Dish> findById(Long aLong) {
        return dishRepo.findById(aLong);
    }
}
