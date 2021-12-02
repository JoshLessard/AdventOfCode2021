package com.adventofcode2021.dec02;

class ChangeAimSubmarineCommand implements SubmarineCommand {

    private final int aimDelta;

    ChangeAimSubmarineCommand( int aimDelta ) {
        this.aimDelta = aimDelta;
    }

    @Override
    public void applyTo( Submarine submarine ) {
        submarine.changeAim( aimDelta );
    }
}
