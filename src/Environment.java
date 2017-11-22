/*
* Siddharth Rajan
* CSC 22100
* Assignment 4
* */

import java.security.SecureRandom;

public abstract class Environment {

    private int energy = 1000;
    private int age = 0;

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public abstract char getSymbol();

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public static Environment[][] addPlants(Environment[][] e) {

        SecureRandom one = new SecureRandom();
        int count;
        int ranx;
        int rany;
        int it;

        count = 1 + one.nextInt(10);
        it = 0;

        while (it < count) {
            ranx = one.nextInt(16);
            rany = one.nextInt(16);
            if (e[ranx][rany] == null) {
                e[ranx][rany] = new Plant();
                ++it;
            }
        }

        return e;
    }

    public static Environment[][] addCarn(Environment[][] e) {

        SecureRandom one = new SecureRandom();
        int count;
        int ranx;
        int rany;
        int it;

        count = 1 + one.nextInt(10);
        it = 0;

        while (it < count) {
            ranx = one.nextInt(16);
            rany = one.nextInt(16);
            if (e[ranx][rany] == null) {
                e[ranx][rany] = new Carnivore();
                ++it;
            }
        }

        return e;
    }

    public static Environment[][] addHerb(Environment[][] e) {

        SecureRandom one = new SecureRandom();
        int count;
        int ranx;
        int rany;
        int it;

        count = 1 + one.nextInt(10);
        it = 0;

        while (it < count) {
            ranx = one.nextInt(16);
            rany = one.nextInt(16);
            if (e[ranx][rany] == null) {
                e[ranx][rany] = new Herbivore();
                ++it;
            }
        }

        return e;
    }

    public static Environment[][] move(Environment[][] e) {

        SecureRandom one = new SecureRandom();
        int rows = 16;
        int columns = 16;

        String[] directions = {"up", "down", "left", "right"};

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {

                if (e[i][j] instanceof Carnivore || e[i][j] instanceof Herbivore) {

                    int ran = one.nextInt(directions.length);

                    switch (directions[ran]) {
                        case "up":
                            if (i > 0 && e[i - 1][j] == null) {
                                e[i - 1][j] = e[i][j];
                                e[i - 1][j].setEnergy(e[i][j].getEnergy() - 10);
                                e[i][j] = null;
                                if (e[i - 1][j].getEnergy() == 0) {
                                    e[i - 1][j] = null;
                                }
                            }
                            break;
                        case "down":
                            if (i < 15 && e[i + 1][j] == null) {
                                e[i + 1][j] = e[i][j];
                                e[i + 1][j].setEnergy(e[i][j].getEnergy() - 10);
                                e[i][j] = null;
                                if (e[i + 1][j].getEnergy() == 0) {
                                    e[i + 1][j] = null;
                                }
                            }
                            break;
                        case "left":
                            if (j > 0 && e[i][j - 1] == null) {
                                e[i][j - 1] = e[i][j];
                                e[i][j - 1].setEnergy(e[i][j].getEnergy() - 10);
                                e[i][j] = null;
                                if (e[i][j - 1].getEnergy() == 0) {
                                    e[i][j - 1] = null;
                                }
                            }
                            break;
                        case "right":
                            if (j < 15 && e[i][j + 1] == null) {
                                e[i][j + 1] = e[i][j];
                                e[i][j + 1].setEnergy(e[i][j].getEnergy() - 10);
                                e[i][j] = null;
                                if (e[i][j + 1].getEnergy() == 0) {
                                    e[i][j + 1] = null;
                                }
                            }
                            break;
                    }
                }
            }
        }

        return e;
    }

    public static Environment[][] eat(Environment[][] e) {

        int rows = 16;
        int columns = 16;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {

                if (e[i][j] instanceof Carnivore) {
                    // Check up
                    if (i > 0 && e[i - 1][j] instanceof Herbivore) {
                        e[i - 1][j] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                    // Check down
                    if (i < 15 && e[i + 1][j] instanceof Herbivore) {
                        e[i + 1][j] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                    // Check left
                    if (j > 0 && e[i][j - 1] instanceof Herbivore) {
                        e[i][j - 1] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                    // Check right
                    if (j < 15 && e[i][j + 1] instanceof Herbivore) {
                        e[i][j + 1] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                }

                else if (e[i][j] instanceof Herbivore) {
                    // Check up
                    if (i > 0 && e[i - 1][j] instanceof Plant) {
                        e[i - 1][j] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                    // Check down
                    if (i < 15 && e[i + 1][j] instanceof Plant) {
                        e[i + 1][j] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                    // Check left
                    if (j > 0 && e[i][j - 1] instanceof Plant) {
                        e[i][j - 1] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                    // Check right
                    if (j < 15 && e[i][j + 1] instanceof Plant) {
                        e[i][j + 1] = null;
                        e[i][j].setEnergy(e[i][j].getEnergy() + 10);
                    }
                }
            }
        }

        return e;
    }

    public static Environment[][] increaseAge(Environment[][] e) {

        int rows = 16;
        int columns = 16;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (e[i][j] instanceof Carnivore || e[i][j] instanceof Herbivore) {
                    e[i][j].setAge(e[i][j].getAge() + 1);
                }
            }
        }

        return e;
    }

    public static Environment[][] reproduce(Environment[][] e) {

        int rows = 16;
        int columns = 16;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (e[i][j] != null && (e[i][j] instanceof Carnivore || e[i][j] instanceof Herbivore)) {
                    // If you can check up and find another carnivore with little age difference
                    if (i > 0 && e[i - 1][j] != null && e[i - 1][j].getClass().getName().equals(e[i][j].getClass().getName()) && e[i][j].getAge() > 15 && e[i - 1][j].getAge() > 15) {
                        // Check for available spaces near the parent
                        // If the below space is null
                        if (i < 15 && e[i + 1][j] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i + 1][j] = new Carnivore(); }
                            else { e[i + 1][j] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i - 1][j].setEnergy(e[i - 1][j].getEnergy() - 20);
                        }
                        // If the left space is null
                        else if (j > 0 && e[i][j - 1] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i][j - 1] = new Carnivore(); }
                            else { e[i][j - 1] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i - 1][j].setEnergy(e[i - 1][j].getEnergy() - 20);
                        }
                        // If the right space is null
                        else if (j < 15 && e[i][j + 1] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i][j + 1] = new Carnivore(); }
                            else { e[i][j + 1] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i - 1][j].setEnergy(e[i - 1][j].getEnergy() - 20);
                        }
                    }
                    // If you can check down and find another carnivore with little age difference
                    else if (i < 15 && e[i + 1][j] != null && e[i + 1][j].getClass().getName().equals(e[i][j].getClass().getName()) && e[i][j].getAge() > 15 && e[i + 1][j].getAge() > 15) {
                        // Check for available spaces near the parent
                        // If the above space is null
                        if (i > 0 && e[i - 1][j] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i - 1][j] = new Carnivore(); }
                            else { e[i - 1][j] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i + 1][j].setEnergy(e[i + 1][j].getEnergy() - 20);
                        }
                        // If the left space is null
                        else if (j > 0 && e[i][j - 1] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i][j - 1] = new Carnivore(); }
                            else { e[i][j - 1] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i + 1][j].setEnergy(e[i + 1][j].getEnergy() - 20);
                        }
                        // If the right space is null
                        else if (j < 15 && e[i][j + 1] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i][j + 1] = new Carnivore(); }
                            else { e[i][j + 1] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i + 1][j].setEnergy(e[i + 1][j].getEnergy() - 20);
                        }
                    }
                    // If you can check left and find another carnivore with little age difference
                    else if (j > 0 && e[i][j - 1] != null && e[i][j - 1].getClass().getName().equals(e[i][j].getClass().getName()) && e[i][j].getAge() > 15 && e[i][j - 1].getAge() > 15) {
                        // Check for available spaces near the parent
                        // If the below space is null
                        if (i < 15 && e[i + 1][j] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i + 1][j] = new Carnivore(); }
                            else { e[i + 1][j] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i][j - 1].setEnergy(e[i][j - 1].getEnergy() - 20);
                        }
                        // If the above space is null
                        else if (i > 0 && e[i - 1][j] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i - 1][j] = new Carnivore(); }
                            else { e[i - 1][j] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i][j - 1].setEnergy(e[i][j - 1].getEnergy() - 20);
                        }
                        // If the right space is null
                        else if (j < 15 && e[i][j + 1] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i][j + 1] = new Carnivore(); }
                            else { e[i][j + 1] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i][j - 1].setEnergy(e[i][j - 1].getEnergy() - 20);
                        }
                    }
                    // If you can check right and find another carnivore with little age difference
                    else if (j < 15 && e[i][j + 1] != null && e[i][j + 1].getClass().getName().equals(e[i][j].getClass().getName()) && e[i][j].getAge() > 15 && e[i][j + 1].getAge() > 15) {
                        // Check for available spaces near the parent
                        // If the below space is null
                        if (i < 15 && e[i + 1][j] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i + 1][j] = new Carnivore(); }
                            else { e[i + 1][j] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i][j + 1].setEnergy(e[i][j + 1].getEnergy() - 20);
                        }
                        // If the left space is null
                        else if (j > 0 && e[i][j - 1] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i][j - 1] = new Carnivore(); }
                            else { e[i][j - 1] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i][j + 1].setEnergy(e[i][j + 1].getEnergy() - 20);
                        }
                        // If the above space is null
                        else if (i > 0 && e[i - 1][j] == null) {
                            if (e[i][j] instanceof Carnivore) { e[i - 1][j] = new Carnivore(); }
                            else { e[i - 1][j] = new Herbivore(); }
                            e[i][j].setEnergy(e[i][j].getEnergy() - 20);
                            e[i][j + 1].setEnergy(e[i][j + 1].getEnergy() - 20);
                        }
                    }
                }
            }
        }
        return e;
    }

    public static void printGrid(Environment[][] e, int iter) {

        int rows = 16;
        int columns = 16;
        int carncount = 0;
        int herbcount = 0;
        int plantcount = 0;
        System.out.printf("Iteration %d%n", iter + 1);

        // Print new grid
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {

                if (e[i][j] != null) {
                    System.out.printf("%s ", e[i][j].getSymbol());
                    if (e[i][j] instanceof Carnivore) {
                        ++carncount;
                    }
                    else if (e[i][j] instanceof Herbivore) {
                        ++herbcount;
                    }
                    else if (e[i][j] instanceof Plant) {
                        ++plantcount;
                    }
                }

                else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

        System.out.printf("Carnivores (@) = %d%nHerbivores (&) = %d%nPlants (#) = %d%n",
                carncount, herbcount, plantcount);

        System.out.print("\n\n");
    }
}
