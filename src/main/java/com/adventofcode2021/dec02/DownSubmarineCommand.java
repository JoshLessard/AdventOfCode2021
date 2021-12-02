package com.adventofcode2021.dec02;

class DownSubmarineCommand implements SubmarineCommand {

    private final int distance;

    DownSubmarineCommand( int distance ) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "down " + distance;
    }

    @Override
    public void applyTo( Submarine submarine ) {
        submarine.dive( distance );
    }
}
