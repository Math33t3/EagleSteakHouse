package com.example.eaglesteakhouse.Model;


/*Retter kombinerert med drikkevare og andet tilbehør, f.eks Durum menu = Durumrulle med
pommes frites og en drikkevare
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private LunchOfferMenu menuLunchOffer;

}
