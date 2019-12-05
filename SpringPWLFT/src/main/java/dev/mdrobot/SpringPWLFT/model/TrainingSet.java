package dev.mdrobot.SpringPWLFT.model;

import lombok.Data;

@Data
public class TrainingSet {

    private int rep;
    private double load;

    public TrainingSet(int rep, double load) {
        this.rep = rep;
        this.load = load;
    }

    @Override
    public String toString() {
        return "set[" + rep + " x " + load + "]";
    }
}