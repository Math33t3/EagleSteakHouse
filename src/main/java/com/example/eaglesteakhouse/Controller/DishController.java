package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Dish;
import com.example.eaglesteakhouse.Service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class DishController {
    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/saveDish")
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish){
        dishService.save(dish);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @GetMapping("/getDishMenu")
    public ResponseEntity<Set<Dish>> getDishMenu(){
        return new ResponseEntity<>(dishService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getDishById")
    public ResponseEntity<Dish> getDishById(@RequestParam Long id){
        Optional<Dish> dishOptional = dishService.findById(id);
        Dish voidDish = new Dish();
        if(dishOptional.isPresent()){
            return new ResponseEntity<>(dishOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voidDish, HttpStatus.NOT_FOUND);
        }
    }


}
