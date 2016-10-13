package com.academy.app.moviegenerator;

import java.util.Random;

public class Generator {

    private static Generator generator;
    private String[] answers;
    public Random rand = new Random();
    public int randInd = 0;

    private Generator() {
    answers = new String[] {
        "Scary Movie 4",
            "Scream",
            "Pulp Fiction",
            "DeadPool" ,
            "Jason Bourne",
            "Lion King",
            "Transformers",
            "The God Father",
            "SpiderMan",
            "Blair Witch"
    };
    }
    public static Generator get() {
        if(generator == null){
    generator = new Generator();

    }
    return generator;
    }
    public String getGenerator(){
        randInd = rand.nextInt(answers.length);
        return answers[randInd];
    }
}
