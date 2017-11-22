/*
* Siddharth Rajan
* CSC 22100
* Assignment 4
* */

import java.security.SecureRandom;

public class TestSimulation {

    public static void main(String[] args) {

        // Declares rows, columns, and array
        int rows = 16;
        int columns = 16;
        Environment[][] e = new Environment[rows][columns];
        boolean plant;

        // Initializes array
        SecureRandom one = new SecureRandom();

        e = Environment.addPlants(e);

        e = Environment.addCarn(e);

        e = Environment.addHerb(e);

        // In every iteration, operate on each position in grid (moving, eating, reproducing, and monitor energy levels) and print resulting array
        for (int iter = 0; iter < 32; ++iter) {

            // Make objects move
            e = Environment.move(e);

            // Make objects eat
            e = Environment.eat(e);

            // Increase age of objects
            e = Environment.increaseAge(e);

            // Make objects reproduce
            e = Environment.reproduce(e);

            // Make plants grow randomly
            plant = one.nextBoolean();

            if (plant) {
                e = Environment.addPlants(e);
            }

            Environment.printGrid(e, iter);
        }

    }
}
