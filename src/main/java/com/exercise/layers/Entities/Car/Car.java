package com.exercise.layers.Entities.Car;

import com.exercise.layers.Entities.Optional.Optional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String model;
    private String type;
    private Float basicPrice;
    @Transient
    private List<Optional> optionals;

    public Car(Integer _id, String _model, String _type, Float _basicPrice) {
        super();
        this.id = _id;
        this.model = _model;
        this.type = _type;
        this.basicPrice = _basicPrice;
    }

    public Car(){
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String newModel) {
        this.model = newModel;
    }

    public String getType() {
        return type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public Float getBasicPrice() {
        return basicPrice;
    }

    public void setOBasicPrice(Float newBasicPrice) {
        this.basicPrice = newBasicPrice;
    }

    public List getOptionals() {
        return optionals;
    }

    public void setOptionals(List<Optional> newOptionals) {
        this.optionals = newOptionals;
    }

}
