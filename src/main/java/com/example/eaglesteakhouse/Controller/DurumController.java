package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Durum;
import com.example.eaglesteakhouse.Service.DurumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class DurumController {

    private DurumService durumService;

    public DurumController(DurumService durumService) {
        this.durumService = durumService;
    }

    @PostMapping("/saveDurum")
    public ResponseEntity<Durum> saveDurum(@RequestBody Durum durum){
        durumService.save(durum);
        return new ResponseEntity<>(durum, HttpStatus.OK);
    }


    @GetMapping("/getDurumList")
    public ResponseEntity<Set<Durum>> getDurumList(){
        return new ResponseEntity<>(durumService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getDurumById")
    public ResponseEntity<Durum> getDurumById(@RequestParam Long id) {
        Optional<Durum> durumOptional = durumService.findById(id);
        Durum voidDurum = new Durum();
        if (durumOptional.isPresent()) {
            return new ResponseEntity<>(durumOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(voidDurum, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/updateDurum")
    public ResponseEntity<Durum> updateDurum(@RequestParam Long id, @RequestParam String name,
                                               @RequestParam String description, @RequestParam int price){
        Durum durum = new Durum();
        durum.setId(id);
        durum.setName(name);
        durum.setDescription(description);
        durum.setPrice(price);
        durumService.save(durum);
        return new ResponseEntity<>(durum, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDurumById")
    public ResponseEntity<String> deleteDurumById(@RequestParam Long id){
        Optional<Durum> durumOptional = durumService.findById(id);
        String msg;
        if(durumOptional.isPresent()){
            msg = "Durum with ID: " + id + " has been deleted.";
        }else{
            msg = "This durum-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }


}