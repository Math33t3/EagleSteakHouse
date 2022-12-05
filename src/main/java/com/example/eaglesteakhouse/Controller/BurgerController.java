package com.example.eaglesteakhouse.Controller;


import com.example.eaglesteakhouse.Model.Burger;
import com.example.eaglesteakhouse.Service.BurgerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/getBurgerList")   //Lavet denne til at sortere listen inden den sendes
    public ResponseEntity<List<Burger>> getBurgerList(){

        Set<Burger> mySet = burgerService.findAll();

        List<Burger> myList = mySet.stream().sorted(new Comparator<Burger>() {
            @Override
            public int compare(Burger burger1, Burger burger2) {
                return burger1.getId().compareTo(burger2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
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
            burgerService.deleteById(id);
            msg = "Burger with ID: " + id + " has been deleted.";
        }else{
            msg = "This burger-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

}
