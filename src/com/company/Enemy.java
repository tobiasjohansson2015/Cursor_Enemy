package com.company;

public class Enemy extends Creature {
    public float howFastEnemy; // Fiendens hastighet.


    public Enemy(int x, int y, float howFastEnemy) {
        super(x, y);
        this.howFastEnemy = howFastEnemy;
    }
}
