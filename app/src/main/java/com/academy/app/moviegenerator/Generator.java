package com.academy.app.moviegenerator;

public class Generator {

    private static Generator generator;
    private String[] answers;

    private Generator() {
    answers = new String[] {
        "liflsjdfj"
    };
    }
    public static Generator get() {
        if(generator == null){
    generator = new Generator();

    }
    return generator;
    };
    public String getGenerator(){

        return answers[0];
    }
}
