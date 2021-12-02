package com.adventofcode2021.dec02;

class ForwardSubmarineCommand implements SubmarineCommand {

    private final int distance;

    ForwardSubmarineCommand( int distance ) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "forward " + distance;
    }

    @Override
    public void applyTo( Submarine submarine ) {
        submarine.forward( distance );
    }
}
