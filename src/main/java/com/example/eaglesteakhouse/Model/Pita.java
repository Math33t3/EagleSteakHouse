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
public class Pita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private LunchOfferMenu pitaLunchOffer;
}
