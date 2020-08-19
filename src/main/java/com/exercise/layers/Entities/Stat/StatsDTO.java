package com.exercise.layers.Entities.Stat;

import com.exercise.layers.Entities.Stat.Stat;

import java.util.List;

public class StatsDTO {
    private int carsCount;
    private List<Stat> cars;
    private int optionalsCount;
    private List<Stat> optionals;

    public StatsDTO(int _carsCount, List<Stat> _cars, int _optionalsCount, List<Stat> _optionals){
        this.setCarsCount(_carsCount);
        this.setCars(_cars);
        this.setOptionalsCount(_optionalsCount);
        this.setOptionals(_optionals);
    }

    public StatsDTO() {
        super();
    }

    public int getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(int carsCount) {
        this.carsCount = carsCount;
    }

    public List<Stat> getCars() {
        return cars;
    }

    public void setCars(List<Stat> cars) {
        this.cars = cars;
    }

    public int getOptionalsCount() {
        return optionalsCount;
    }

    public void setOptionalsCount(int optionalsCount) {
        this.optionalsCount = optionalsCount;
    }

    public List<Stat> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<Stat> optionals) {
        this.optionals = optionals;
    }
}
