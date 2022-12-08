package com.example.eaglesteakhouse.Controller;


import com.example.eaglesteakhouse.Model.Pizza;
import com.example.eaglesteakhouse.Service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class PizzaController {

    private PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping("/savePizza")
    public ResponseEntity<Pizza> savePizza(@RequestBody Pizza pizza){
        pizzaService.save(pizza);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @GetMapping("/getPizzaList")
    public ResponseEntity<List<Pizza>> getPizzaList(){
        Set<Pizza> mySet = pizzaService.findAll();

        List<Pizza> myList = mySet.stream().sorted(new Comparator<Pizza>() {
            @Override
            public int compare(Pizza pizza1, Pizza pizza2) {
                return pizza1.getId().compareTo(pizza2.getId());}
        }).collect(Collectors.toList());


        return new ResponseEntity<>(myList,HttpStatus.OK);
    }



    @GetMapping("/getPizzaById")
    public ResponseEntity<Pizza> getPizzaById(@RequestParam Long id){
        Optional<Pizza> pizzaOptional = pizzaService.findById(id);
        Pizza voidPizza = new Pizza();
        if(pizzaOptional.isPresent()){
            return new ResponseEntity<>(pizzaOptional.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voidPizza,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updatePizza")
    public ResponseEntity<Pizza> updatePizza(@RequestParam Long id,@RequestParam String name,
                                             @RequestParam String description, @RequestParam int regPrice,
                                             @RequestParam int bigPrice){
        Pizza pizza = new Pizza();
        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setRegPrice(regPrice);
        pizza.setBigPrice(bigPrice);
        pizza.setId(id);
        pizzaService.save(pizza);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @DeleteMapping("/deletePizzaById")
    public ResponseEntity<String> deletePizzaById(@RequestParam Long id){
        Optional<Pizza> pizzaOptional = pizzaService.findById(id);
        String msg;
        if(pizzaOptional.isPresent()){
            pizzaService.deleteById(id);
            msg = "Pizza with ID: " + id + " has been deleted.";
        }else{
            msg = "Pizza-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
