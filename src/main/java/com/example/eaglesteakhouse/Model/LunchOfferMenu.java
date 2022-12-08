package com.example.eaglesteakhouse.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LunchOfferMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "burgerLunchOffer")
    private List<Burger> burgerSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chipsLunchOffer")
    private List<Chips> chipsSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishLunchOffer")
    private List<Dish> dishSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drinkLunchOffer")
    private List<Drink> drinkSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "durumLunchOffer")
    private List<Durum> durumSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuLunchOffer")
    private List<Menu> menuSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pitaLunchOffer")
    private List<Pita> pitaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaLunchOffer")
    private List<Pizza> pizzaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaSandwichLunchOffer")
    private List<PizzaSandwich> pizzaSandwichSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turkBreadLunchOffer")
    private List<TurkBread> turkBreadSet;
}
