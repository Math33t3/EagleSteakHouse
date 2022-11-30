package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Burger;
import com.example.eaglesteakhouse.Model.Durum;
import com.example.eaglesteakhouse.Model.LunchOfferMenu;
import com.example.eaglesteakhouse.Service.BurgerService;
import com.example.eaglesteakhouse.Service.LunchOfferMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class LunchOfferMenuController {

    private LunchOfferMenuService lunchOfferMenuService;
    private BurgerService burgerService;

    public LunchOfferMenuController(LunchOfferMenuService lunchOfferMenuService, BurgerService burgerService) {
        this.lunchOfferMenuService = lunchOfferMenuService;
        this.burgerService = burgerService;
    }

    @PostMapping("/saveLunchOfferMenu")
    public ResponseEntity<LunchOfferMenu> saveLunchOffermenu(){
        LunchOfferMenu lunchOfferMenu = new LunchOfferMenu();
        lunchOfferMenuService.save(lunchOfferMenu);
        return new ResponseEntity<>(lunchOfferMenu, HttpStatus.OK);
    }


    @GetMapping("/getLunchOfferMenuList")
    public ResponseEntity<Set<LunchOfferMenu>> getLunchOfferMenuList(){
        return new ResponseEntity<>(lunchOfferMenuService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getLunchOfferMenuById")
    public ResponseEntity<LunchOfferMenu> getLunchOfferMenuById(@RequestParam Long id) {
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(id);
        LunchOfferMenu voidLunchOfferMenu = new LunchOfferMenu();
        if (lunchOfferMenuOptional.isPresent()) {
            return new ResponseEntity<>(lunchOfferMenuOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(voidLunchOfferMenu, HttpStatus.NOT_FOUND);
        }

    }

   /* @PostMapping("/updateLunchOfferMenu")
    public ResponseEntity<LunchOfferMenu> updateLunchOfferMenu(@RequestParam Long id){
        LunchOfferMenu lunchOfferMenu = new LunchOfferMenu();
        lunchOfferMenu.setId(id);
        lunchOfferMenuService.save(lunchOfferMenu);
        return new ResponseEntity<>(lunchOfferMenu, HttpStatus.OK);
    }*/

    @DeleteMapping("/deleteLunchOfferMenuById")
    public ResponseEntity<String> deleteLunchOfferMenuById(@RequestParam Long id){
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(id);
        String msg;
        if(lunchOfferMenuOptional.isPresent()){
            lunchOfferMenuService.deleteById(id);
            msg = "LunchOfferMenu with ID: " + id + " has been deleted.";
        }else{
            msg = "This LunchOfferMenu-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @PostMapping("/addBurger")
    public ResponseEntity<Burger> addBurger(@RequestParam Long LOid, @RequestParam Long burgerId){
        System.out.println("received burger with ID: " + burgerId);
        // 1. check om børgererer findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Burger> burgerOptional = burgerService.findById(burgerId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if(burgerOptional.isPresent() && lunchOfferMenuOptional.isPresent()){
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Burger burger = burgerOptional.get();
            burger.setBurgerLunchOffer(lunchOfferMenu);
            burgerService.save(burger);
            return new ResponseEntity<>(burger, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
