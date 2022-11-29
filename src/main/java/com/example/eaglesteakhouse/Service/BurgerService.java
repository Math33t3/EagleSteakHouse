package com.example.eaglesteakhouse.Service;


import com.example.eaglesteakhouse.Model.Burger;
import com.example.eaglesteakhouse.Repository.BurgerRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BurgerService implements IBurgerService{

    private BurgerRepo burgerRepo;

    public BurgerService(BurgerRepo burgerRepo) {
        this.burgerRepo = burgerRepo;
    }

    @Override
    public Set<Burger> findAll() {
        Set<Burger> set = new HashSet<>();
        burgerRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Burger save(Burger object) {
        return burgerRepo.save(object);
    }

    @Override
    public void delete(Burger object) {
        burgerRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        burgerRepo.deleteById(aLong);
    }

    @Override
    public Optional<Burger> findById(Long aLong) {
        return burgerRepo.findById(aLong);
    }
}
