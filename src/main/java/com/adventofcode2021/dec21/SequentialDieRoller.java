package com.adventofcode2021.dec21;

class SequentialDieRoller implements DieRoller {

    private int nextRoll;
    private int numberOfRolls;

    SequentialDieRoller( int firstDieRoll ) {
        this.nextRoll = firstDieRoll;
        this.numberOfRolls = 0;
    }

    @Override
    public int rollDie() {
        int roll = nextRoll++;
        if ( nextRoll > 100 ) {
            nextRoll = 1;
        }
        ++numberOfRolls;
        return roll;
    }

    @Override
    public int numberOfTimesRolled() {
        return numberOfRolls;
    }
}
