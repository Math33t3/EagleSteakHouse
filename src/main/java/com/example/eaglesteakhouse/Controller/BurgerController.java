package com.example.eaglesteakhouse.Controller;


import com.example.eaglesteakhouse.Model.Burger;
import com.example.eaglesteakhouse.Service.BurgerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class BurgerController {

    private BurgerService burgerService;

    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @PostMapping("/saveBurger")
    public ResponseEntity<Burger> saveBurger(@RequestBody Burger burger){
        burgerService.save(burger);
        return new ResponseEntity<>(burger, HttpStatus.OK);
    }

    @GetMapping("/getBurgerList")
    public ResponseEntity<Set<Burger>> getBurgerList(){
        return new ResponseEntity<>(burgerService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getBurgerById")
    public ResponseEntity<Burger> getBurgerById(@RequestParam Long id) {
        Optional<Burger> burgerOptional = burgerService.findById(id);
        Burger voidBurger = new Burger();
        if (burgerOptional.isPresent()) {
            return new ResponseEntity<>(burgerOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(voidBurger, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/updateBurger")
    public ResponseEntity<Burger> updateBurger(@RequestParam Long id, @RequestParam String name,
                                               @RequestParam String description, @RequestParam int price){
        Burger burger = new Burger();
        burger.setId(id);
        burger.setName(name);
        burger.setDescription(description);
        burger.setPrice(price);
        burgerService.save(burger);
        return new ResponseEntity<>(burger, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBurgerById")
    public ResponseEntity<String> deleteBurgerById(@RequestParam Long id){
        Optional<Burger> burgerOptional = burgerService.findById(id);
        String msg;
        if(burgerOptional.isPresent()){
            msg = "Burger with ID: " + id + " has been deleted.";
        }else{
            msg = "This burger-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

}
