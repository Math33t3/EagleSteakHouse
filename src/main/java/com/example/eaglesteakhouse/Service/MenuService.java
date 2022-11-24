package com.example.eaglesteakhouse.Service;

import com.example.eaglesteakhouse.Model.Menu;
import com.example.eaglesteakhouse.Repository.MenuRepo;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuService implements IMenuService{
    private MenuRepo menuRepo;

    public MenuService(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }

    @Override
    public Set<Menu> findAll() {
        Set<Menu> set = new HashSet<>();
        menuRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Menu save(Menu object) {
        return menuRepo.save(object);
    }

    @Override
    public void delete(Menu object) {
        menuRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        menuRepo.deleteById(aLong);
    }

    @Override
    public Optional<Menu> findById(Long aLong) {
        return menuRepo.findById(aLong);
    }
}
