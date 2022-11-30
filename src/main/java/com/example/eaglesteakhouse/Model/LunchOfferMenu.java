package com.example.eaglesteakhouse.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Set<Burger> burgerSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chipsLunchOffer")
    private Set<Chips> chipsSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishLunchOffer")
    private Set<Dish> dishSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drinkLunchOffer")
    private Set<Drink> drinkSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "durumLunchOffer")
    private Set<Durum> durumSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuLunchOffer")
    private Set<Menu> menuSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pitaLunchOffer")
    private Set<Pita> pitaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaLunchOffer")
    private Set<Pizza> pizzaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaSandwichLunchOffer")
    private Set<PizzaSandwich> pizzaSandwichSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turkBreadLunchOffer")
    private Set<TurkBread> turkBreadSet;
}
