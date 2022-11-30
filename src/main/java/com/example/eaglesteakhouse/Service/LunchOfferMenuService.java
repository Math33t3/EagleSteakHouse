package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.LunchOfferMenu;
import com.example.eaglesteakhouse.Repository.LunchOfferMenuRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LunchOfferMenuService implements ILunchOfferMenuService{

    private LunchOfferMenuRepo lunchOfferMenuRepo;

    public LunchOfferMenuService(LunchOfferMenuRepo lunchOfferMenuRepo) {
        this.lunchOfferMenuRepo = lunchOfferMenuRepo;
    }



    @Override
    public Set<LunchOfferMenu> findAll(){
        Set<LunchOfferMenu> set = new HashSet<>();
        lunchOfferMenuRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public LunchOfferMenu save(LunchOfferMenu object) {return lunchOfferMenuRepo.save(object);}

    @Override
    public void delete(LunchOfferMenu object) {lunchOfferMenuRepo.delete(object);}

    @Override
    public void deleteById(Long aLong) {lunchOfferMenuRepo.deleteById(aLong);}

    @Override
    public Optional<LunchOfferMenu> findById(Long aLong) {return lunchOfferMenuRepo.findById(aLong);}
}
