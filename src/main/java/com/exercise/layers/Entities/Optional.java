package com.exercise.layers.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Optional {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private Float price;

    public Optional(Integer _id, String _name, Float _price) {
        super();
        this.id = _id;
        this.name = _name;
        this.price = _price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setModel(String newName) {
        this.name = newName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float newPrice) {
        this.price = newPrice;
    }
}
