package com.adventofcode2021.dec02;

class UpSubmarineCommand implements SubmarineCommand {

    private final int distance;

    UpSubmarineCommand( int distance ) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "up " + distance;
    }

    @Override
    public void applyTo( Submarine submarine ) {
        submarine.surface( distance );
    }
}
