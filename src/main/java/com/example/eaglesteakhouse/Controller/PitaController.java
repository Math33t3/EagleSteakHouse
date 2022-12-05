package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Chips;
import com.example.eaglesteakhouse.Model.Drink;
import com.example.eaglesteakhouse.Model.Menu;
import com.example.eaglesteakhouse.Model.Pita;
import com.example.eaglesteakhouse.Service.ChipsService;
import com.example.eaglesteakhouse.Service.PitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PitaController {

    private PitaService pitaService;

    public PitaController(PitaService pitaService) {this.pitaService = pitaService;}

    @PostMapping("/savePita")
    public ResponseEntity<Pita> saveChips(@RequestBody Pita pita){
        pitaService.save(pita);
        return new ResponseEntity<>(pita, HttpStatus.OK);
    }

    @GetMapping("/getPitaList")public ResponseEntity<List<Pita>> getPitaList(){

        Set<Pita> mySet = pitaService.findAll();
        List<Pita> myList = mySet.stream().sorted(new Comparator<Pita>() {
            @Override
            public int compare(Pita pita1, Pita pita2) {
                return pita1.getId().compareTo(pita2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
    }


    @GetMapping("/getPitaById")
    public ResponseEntity<Pita> getPitaById(@RequestParam Long id){
        Optional<Pita> pitaOptional = pitaService.findById(id);
        Pita voidPita = new Pita();
        if(pitaOptional.isPresent()){
            return new ResponseEntity<>(pitaOptional.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voidPita, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updatePita")
        public ResponseEntity<Pita> updatePita(@RequestParam Long id, @RequestParam String name,
                                               @RequestParam String description,@RequestParam int price){
        Pita pita = new Pita();
        pita.setId(id);
        pita.setName(name);
        pita.setPrice(price);
        pita.setDescription(description);
        pitaService.save(pita);
        return new ResponseEntity<>(pita, HttpStatus.OK);
    }

    @DeleteMapping("/deletePitaById")
    public ResponseEntity<String> deletePitaById(@RequestParam Long id){
        Optional<Pita> pitaOptional = pitaService.findById(id);
        String msg;
        if(pitaOptional.isPresent()){
            pitaService.deleteById(id);
            msg = "Pita with ID :" + id + " has been deleted.";
        }else{
            msg = "This pita-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
