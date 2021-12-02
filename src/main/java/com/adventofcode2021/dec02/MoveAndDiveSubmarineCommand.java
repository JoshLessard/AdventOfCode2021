package com.adventofcode2021.dec02;

class MoveAndDiveSubmarineCommand implements SubmarineCommand {

    private final int x;

    MoveAndDiveSubmarineCommand( int x ) {
        this.x = x;
    }

    @Override
    public void applyTo( Submarine submarine ) {
        submarine.forward( x );
        submarine.dive( submarine.currentAim() * x );
    }
}
