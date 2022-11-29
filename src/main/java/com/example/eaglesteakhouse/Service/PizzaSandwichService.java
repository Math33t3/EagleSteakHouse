package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.PizzaSandwich;
import com.example.eaglesteakhouse.Repository.PizzaSandwichRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PizzaSandwichService implements IPizzaSandwichService{
    private PizzaSandwichRepo pizzaSandwichRepo;

    public PizzaSandwichService(PizzaSandwichRepo pizzaSandwichRepo) {
        this.pizzaSandwichRepo = pizzaSandwichRepo;
    }

    @Override
    public Set<PizzaSandwich> findAll() {
        Set<PizzaSandwich> set = new HashSet<>();
        pizzaSandwichRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public PizzaSandwich save(PizzaSandwich object) {
        return pizzaSandwichRepo.save(object);
    }

    @Override
    public void delete(PizzaSandwich object) {
        pizzaSandwichRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        pizzaSandwichRepo.deleteById(aLong);
    }

    @Override
    public Optional<PizzaSandwich> findById(Long aLong) {
        return pizzaSandwichRepo.findById(aLong);
    }
}
