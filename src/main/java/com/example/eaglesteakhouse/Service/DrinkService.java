package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Drink;
import com.example.eaglesteakhouse.Repository.DrinkRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DrinkService implements IDrinkService{

    private DrinkRepo drinkRepo;

    public DrinkService(DrinkRepo drinkRepo) {
        this.drinkRepo = drinkRepo;
    }

    @Override
    public Set<Drink> findAll() {
        Set<Drink> set = new HashSet<>();
        drinkRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Drink save(Drink object) {
        return drinkRepo.save(object);
    }

    @Override
    public void delete(Drink object) {
        drinkRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        drinkRepo.deleteById(aLong);
    }

    @Override
    public Optional<Drink> findById(Long aLong) {
        return drinkRepo.findById(aLong);
    }
}
