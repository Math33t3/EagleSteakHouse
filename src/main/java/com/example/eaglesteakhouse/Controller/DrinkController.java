package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Drink;
import com.example.eaglesteakhouse.Service.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    @GetMapping("/getDrinkList")
    public ResponseEntity<Set<Drink>> getDrinkList(){
        return new ResponseEntity<>(drinkService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getDrinkById")
    //Bruger ResponseEntity til at sende et svar til kaldet på endpointed
    public ResponseEntity<Drink> getDrinkById(@RequestParam Long id){
        //Vi bruger en Optional fordi det ikke er sikkert det man søger på er oprettet.
        Optional<Drink> drinkOptional = drinkService.findById(id);
        //Opretter et tomt Drink objekt til brug hvis den id der søges på ikke findes
        Drink voidDrink = new Drink();
        //Checker om Optional indeholder et Drink objekt
        if(drinkOptional.isPresent()){
            return new ResponseEntity<>(drinkOptional.get(), HttpStatus.OK);
        }else{
        //Hvis den Optional er tom returneres en negativ HTTP status
            return new ResponseEntity<>(voidDrink,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateDrink")
    public ResponseEntity<Drink> updateDrink(@RequestParam Long id, @RequestParam String name,
                                             @RequestParam int price){
        Drink drink = new Drink();
        drink.setId(id);
        drink.setName(name);
        drink.setPrice(price);
        drinkService.save(drink);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDrinkById")
    public ResponseEntity<String> deleteDrinkById(@RequestParam Long id){
        Optional<Drink> drinkOptional = drinkService.findById(id);
        String msg;
        if(drinkOptional.isPresent()) {
            drinkService.deleteById(id);
            msg = "Drink with ID :" + id + " has been deleted.";
        }else{
            msg = "This drink-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
