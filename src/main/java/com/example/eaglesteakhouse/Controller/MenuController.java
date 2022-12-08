package com.example.eaglesteakhouse.Controller;

import com.example.eaglesteakhouse.Model.Burger;
import com.example.eaglesteakhouse.Model.Menu;
import com.example.eaglesteakhouse.Service.MenuService;
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
public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/saveMenu")
    public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu){
        menuService.save(menu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/getMenuList")  //sorterer listen inden den sendes
    public ResponseEntity<List<Menu>> getMenuList(){

        Set<Menu> mySet = menuService.findAll();
        List<Menu> myList = mySet.stream().sorted(new Comparator<Menu>() {
            @Override
            public int compare(Menu menu1, Menu menu2) {
                return menu1.getId().compareTo(menu2.getId());
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(myList,HttpStatus.OK);
    }

    @GetMapping("/getMenuById")
    public ResponseEntity<Menu> getMenuById(@RequestParam Long id){
        Optional<Menu> menuOptional = menuService.findById(id);
        Menu voidMenu = new Menu();
        if(menuOptional.isPresent()){
            return new ResponseEntity<>(menuOptional.get(), HttpStatus.OK );
        }else{
            return new ResponseEntity<>(voidMenu, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateMenu")
    public ResponseEntity<Menu> updateMenu(@RequestParam Long id, @RequestParam String name,
                                           @RequestParam String description, @RequestParam int price){
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setDescription(description);
        menu.setPrice(price);
        menuService.save(menu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMenuById")
    public ResponseEntity<String> deleteMenuById(@RequestParam Long id){
        Optional<Menu> menuOptional = menuService.findById(id);
        String msg;
        if(menuOptional.isPresent()){
            menuService.deleteById(id);
            msg = "Menu with ID: " + id + " has been deleted.";
        }else{
            msg = "This menu-ID does not exist, nothing was deleted. Type an existing ID.";
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
