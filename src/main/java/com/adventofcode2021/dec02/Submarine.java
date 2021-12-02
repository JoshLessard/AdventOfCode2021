package com.adventofcode2021.dec02;

import com.adventofcode2021.common.Delta;
import com.adventofcode2021.common.Point;

class Submarine {

    private int aim;
    private Point position;

    Submarine() {
        this.aim = 0;
        this.position = Point.ORIGIN;
    }

    void dive( int distance ) {
        position = position.translate( new Delta( 0, distance ) );
    }

    void forward( int distance ) {
        position = position.translate( new Delta( distance, 0 ) );
    }

    void surface( int distance ) {
        position = position.translate( new Delta( 0, -distance ) );
    }

    void changeAim( int aimDelta ) {
        aim += aimDelta;
    }

    int currentAim() {
        return aim;
    }

    Point currentPosition() {
        return position;
    }
}
