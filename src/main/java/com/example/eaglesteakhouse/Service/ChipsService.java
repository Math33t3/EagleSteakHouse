package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Chips;
import com.example.eaglesteakhouse.Repository.ChipsRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ChipsService implements IChipsService{

    private ChipsRepo chipsRepo;

    public ChipsService(ChipsRepo chipsRepo) {this.chipsRepo = chipsRepo;}

    @Override
    public Set<Chips> findAll() {
        Set<Chips> set = new HashSet<>();
        chipsRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Chips save(Chips object) {return chipsRepo.save(object);}

    @Override
    public void delete(Chips object) {chipsRepo.delete(object);}

    @Override
    public void deleteById(Long aLong) {chipsRepo.deleteById(aLong);}

    @Override
    public Optional<Chips> findById(Long aLong) {return chipsRepo.findById(aLong);}

}
