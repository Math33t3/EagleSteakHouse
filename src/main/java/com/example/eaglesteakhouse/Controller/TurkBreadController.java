package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Dish;
import com.example.eaglesteakhouse.Model.Pita;
import com.example.eaglesteakhouse.Model.TurkBread;
import com.example.eaglesteakhouse.Service.DishService;
import com.example.eaglesteakhouse.Service.TurkBreadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class TurkBreadController {
    private TurkBreadService turkBreadService;

    public TurkBreadController(TurkBreadService turkBreadService) {
        this.turkBreadService = turkBreadService;
    }

    @PostMapping("/saveTurkBread")
    public ResponseEntity<TurkBread> saveTurkBread(@RequestBody TurkBread turkBread){
        turkBreadService.save(turkBread);
        return new ResponseEntity<>(turkBread, HttpStatus.OK);
    }

    @GetMapping("/getTurkBreadList")
    public ResponseEntity<List<TurkBread>> getTurkBreadList(){

        Set<TurkBread> mySet = turkBreadService.findAll();
        List<TurkBread> myList = mySet.stream().sorted(new Comparator<TurkBread>() {
            @Override
            public int compare(TurkBread turkBread1, TurkBread turkBread2) {
                return turkBread1.getId().compareTo(turkBread2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
    }

    @GetMapping("/getTurkBreadById")
    public ResponseEntity<TurkBread> getTurkBreadById(@RequestParam Long id){
        Optional<TurkBread> turkBreadOptional = turkBreadService.findById(id);
        TurkBread voidTurkBread = new TurkBread();
        if(turkBreadOptional.isPresent()){
            return new ResponseEntity<>(turkBreadOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voidTurkBread, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateTurkBread")
    public ResponseEntity<TurkBread> updateTurkBread(@RequestParam Long id, @RequestParam String name,
                                           @RequestParam String description,@RequestParam int price){
        TurkBread turkBread = new TurkBread();
        turkBread.setId(id);
        turkBread.setName(name);
        turkBread.setDescription(description);
        turkBread.setPrice(price);
        turkBreadService.save(turkBread);
        return new ResponseEntity<>(turkBread, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTurkBreadById")
    public ResponseEntity<String> deleteTurkBreadById(@RequestParam Long id){
        Optional<TurkBread> turkBreadOptional = turkBreadService.findById(id);
        String msg;
        if(turkBreadOptional.isPresent()){
            turkBreadService.deleteById(id);
            msg = "TurkBread with ID: " + id + " has been deleted.";
        }else{
            msg = "This turkBread-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
