package com.adventofcode2021.dec17;

class Velocity {

    private final int xVelocity;
    private final int yVelocity;

    Velocity( int xVelocity, int yVelocity ) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    int x() {
        return xVelocity;
    }

    int y() {
        return yVelocity;
    }

    Velocity drag() {
        return new Velocity( dragX( xVelocity ), dragY( yVelocity ) );
    }

    private int dragX( int xVelocity ) {
        if ( xVelocity == 0 ) {
            return 0;
        } else if ( xVelocity > 0 ) {
            return xVelocity - 1;
        } else {
            return xVelocity + 1;
        }
    }

    private int dragY( int yVelocity ) {
        return yVelocity - 1;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "xVelocity=" + xVelocity +
                ", yVelocity=" + yVelocity +
                '}';
    }
}
