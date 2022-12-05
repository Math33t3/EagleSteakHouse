package com.example.eaglesteakhouse.Controller;


import com.example.eaglesteakhouse.Model.Dish;
import com.example.eaglesteakhouse.Service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/getDishList")
    public ResponseEntity<List<Dish>> getDishList(){

        Set<Dish> mySet = dishService.findAll();

        List<Dish> myList = mySet.stream().sorted(new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2) {
                return dish1.getId().compareTo(dish2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
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

    @PostMapping("/updateDish")
    public ResponseEntity<Dish> updateDish(@RequestParam Long id, @RequestParam String name,
                                           @RequestParam String description,@RequestParam int price){
        Dish dish = new Dish();
        dish.setId(id);
        dish.setName(name);
        dish.setDescription(description);
        dish.setPrice(price);
        dishService.save(dish);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDishById")
    public ResponseEntity<String> deleteDishById(@RequestParam Long id){
        Optional<Dish> dishOptional = dishService.findById(id);
        String msg;
        if(dishOptional.isPresent()){
            dishService.deleteById(id);
            msg = "Dish with ID: " + id + " has been deleted.";
        }else{
            msg = "This dish-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
