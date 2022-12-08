package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.*;
import com.example.eaglesteakhouse.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class LunchOfferMenuController {

    private LunchOfferMenuService lunchOfferMenuService;
    private BurgerService burgerService;
    private ChipsService chipsService;
    private DishService dishService;
    private DrinkService drinkService;
    private DurumService durumService;
    private MenuService menuService;
    private PitaService pitaService;
    private PizzaService pizzaService;
    private PizzaSandwichService pizzaSandwichService;
    private TurkBreadService turkBreadService;

    public LunchOfferMenuController(LunchOfferMenuService lunchOfferMenuService, BurgerService burgerService,
                                    ChipsService chipsService, DishService dishService,
                                    DrinkService drinkService, DurumService durumService,
                                    MenuService menuService, PitaService pitaService,
                                    PizzaService pizzaService, PizzaSandwichService pizzaSandwichService,
                                    TurkBreadService turkBreadService) {
        this.lunchOfferMenuService = lunchOfferMenuService;
        this.burgerService = burgerService;
        this.chipsService = chipsService;
        this.dishService = dishService;
        this.drinkService = drinkService;
        this.durumService = durumService;
        this.menuService = menuService;
        this.pitaService = pitaService;
        this.pizzaService = pizzaService;
        this.pizzaSandwichService = pizzaSandwichService;
        this.turkBreadService = turkBreadService;
    }

    @PostMapping("/saveLunchOfferMenu")
    public ResponseEntity<LunchOfferMenu> saveLunchOffermenu() {
        LunchOfferMenu lunchOfferMenu = new LunchOfferMenu();
        lunchOfferMenuService.save(lunchOfferMenu);
        return new ResponseEntity<>(lunchOfferMenu, HttpStatus.OK);
    }


    @GetMapping("/getLunchOfferMenuList")
    public ResponseEntity<Set<LunchOfferMenu>> getLunchOfferMenuList() {
        return new ResponseEntity<>(lunchOfferMenuService.findAll(), HttpStatus.OK);
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
    public ResponseEntity<String> deleteLunchOfferMenuById(@RequestParam Long id) {
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(id);
        String msg;
        if (lunchOfferMenuOptional.isPresent()) {
            lunchOfferMenuService.deleteById(id);
            msg = "LunchOfferMenu with ID: " + id + " has been deleted.";
        } else {
            msg = "This LunchOfferMenu-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @PostMapping("/addBurger")
    public ResponseEntity<Burger> addBurger(@RequestParam Long LOid, @RequestParam Long burgerId) {
        System.out.println("received burger with ID: " + burgerId);
        // 1. check om børgererer findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Burger> burgerOptional = burgerService.findById(burgerId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (burgerOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Burger burger = burgerOptional.get();
            burger.setBurgerLunchOffer(lunchOfferMenu);
            burgerService.save(burger);
            return new ResponseEntity<>(burger, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addChips")
    public ResponseEntity<Chips> addChips(@RequestParam Long LOid, @RequestParam Long chipsId) {
        System.out.println("received chips with ID: " + chipsId);
        // 1. check om Chips findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Chips> chipsOptional = chipsService.findById(chipsId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (chipsOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Chips chips = chipsOptional.get();
            chips.setChipsLunchOffer(lunchOfferMenu);
            chipsService.save(chips);
            return new ResponseEntity<>(chips, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addDish")
    public ResponseEntity<Dish> addDish(@RequestParam Long LOid, @RequestParam Long dishId) {
        System.out.println("received dish with ID: " + dishId);
        // 1. check om Dish findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Dish> dishOptional = dishService.findById(dishId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (dishOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Dish dish = dishOptional.get();
            dish.setDishLunchOffer(lunchOfferMenu);
            dishService.save(dish);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addDrink")
    public ResponseEntity<Drink> addDrink(@RequestParam Long LOid, @RequestParam Long drinkId) {
        System.out.println("received drink with ID: " + drinkId);
        // 1. check om Drink findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Drink> drinkOptional = drinkService.findById(drinkId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (drinkOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Drink drink = drinkOptional.get();
            drink.setDrinkLunchOffer(lunchOfferMenu);
            drinkService.save(drink);
            return new ResponseEntity<>(drink, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addDurum")
    public ResponseEntity<Durum> addDurum(@RequestParam Long LOid, @RequestParam Long durumId) {
        System.out.println("received durum with ID: " + durumId);
        // 1. check om Durum findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Durum> durumOptional = durumService.findById(durumId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (durumOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Durum durum = durumOptional.get();
            durum.setDurumLunchOffer(lunchOfferMenu);
            durumService.save(durum);
            return new ResponseEntity<>(durum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMenu")
    public ResponseEntity<Menu> addMenu(@RequestParam Long LOid, @RequestParam Long menuId) {
        System.out.println("received menu with ID: " + menuId);
        // 1. check om Menu findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Menu> menuOptional = menuService.findById(menuId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (menuOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Menu menu = menuOptional.get();
            menu.setMenuLunchOffer(lunchOfferMenu);
            menuService.save(menu);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addPita")
    public ResponseEntity<Pita> addPita(@RequestParam Long LOid, @RequestParam Long pitaId) {
        System.out.println("received pita with ID: " + pitaId);
        // 1. check om Pita findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Pita> pitaOptional = pitaService.findById(pitaId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (pitaOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Pita pita = pitaOptional.get();
            pita.setPitaLunchOffer(lunchOfferMenu);
            pitaService.save(pita);
            return new ResponseEntity<>(pita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addPizza")
    public ResponseEntity<Pizza> addPizza(@RequestParam Long LOid, @RequestParam Long pizzaId) {
        System.out.println("received pizza with ID: " + pizzaId);
        // 1. check om Pizza findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<Pizza> pizzaOptional = pizzaService.findById(pizzaId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (pizzaOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            Pizza pizza = pizzaOptional.get();
            pizza.setPizzaLunchOffer(lunchOfferMenu);
            pizzaService.save(pizza);
            return new ResponseEntity<>(pizza, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addPizzaSandwich")
    public ResponseEntity<PizzaSandwich> addPizzaSandwich(@RequestParam Long LOid, @RequestParam Long pizzaSandwichId) {
        System.out.println("received Pizza Sandwich with ID: " + pizzaSandwichId);
        // 1. check om Pizza Sandwich findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<PizzaSandwich> pizzaSandwichOptional = pizzaSandwichService.findById(pizzaSandwichId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (pizzaSandwichOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            PizzaSandwich pizzaSandwich = pizzaSandwichOptional.get();
            pizzaSandwich.setPizzaSandwichLunchOffer(lunchOfferMenu);
            pizzaSandwichService.save(pizzaSandwich);
            return new ResponseEntity<>(pizzaSandwich, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTurkBread")
    public ResponseEntity<TurkBread> addTurkBread(@RequestParam Long LOid, @RequestParam Long turkBreadId) {
        System.out.println("received Turkish Bread with ID: " + turkBreadId);
        // 1. check om Turkish Bread findes
        // 2. hvis den findes, sæt den ind i frokost menuen
        Optional<TurkBread> turkBreadOptional = turkBreadService.findById(turkBreadId);
        Optional<LunchOfferMenu> lunchOfferMenuOptional = lunchOfferMenuService.findById(LOid);
        if (turkBreadOptional.isPresent() && lunchOfferMenuOptional.isPresent()) {
            LunchOfferMenu lunchOfferMenu = lunchOfferMenuOptional.get();
            TurkBread turkBread = turkBreadOptional.get();
            turkBread.setTurkBreadLunchOffer(lunchOfferMenu);
            turkBreadService.save(turkBread);
            return new ResponseEntity<>(turkBread, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
