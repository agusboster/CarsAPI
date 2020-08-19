package com.exercise.layers.Entities.Stat;

public class Stat {
    private String name;
    private int count;
    private float percent;

    public Stat(String _name, int _count, float _percent){
        this.setName(_name);
        this.setCount(_count);
        this.setPercent(_percent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
