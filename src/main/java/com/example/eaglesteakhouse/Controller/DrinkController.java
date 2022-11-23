package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Drink;
import com.example.eaglesteakhouse.Service.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class DrinkController {

    private DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping("/saveDrink")
    public ResponseEntity<Drink> saveDrink(@RequestBody Drink drink){
        drinkService.save(drink);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @GetMapping("/getDrinkMenu")
    public ResponseEntity<Set<Drink>> getDrinkMenu(){
        return new ResponseEntity<>(drinkService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getDrinkById")
    public ResponseEntity<Drink> getDrinkById(@RequestParam Long id){
        return new ResponseEntity<>(drinkService.findById(id).get(), HttpStatus.OK);
    }
}
