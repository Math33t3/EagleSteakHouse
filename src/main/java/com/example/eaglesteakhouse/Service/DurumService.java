package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Durum;
import com.example.eaglesteakhouse.Repository.DurumRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DurumService implements IDurumService{

    private DurumRepo durumRepo;

    public DurumService(DurumRepo durumRepo) {
        this.durumRepo = durumRepo;
    }

    @Override
    public Set<Durum> findAll() {
        Set<Durum> set = new HashSet<>();
        durumRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Durum save(Durum object) {
        return durumRepo.save(object);
    }

    @Override
    public void delete(Durum object) {
        durumRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        durumRepo.deleteById(aLong);
    }

    @Override
    public Optional<Durum> findById(Long aLong) {
        return durumRepo.findById(aLong);
    }
}
