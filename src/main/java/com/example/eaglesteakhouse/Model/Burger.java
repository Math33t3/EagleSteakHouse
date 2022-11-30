package com.example.eaglesteakhouse.Model;


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
public class Burger {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    @ManyToOne // many burgers can have one lunchOfferMenu entry
    @JsonBackReference // stops circular references in JSON output
    @EqualsAndHashCode.Exclude // leaves lunchOfferMenu out in the equals()
    // og hashCode() methods, to avoid circular reference
    private LunchOfferMenu burgerLunchOffer;
}
