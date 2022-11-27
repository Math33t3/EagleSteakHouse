package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.TurkBread;
import com.example.eaglesteakhouse.Repository.TurkBreadRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TurkBreadService implements ITurkBreadService{

    private TurkBreadRepo turkBreadRepo;

    @Override
    public Set<TurkBread> findAll() {
        Set<TurkBread> set = new HashSet<>();
        turkBreadRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public TurkBread save(TurkBread object) {return turkBreadRepo.save(object);}

    @Override
    public void delete(TurkBread object) {turkBreadRepo.delete(object);}

    @Override
    public void deleteById(Long aLong) {turkBreadRepo.deleteById(aLong);}

    @Override
    public Optional<TurkBread> findById(Long aLong) {return turkBreadRepo.findById(aLong);}
}
