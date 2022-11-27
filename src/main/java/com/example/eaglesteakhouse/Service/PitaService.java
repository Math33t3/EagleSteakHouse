package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Pita;
import com.example.eaglesteakhouse.Repository.PitaRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PitaService implements IPitaService {

    private PitaRepo pitaRepo;

    public PitaService(PitaRepo pitaRepo) {this.pitaRepo = pitaRepo;}

    @Override
    public Set<Pita> findAll() {
        Set<Pita> set = new HashSet<>();
        pitaRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Pita save(Pita object) {
        return pitaRepo.save(object);
    }

    @Override
    public void delete(Pita object) {
        pitaRepo.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {pitaRepo.deleteById(aLong);}

    @Override
    public Optional<Pita> findById(Long aLong) {
        return pitaRepo.findById(aLong);
    }
}
