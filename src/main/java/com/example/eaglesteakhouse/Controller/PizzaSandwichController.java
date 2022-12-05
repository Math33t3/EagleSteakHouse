package com.example.eaglesteakhouse.Controller;


import com.example.eaglesteakhouse.Model.Pita;
import com.example.eaglesteakhouse.Model.PizzaSandwich;
import com.example.eaglesteakhouse.Service.PizzaSandwichService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PizzaSandwichController {

    private PizzaSandwichService pizzaSandwichService;

    public PizzaSandwichController(PizzaSandwichService pizzaSandwichService) {
        this.pizzaSandwichService = pizzaSandwichService;
    }
    @PostMapping("/savePizzaSandwich")
    public ResponseEntity<PizzaSandwich> savePizzaSandwich(@RequestBody PizzaSandwich pizzaSandwich){
        pizzaSandwichService.save(pizzaSandwich);
        return new ResponseEntity<>(pizzaSandwich, HttpStatus.OK);
    }

    @GetMapping("/getPizzaSandwichList")
    public ResponseEntity<List<PizzaSandwich>> getPizzaSandwichList(){

        Set<PizzaSandwich> mySet = pizzaSandwichService.findAll();
        List<PizzaSandwich> myList = mySet.stream().sorted(new Comparator<PizzaSandwich>() {
            @Override
            public int compare(PizzaSandwich pizzaSandwich1, PizzaSandwich pizzaSandwich2) {
                return pizzaSandwich1.getId().compareTo(pizzaSandwich2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
    }

    @GetMapping("/getPizzaSandwichById")
    public ResponseEntity<PizzaSandwich> getPizzaSandwichById(@RequestParam Long id) {
        Optional<PizzaSandwich> pizzaSandwichOptional = pizzaSandwichService.findById(id);
        PizzaSandwich voidPizzaSandwich = new PizzaSandwich();
        if (pizzaSandwichOptional.isPresent()) {
            return new ResponseEntity<>(pizzaSandwichOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(voidPizzaSandwich, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/updatePizzaSandwich")
    public ResponseEntity<PizzaSandwich> updatePizzaSandwich(@RequestParam Long id, @RequestParam String name,
                                               @RequestParam String description, @RequestParam int price){
        PizzaSandwich pizzaSandwich = new PizzaSandwich();
        pizzaSandwich.setId(id);
        pizzaSandwich.setName(name);
        pizzaSandwich.setDescription(description);
        pizzaSandwich.setPrice(price);
        pizzaSandwichService.save(pizzaSandwich);
        return new ResponseEntity<>(pizzaSandwich, HttpStatus.OK);
    }

    @DeleteMapping("/deletePizzaSandwichById")
    public ResponseEntity<String> deletePizzaSandwichById(@RequestParam Long id){
        Optional<PizzaSandwich> pizzaSandwichOptional = pizzaSandwichService.findById(id);
        String msg;
        if(pizzaSandwichOptional.isPresent()){
            pizzaSandwichService.deleteById(id);
            msg = "PizzaSandwich with ID: " + id + " has been deleted.";
        }else{
            msg = "This pizzaSandwich-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }
}
