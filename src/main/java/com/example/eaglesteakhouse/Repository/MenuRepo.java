package com.example.eaglesteakhouse.Repository;

import com.example.eaglesteakhouse.Model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepo extends CrudRepository<Menu, Long> {
}
