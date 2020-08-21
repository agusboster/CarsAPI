package com.exercise.layers.Entities.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Optional implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer carId;
    private String name;
    private Float price;

    public Optional(Integer _id, String _name, Float _price) {
        super();
        this.id = _id;
        this.name = _name;
        this.price = _price;
    }

    public Optional(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer newCarId) {
        this.carId = newCarId;
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
