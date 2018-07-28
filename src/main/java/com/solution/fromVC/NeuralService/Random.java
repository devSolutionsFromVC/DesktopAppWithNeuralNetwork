package com.solution.fromVC.NeuralService;

import java.text.DecimalFormat;

public class Random {

    private static final String format = "#0.000";

    public static void main(String[] args) {


        for (int i = 0; i < 100; i++){
            double rand = Math.random();
            String randNew = new DecimalFormat(format).format(rand);
            System.out.println(randNew);
        }
    }
}
