package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Pizza;
import com.example.eaglesteakhouse.Service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/getPizzaMenu")
    public ResponseEntity<Set<Pizza>> getPizzaMenu(){
        return new ResponseEntity<>(pizzaService.findAll(),HttpStatus.OK);
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
}
