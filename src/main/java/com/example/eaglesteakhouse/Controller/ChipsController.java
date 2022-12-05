package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Chips;
import com.example.eaglesteakhouse.Service.ChipsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ChipsController {

    private ChipsService chipsService;

    public ChipsController(ChipsService chipsService) {this.chipsService = chipsService;}

    @PostMapping("/saveChips")
    public ResponseEntity<Chips> saveChips(@RequestBody Chips chips){
        chipsService.save(chips);
        return new ResponseEntity<>(chips, HttpStatus.OK);
    }

    @GetMapping("/getChipsList")   //Lavet denne til at sortere listen inden den sendes
    public ResponseEntity<List<Chips>> getChipsList(){

        Set<Chips> mySet = chipsService.findAll();

        List<Chips> myList = mySet.stream().sorted(new Comparator<Chips>() {
            @Override
            public int compare(Chips chips1, Chips chips2) {
                return chips1.getId().compareTo(chips2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
    }

    @GetMapping("/getChipsById")
    public ResponseEntity<Chips> getChipsById(@RequestParam Long id){
        Optional<Chips> chipsOptional = chipsService.findById(id);
        Chips voidChips = new Chips();
        if(chipsOptional.isPresent()){
            return new ResponseEntity<>(chipsOptional.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voidChips, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateChips")
    public ResponseEntity<Chips> updateChips(@RequestParam Long id, @RequestParam String name,
                                             @RequestParam String description, @RequestParam int price){
        Chips chips = new Chips();
        chips.setId(id);
        chips.setName(name);
        chips.setDescription(description);
        chips.setPrice(price);
        chipsService.save(chips);
        return new ResponseEntity<>(chips, HttpStatus.OK);
    }

    @DeleteMapping("/deleteChipsById")
    public ResponseEntity<String> deleteChipsById(@RequestParam Long id){
        Optional<Chips> chipsOptional = chipsService.findById(id);
        String msg;
        if(chipsOptional.isPresent()){
            chipsService.deleteById(id);
            msg = "Chips with ID :" + id + " has been deleted.";
        }else{
            msg = "This chips-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
