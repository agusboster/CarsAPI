package com.exercise.layers.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String model;
    private String type;
    @Transient
    private List<Optional> optionals;

    public Car(Integer _id, String _model, String _type, List<Optional> _optionals) {
        super();
        this.id = _id;
        this.model = _model;
        this.type = _type;
        this.optionals = _optionals;
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

    public List getOptionals() {
        return optionals;
    }

    public void setOptionals(List<Optional> newOptionals) {
        this.optionals = newOptionals;
    }

}
